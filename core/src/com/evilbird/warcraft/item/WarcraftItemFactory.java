package com.evilbird.warcraft.item;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.data.DataProvider;
import com.evilbird.warcraft.item.hud.HudControlProvider;
import com.evilbird.warcraft.item.layer.LayerProvider;
import com.evilbird.warcraft.item.unit.UnitProvider;

import javax.inject.Inject;

public class WarcraftItemFactory implements ItemFactory
{
    private IdentifiedAssetProviderSet<Item> providers;

    @Inject
    public WarcraftItemFactory(
        DataProvider dataProvider,
        HudControlProvider hudProvider,
        LayerProvider layerProvider,
        UnitProvider unitProvider)
    {
        providers = new IdentifiedAssetProviderSet<Item>();
        providers.addProvider(unitProvider);
        providers.addProvider(hudProvider);
        providers.addProvider(layerProvider);
        providers.addProvider(dataProvider);
    }

    @Override
    public void load()
    {
        providers.load();
    }

    @Override
    public Item newItem(ItemType type)
    {
        Item result = providers.get(type);
        if (result == null){
            throw new IllegalArgumentException();
        }
        return result;
    }
}
