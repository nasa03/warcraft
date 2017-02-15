package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemGroup;

import java.util.List;

public class CompositeBehaviour implements Behaviour
{
    private List<Behaviour> behaviours;

    public CompositeBehaviour(List<Behaviour> behaviours)
    {
        this.behaviours = behaviours;
    }

    @Override
    public void update(ItemGroup world, ItemGroup hud, List<UserInput> input)
    {
        for (Behaviour behaviour: behaviours)
        {
            behaviour.update(world, hud, input);
        }
    }
}
