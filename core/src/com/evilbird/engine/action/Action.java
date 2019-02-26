/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.ReflectionAdapter;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a self contained "bundle" of behaviour
 * that modify the game state is a meaningful manner.
 *
 * @author Blair Butterworth
 */
public interface Action extends Identifiable, Poolable
{
    /**
     * Updates the Action based on time. Typically this is called each frame by
     * {@link Item#update(float)}.
     *
     * @param delta the number of seconds since the last invocation.
     * @return      <code>true</code> if the action is done.
     */
    boolean act(float delta);

    /**
     * Stops the Action.
     */
    void cancel();

    /**
     * Sets the state of the action so it can be run again.
     */
    void restart();

    /**
     * Returns the {@link Item} that the Action will operate on.
     *
     * @return an Item instance. Will not return <code>null</code>.
     */
    Item getItem();

    /**
     * Returns an optional target that the Action will manipulate.
     *
     * @return an Item instance. May return <code>null</code>.
     */
    Item getTarget();

    /**
     * Returns the {@link UserInput} event that generated the Action. Not all
     * Actions are generated by user interactions so this method should be
     * considered optional.
     *
     * @return a UserInput instance. May be <code>null</code>.
     */
    UserInput getCause();

    /**
     * Returns a possible error generated by the execution of the Action.
     *
     * @return  a ActionException instance. May return <code>null</code> if
     *          execution was successful.
     */
    ActionException getError();

    /**
     * Determines whether or not an error occurred during the execution of the
     * Action.
     *
     * @return <code>true</code> if an error occurred during Action execution.
     */
    boolean hasError();

    /**
     * Sets the {@link Item} that the Action will operate on.
     *
     * @param item an Item instance. Cannot be <code>null</code>.
     */
    void setItem(Item item);

    /**
     * Sets an optional target that the Action will manipulate.
     *
     * @param target an Item instance. May be <code>null</code>.
     */
    void setTarget(Item target);

    /**
     * Sets the {@link ItemComposite} at the root of the item hierarchy.
     * Actions usually maintain references to {@link Item Items}, rather
     * than Items themselves, so this method will update the parent used to
     * resolve item references.
     *
     * @param root an ItemComposite instance. Cannot be <code>null</code>.
     */
    void setRoot(ItemComposite root);

    /**
     * Sets the {@link UserInput} event that generated the Action.
     *
     * @return a UserInput instance. Cannot be <code>null</code>.
     */
    void setCause(UserInput input);

    /**
     * Sets an error generated by the execution of the Action.
     *
     * @return  a ActionException instance. May be <code>null</code>.
     */
    void setError(ActionException error);

    void setIdentifier(Identifier identifier);
}
