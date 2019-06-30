/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Air;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;

/**
 * Instances of this factory create Gryphon Riders, Human heavy flying
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GryphonRiderFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(0, 228);
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public GryphonRiderFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GryphonRiderFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, GryphonRider, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(8);
        result.setDamageMaximum(16);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GryphonRider", result));
        result.setLevel(1);
        result.setName("Gryphon Rider");
        result.setMovementSpeed(8 * 14);
        result.setMovementCapability(Air);
        result.setRange(tiles(4));
        result.setSight(tiles(6));
        result.setType(GryphonRider);
        result.setProjectileType(Lightning);
        return result;
    }
}