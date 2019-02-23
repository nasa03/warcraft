/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.building;

import com.evilbird.engine.common.control.GridPane;
import com.evilbird.engine.common.control.TextLabel;
import com.evilbird.engine.common.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface show details about a farm.
 *
 * @author Blair Butterworth
 */
public class FarmDetailsPane extends GridPane
{
    private Building building;
    private TextLabel grown;
    private TextLabel used;

    public FarmDetailsPane() {
        super(1, 3);

        grown = createLabel("Grown");
        used = createLabel("Used");

        setSize(160, 100);
        setCellSpacing(4);
        setCell(createLabel("Food Usage"), 0, 0);
        setCell(grown, 0, 1);
        setCell(used, 0, 2);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {

        super.update(delta);
    }

    private String getText(String prefix, float suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return stringBuilder.toString();
    }

    private TextLabel createLabel(String text) {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(160, 12);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
