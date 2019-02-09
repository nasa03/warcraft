/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface represent an object that can navigated
 * through.
 *
 * @author Blair Butterworth
 */
public interface Navigable
{
    void navigate(Identifier location);
}