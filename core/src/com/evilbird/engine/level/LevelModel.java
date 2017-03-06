package com.evilbird.engine.level;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;

import java.util.List;

import javax.inject.Inject;

public class LevelModel
{
    private Level presenter;
    private Device device;

    private ItemFactory itemFactory;
    private BehaviourFactory behaviourFactory;

    private ItemRoot hud;
    private ItemRoot world;
    private Behaviour behaviour;

    @Inject
    public LevelModel(
        Device device,
        ItemFactory itemFactory,
        BehaviourFactory behaviourFactory)
    {
        this.device = device;
        this.itemFactory = itemFactory;
        this.behaviourFactory = behaviourFactory;
    }

    public void setPresenter(Level presenter)
    {
        this.presenter = presenter;
    }

    public void load()
    {
        world = itemFactory.newItemGroup(new Identifier("Level1"));
        hud = itemFactory.newItemGroup(new Identifier("HumanHud"));
        behaviour = behaviourFactory.newBehaviour(null); //TODO Provide meaningful id

        presenter.setHud(hud);
        presenter.setWorld(world);

        device.getDeviceInput().install(); //TODO
    }

    public void update(float delta)
    {
        List<UserInput> inputs = device.getDeviceInput().readInput();
        behaviour.update(world, hud, inputs);
        world.update(delta);
        hud.update(delta);
    }
}