/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.time;

/**
 * Contains common helper functions for working with Durations.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodName")
public class DurationUtils
{
    /**
     * Disable construction of static utility class.
     */
    private DurationUtils() {
    }

    /**
     * Obtains a {@code Duration} representing the given number of seconds.
     */
    public static Duration Seconds(long seconds) {
        return Duration.ofSeconds(seconds);
    }

    /**
     * Obtains a {@code Duration} representing the given number of seconds.
     */
    //TODO: Extract remainder and give to duration as nanos
    public static Duration Seconds(float seconds) {
        return Duration.ofSeconds((long)seconds);
    }
}
