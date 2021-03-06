/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface represent an object whose presentation can be
 * magnified. Methods are provided to set and retrieve the magnification applied
 * to the object.
 *
 * @author Blair Butterworth
 */
public interface Zoomable
{
    float getZoom();

    float getOriginalZoom();

    void setZoom(float zoom);

    void setOriginalZoom(float originalZoom);
}
