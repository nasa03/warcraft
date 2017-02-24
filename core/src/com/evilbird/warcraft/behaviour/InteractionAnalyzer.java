package com.evilbird.warcraft.behaviour;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemUtils;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class InteractionAnalyzer implements Behaviour
{
    private ActionFactory actionFactory;
    private List<Interaction> interactions;

    public InteractionAnalyzer(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
        this.interactions = new ArrayList<Interaction>();

        this.interactions.add(new Interaction(UserInputType.Zoom, "Camera", null, ActionType.Zoom));
        this.interactions.add(new Interaction(UserInputType.Pan, "Camera", null, ActionType.Pan));

        this.interactions.add(new Interaction(UserInputType.Action, "Footman", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Peasant", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "TownHall", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Barracks", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Farm", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", null, ActionType.Select));

        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Footman", ActionType.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Grunt", ActionType.Move));

        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildFarm));
        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildBarracks));

        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", "Peasant", ActionType.GatherGold));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", "Peasant", ActionType.GatherWood));

        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", "Footman", ActionType.Attack));
    }

    @Override
    public void update(ItemGroup world, ItemGroup hud, List<UserInput> input)
    {
        update(world, input); //TODO
    }

    public void update(ItemGroup world, List<UserInput> inputs)
    {
        if (! inputs.isEmpty())
        {
            Collection<Item> selection = ItemUtils.findAll(world.getItems(), new Identifier("Selected"), true);

            for (UserInput input : inputs)
            {
                Collection<Item> targets = getTargets(world, input);

                updateActions(input, targets, selection);
            }
        }
    }

    private void updateActions(UserInput input, Collection<Item> targets, Collection<Item> selection)
    {
        for (Item target: targets)
        {
            if (selection.isEmpty())
            {
                updateActions(input, target, null);
            }
            for (Item selected: selection)
            {
                updateActions(input, target, selected);
            }
        }
    }

    private void updateActions(UserInput input, Item target, Item selected)
    {
        for (Interaction interaction: interactions)
        {
            if (interactionApplicable(interaction, input, target, selected))
            {
                addAction(interaction, input, target, selected);
            }
        }
    }

    //TODO - Messy/incomplete
    private void addAction(Interaction interaction, UserInput input, Item target, Item selected)
    {
        if (interaction.getCommandType() == ActionType.Move)
        {
            //Vector2 meh = selected.getStage().screenToStageCoordinates(input.getPosition());
            Vector2 position = input.getPosition(); //screenToStageCoordinates in getTargets modified input position
            Action action = actionFactory.newAction(new Identifier("Move"), selected, position);

            selected.clearActions();
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Select)
        {
            AnimatedItem animatedItem = (AnimatedItem)target;
            boolean itemSelected = (Boolean)animatedItem.getProperty(new Identifier("Selected"));
            Action action = actionFactory.newAction(new Identifier("Select"), target, !itemSelected);
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Zoom)
        {
            Action action = actionFactory.newAction(new Identifier("Zoom"), target, input);
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Pan)
        {
            Action action = actionFactory.newAction(new Identifier("Pan"), target, input.getDelta());
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.GatherGold)
        {
            Action action = actionFactory.newAction(new Identifier("GatherGold"), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.GatherWood)
        {
            Action action = actionFactory.newAction(new Identifier("GatherWood"), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.BuildFarm)
        {
            Action action = actionFactory.newAction(new Identifier("BuildFarm"), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.BuildBarracks)
        {
            Action action = actionFactory.newAction(new Identifier("BuildBarracks"), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Attack)
        {
            Action action = actionFactory.newAction(new Identifier("Attack"), selected, target);
            selected.addAction(action);
        }
    }

    private Collection<Item> getTargets(ItemGroup stage, UserInput userInput)
    {
        Vector2 inputPosition = userInput.getPosition();

        Vector2 worldPosition = stage.screenToStageCoordinates(inputPosition);

        Item target = (Item)stage.hit(worldPosition.x, worldPosition.y, false);

        Item camera = ItemUtils.findByType(stage, new Identifier("Camera"));

        return Arrays.asList(target, camera);
    }

    private boolean interactionApplicable(Interaction interaction, UserInput input, Item target, Item selected)
    {
        if (!Objects.equals(interaction.getInputType(), input.getType()))
        {
            return false;
        }
        //if (!Objects.equals(interaction.getTargetType(), target.getType()))
        if (!Objects.equals(interaction.getTargetType(), getType(target)))
        {
            return false;
        }
        if (interaction.getSelectedType() != null)
        {
            //if (!Objects.equals(interaction.getSelectedType(), selected.getType()))
            if (!Objects.equals(interaction.getSelectedType(), getType(selected)))
            {
                return false;
            }
        }
        return true;
    }

    //TODO: Investigate why type is missing
    private String getType(Item item)
    {
        if (item == null) return "Unknown";
        Identifier type = (Identifier)item.getProperty(new Identifier("Type"));
        return type != null ? type.toString() : "Unknown";
    }
}
