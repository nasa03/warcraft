/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Instances of this class contain a set of {@link Interaction Interactions},
 * with methods available to add and retrieve them.
 *
 * @author Blair Butterworth
 */
public class InteractionContainer
{
    private Provider<InteractionDefinition> factory;
    private Collection<Interaction> interactions;

    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     *
     * @param factory an {@link InteractionDefinition} factory. This parameter
     *                cannot be {@code null}.
     */
    @Inject
    public InteractionContainer(Provider<InteractionDefinition> factory) {
        Objects.requireNonNull(factory);
        this.factory = factory;
        this.interactions = new ArrayList<>();
    }

    /**
     * Adds an {@link InteractionDefinition} to the container which is
     * applicable when invoked will generate an action identified by given
     * {@link ActionIdentifier}.
     *
     * @param actions   an array of action identifiers for the actions that
     *                  will be generated by the resulting interaction. This
     *                  parameter cannot be {@code null}.
     *
     * @return  a new {@code InteractionDefinition}. This method will no return
     *          {@code null}.
     */
    public InteractionDefinition addAction(ActionIdentifier ... actions) {
        InteractionDefinition interaction = factory.get();
        interaction.forAction(actions);
        interaction.forInput(UserInputType.Action);
        interaction.assignedTo(InteractionAssignment.Item);
        interaction.appliedAs(InteractionDisplacement.Replacement);
        interactions.add(interaction);
        return interaction;
    }

    /**
     * Adds the interactions contained in the given interaction container to
     * this container. Subsequent changes made to the given container will not
     * be reflected in this container.
     *
     * @param container an {@link InteractionContainer}.
     */
    public void addActions(InteractionContainer container) {
        Objects.requireNonNull(container);
        interactions.addAll(container.interactions);
    }

    /**
     * Returns the first interaction that applies to the given user input, target
     * item and selected item. The specific matching algorithm is contained in
     * the individual {@link Interaction} implementations.
     *
     * @param input     a {@link UserInput} instance specifying the interaction
     *                  the user made with the application
     * @param gameObject      the target of the users interaction.
     * @param selected  a currently selected item.
     *
     * @return the matching {@code Interaction}, if any.
     */
    public Interaction getInteraction(UserInput input, GameObject gameObject, GameObject selected) {
        for (Interaction interaction: interactions) {
            if (interaction.applies(input, gameObject, selected)) {
                return interaction;
            }
        }
        return null;
    }

    protected BiConsumer<GameObject, Collection<Action>> confirmedAction() {
        return (subject, actions) -> {
            for (Action action: actions) {
                Identifier id = action.getIdentifier();
                if (id instanceof ConfirmActions) {
                    GameObject parent = subject.getParent();
                    parent.addAction(action);
                } else {
                    subject.clearActions();
                    subject.addAction(action);
                }
            }
        };
    }

    protected BiFunction<GameObject, GameObject, GameObject> targetParentItem() {
        return (target, selected) -> {
            GameObject parent = target.getParent();
            if (parent instanceof Supplier) {
                Supplier supplier = (Supplier)parent;
                Object recipient = supplier.get();
                if (recipient instanceof GameObject) {
                    return (GameObject)recipient;
                }
            }
            return target;
        };
    }

    protected BiFunction<GameObject, GameObject, GameObject> selectedItem() {
        return (target, selected) -> selected;
    }

    protected BiFunction<GameObject, GameObject, GameObject> associatedItem() {
        return (target, selected) -> ((Unit)selected).getAssociatedItem();
    }
}
