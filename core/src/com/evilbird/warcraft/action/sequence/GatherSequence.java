package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TimeDuration;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.Movable;
import com.evilbird.warcraft.item.unit.resource.ResourceContainer;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.unit.ResourceType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import java.util.Collection;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GatherSequence implements ActionProvider
{
    @Inject
    public GatherSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item gatherer, Item resource, UserInput input)
    {
        Item depot = findDepot(gatherer);
        Item player = findPlayer(gatherer);
        ResourceType type = getResourceProperty(resource);
        Action gather = gather(gatherer, resource, type, depot, player);
        return new RepeatedAction(gather);
    }

    private Action gather(Item gatherer, Item resource, ResourceType type, Item depot, Item player)
    {
        Action moveToResource = move(gatherer, resource);
        Action obtainResource = obtain(gatherer, resource, type);
        Action moveToDepot = move(gatherer, depot);
        Action depositResource = deposit(gatherer, player, type);
        return new SequenceAction(moveToResource, obtainResource, moveToDepot, depositResource);
    }

    private Action move(Item target, Item destination)
    {
        Action animate = new AnimateAction((Animated)target, UnitAnimation.Move);
        Action reposition = new com.evilbird.warcraft.action.common.MoveAction((Movable)target, destination);
        Action idle = new AnimateAction((Animated)target, UnitAnimation.Idle);
        return new SequenceAction(animate, reposition, idle);
    }

    private Action obtain(Item gatherer, Item resource, ResourceType type)
    {
        Action deselect = new SelectAction(gatherer, false);

        SoundIdentifier obtainSound = getObtainSound(type);
        AnimationIdentifier obtainAnimation = getObtainAnimation(type);

        Action gathererAnimation = new AnimateAction((Animated)gatherer, obtainAnimation);
        Action resourceAnimation = new AnimateAction((Animated)resource, obtainAnimation);

        Action gathererSound = repeatedSound(gatherer, obtainSound);
        Action resourceSound = repeatedSound(resource, obtainSound);

        Action setup = new ParallelAction(deselect, gathererAnimation, resourceAnimation, gathererSound, resourceSound);
        Action transfer = new com.evilbird.warcraft.action.common.ResourceTransferAction((ResourceContainer)resource, (ResourceContainer)gatherer);

        return new ParallelAction(setup, transfer);
    }

    private Action deposit(Item gatherer, Item player, ResourceType type)
    {
        AnimationIdentifier depositAnimation = getDepositAnimation(type);
        SoundIdentifier depositSound = getDepositSound(type);

        Action gathererAnimation = new AnimateAction((Animated)gatherer, depositAnimation);
        Action playerAnimation = new AnimateAction((Animated)player, depositAnimation);

        Action gathererSound = repeatedSound(gatherer, depositSound);
        Action playerSound = repeatedSound(player, depositSound);

        Action setup = new ParallelAction(gathererAnimation, playerAnimation, gathererSound, playerSound);
        Action transfer = new ResourceTransferAction((ResourceContainer)player, (ResourceContainer)gatherer);

        return new ParallelAction(setup, transfer);
    }

    private Action repeatedSound(Item item, SoundIdentifier soundId)
    {
        Action sound = new AudibleAction((Audible)item, soundId);
        Action soundBuffer = new DelayedAction(sound, new TimeDuration(1f));
        return new RepeatedAction(soundBuffer, 10);
    }

    private Item findDepot(Item item)
    {
        ItemGroup player = item.getParent();
        Predicate<Item> ownedDepots = itemWithType(new NamedIdentifier("TownHall"));
        Comparator<Item> closestDepot = closestItem(item);
        Collection<Item> depots = player.findAll(ownedDepots);
        return Collections.min(depots, closestDepot);
    }

    private Item findPlayer(Item item)
    {
        return item.getParent();
    }

    private ResourceType getResourceProperty(Item resource)
    {
        return ResourceType.valueOf(resource.getType().toString());
    }

    private AnimationIdentifier getObtainAnimation(ResourceType resource)
    {
        return UnitAnimation.valueOf("Gather" + resource.toString());
    }

    private SoundIdentifier getObtainSound(ResourceType resource)
    {
        return UnitSound.valueOf("Gather" + resource.toString());
    }

    private AnimationIdentifier getDepositAnimation(ResourceType resource)
    {
        return UnitAnimation.valueOf("Deposit" + resource.toString());
    }

    private SoundIdentifier getDepositSound(ResourceType resource)
    {
        return UnitSound.valueOf("Deposit" + resource.toString());
    }


    /*
    private Action resourceTake(Item item, ResourceType type, AnimationIdentifier animation, SoundIdentifier sound)
    {
        Action takeAnimation = new AnimateAction((Animated)item, animation);
        Action takeSound = repeatedSound(item, sound);
        Action takeResource = resourceTake(item, type);
        Action takeAction = new ParallelAction(takeResource, takeSound);
        Action idleAnimation = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new SequenceAction(takeAnimation, takeAction, idleAnimation);
    }

    private Action resourceReceive(Item item, ResourceType type, AnimationIdentifier animation, SoundIdentifier sound)
    {
        Action receiveAnimation = new AnimateAction((Animated)item, animation);
        Action receiveSound = repeatedSound(item, sound);
        Action receiveResource = resourceReceive(item, type);
        Action receiveAction = new ParallelAction(receiveResource, receiveSound);
        Action idleAnimation = new AnimateAction((Animated)item,  UnitAnimation.Idle);
        return new SequenceAction(receiveAnimation, receiveAction, idleAnimation);
    }

    private Action resourceTake(Item item, ResourceType type)
    {
        ActionValue value = new ItemValue(item, type);
        ActionModifier modifier = new DeltaModifier(-1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(value, modifier, duration);
    }

    private Action resourceReceive(Item item, ResourceType type)
    {
        ActionValue value = new ItemValue(item, type);
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(value, modifier, duration);
    }
    */






    /*
    @Override
    public Action get(Item gatherer, Item resource, UserInput input)
    {
        Item depot = findDepot(gatherer);
        Item player = findPlayer(gatherer);
        ResourceType resourceProperty = getResourceProperty(resource);
        Identifier gatherAnimation = getGatherAnimation(resourceProperty);
        Identifier gatherSound = getGatherSound(resourceProperty);
        Identifier depositAnimation = getDepositAnimation(resourceProperty);
        return gather(gatherer, resource, player, depot, resourceProperty, gatherAnimation, gatherSound, depositAnimation);
    }

    private Action gather(Item gatherer, Item resource, Item player, Item depot, ItemProperty property,
                          Identifier gatherAnimation, Identifier gatherSound, Identifier depositAnimation)
    {
        Action moveToResource = moveActionProvider.get(gatherer, resource);
        Action transfer = obtain(resource, gatherer, property, gatherAnimation, gatherSound);
        Action moveToDepot = moveActionProvider.get(gatherer, depot);
        Action deposit = resourceTransfer(gatherer, player, property, depositAnimation, null);
        Action gather = new SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action obtain(Item resource, Item gatherer, ItemProperty type, Identifier animation, Identifier sound)
    {
        Action deselect = selectionActionProvider.get(resource, false);
        Action resourceTake = resourceTakeAnimated(resource, type, animation);
        Action resourceReceive = resourceReceiveAnimated(gatherer, type, animation, sound);
        return new ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action deposit(Item source, Item destination, ItemProperty resource, Identifier animation, Identifier sound)
    {
        Action deselect = selectionActionProvider.get(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, animation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, animation, sound);
        return new ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action resourceTakeAnimated(Item item, ItemProperty resourceProperty, Identifier animation)
    {
        Action animateBefore = animateActionProvider.get(item, animation);
        Action gather = resourceTake(item, resourceProperty);
        Action animateAfter = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceReceiveAnimated(Item item, ItemProperty resource, Identifier animation, Identifier sound)
    {
        Action animateBefore = animateActionProvider.get(item, animation);
        Action gather = resourceReceive(item, resource, sound);
        Action animateAfter = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
    }

    private Action resourceReceive(Item item, ItemProperty resource, Identifier sound)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(1f);
        Action gatherResource = new ModifyAction(item, resource, modifier, duration);
        Action gatherSound = audioActionProvider.get(item, sound);
        Action gather = new ParallelAction(gatherResource, gatherSound);
        return new RepeatedAction(gather, 10);
    }

    private Item findDepot(Item item)
    {
        ItemGroup player = item.getParent();
        Predicate<Item> ownedDepots = itemWithType(new Identifier("TownHall"));
        Comparator<Item> closestDepot = closestItem(item);
        Collection<Item> depots = player.findAll(ownedDepots);
        return Collections.min(depots, closestDepot);
    }

    private Item findPlayer(Item item)
    {
        return item.getParent();
    }

    private ResourceType getResourceProperty(Item resource)
    {
        return ResourceType.valueOf(resource.getType().toString());
    }

    private Identifier getGatherAnimation(ResourceType resource)
    {
        return new Identifier("Gather" + resource.toString());
    }

    private Identifier getGatherSound(ResourceType resource)
    {
        return new Identifier("Gather" + resource.toString());
    }

    private Identifier getDepositAnimation(ResourceType resource)
    {
        return new Identifier("Deposit" + resource.toString());
    }

    */
}