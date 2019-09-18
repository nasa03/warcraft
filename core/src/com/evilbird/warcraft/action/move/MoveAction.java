/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.pathing.SpatialUtils;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemNodePath;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.common.path.ItemPathFinder;
import com.evilbird.warcraft.item.common.movement.Movable;

import java.util.Collection;
import java.util.ListIterator;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.engine.action.ActionConstants.ACTION_INCOMPLETE;
import static com.evilbird.engine.common.function.Predicates.not;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination. The moving item will be animated
 * with a movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
abstract class MoveAction extends BasicAction
{
    protected Events events;
    protected ItemGraph graph;
    protected ItemNode endNode;
    protected ItemNode waypoint;
    protected ItemNodePath path;
    protected ListIterator<ItemNode> pathIterator;

    public MoveAction(Events events) {
        this.events = events;
    }

    @Override
    public void reset() {
        path = null;
    }

    @Override
    public void restart() {
        path = null;
    }

    @Override
    public boolean act(float time) {
        Item item = getItem();
        return move(item, time);
    }

    public boolean move(Item item, float time) {
        if (!initializePath(item)) {
            return moveFailed(item);
        }
        if (! waypointReached(item)) {
            return updatePosition(item, time);
        }
        if (destinationReached()) {
            return moveComplete(item);
        }
        if (!destinationValid() || nextWaypointOccupied()) {
            return reinitializePath(item);
        }
        return updateWaypoint(item);
    }

    private boolean moveFailed(Item item) {
        setError(new PathUnknownException(item));
        events.add(new MoveEvent(item, MoveStatus.Failed));
        return ACTION_COMPLETE;
    }

    private boolean moveComplete(Item item) {
        updateOccupancy(item);
        events.add(new MoveEvent(item, MoveStatus.Complete));
        return ACTION_COMPLETE;
    }

    private boolean reinitializePath(Item item) {
        updateOccupancy(item);
        restart();
        return ACTION_INCOMPLETE;
    }

    private boolean destinationReached() {
        return isDestinationReached(waypoint) || waypoint.equals(endNode);
    }

    protected boolean destinationValid() {
        return true;
    }

    protected abstract boolean isDestinationReached(ItemNode node);

    private boolean waypointReached(Item item) {
        Vector2 position = item.getPosition();
        Vector2 nodePosition = waypoint.getWorldReference();
        return position.equals(nodePosition);
    }

    private boolean nextWaypointOccupied() {
        if (pathIterator.nextIndex() < path.getCount()) {
            ItemNode nextNode = path.get(pathIterator.nextIndex());
            ItemPathFilter pathFilter = getPathFilter();
            return !pathFilter.test(nextNode);
        }
        return true;
    }

    protected abstract ItemPathFilter getPathFilter();

    protected abstract Vector2 getDestination();

    protected boolean initializePath(Item item) {
        if (path == null) {
            ItemRoot root = item.getRoot();
            graph = new ItemGraph(root.getSpatialGraph(), getPathFilter());
            waypoint = getStartNode(item);
            endNode = getEndNode(waypoint);
            path = ItemPathFinder.findPath(graph, waypoint, endNode);
            pathIterator = path.listIterator();

            if (!path.isEmpty()) {
                updateOccupancy(item);
                graph.addOccupants(waypoint, item);
            }
        }
        return !path.isEmpty();
    }

    protected ItemNode getStartNode(Item item) {
        if (graph.isPartiallyAligned(item)) {
            ItemNode destinationNode = graph.getNode(getDestination());
            Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(item);
            adjacentNodes.removeIf(not(getPathFilter()));
            if (!adjacentNodes.isEmpty()) {
                return SpatialUtils.getClosest(adjacentNodes, destinationNode);
            } 
        }
        return graph.getNode(item.getPosition());
    }

    protected ItemNode getEndNode(ItemNode node) {
        Vector2 destination = getDestination();
        return graph.getNode(destination);
    }

    private void updateOccupancy(Item item) {
        if (item instanceof ItemGraphOccupant) {
            ItemGraphOccupant occupant = (ItemGraphOccupant)item;

            Collection<ItemNode> previouslyOccupiedNodes = graph.getOccupiedNodes(occupant);
            graph.removeOccupants(previouslyOccupiedNodes, occupant);

            Collection<ItemNode> currentlyOccupiedNodes = graph.getNodes(occupant);
            graph.addOccupants(currentlyOccupiedNodes, occupant);
        }
    }

    private boolean updateWaypoint(Item item) {
        updateOccupancy(item);
        events.add(new MoveEvent(item, waypoint, MoveStatus.Updated));
        waypoint = pathIterator.next();
        graph.addOccupants(waypoint, item);
        return ACTION_INCOMPLETE;
    }

    private boolean updatePosition(Item item, float time) {
        Vector2 oldPosition = item.getPosition();
        Vector2 newPosition = getNextPosition(item, oldPosition, time);
        item.setPosition(newPosition);
        return ACTION_INCOMPLETE;
    }

    private Vector2 getNextPosition(Item item, Vector2 position, float time) {
        Movable movable = (Movable)item;

        Vector2 pathNodePosition = waypoint.getWorldReference();
        Vector2 remaining = pathNodePosition.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * movable.getMovementSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return pathNodePosition;
    }
}
