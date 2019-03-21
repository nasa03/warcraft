/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

//TODO: Replace with Java Comparator now that Android SDK has been bumped
public interface Comparator<T>
{
    int compare(T var1, T var2);
}
