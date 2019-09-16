/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.music;

import com.badlogic.gdx.audio.Music;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a audio stream comprised of individual audio streams played in
 * parallel. Methods are provided to play, pause and stop the music combination,
 * as well as to configure its playback.
 *
 * @author Blair Butterworth
 */
public class MusicCombination implements Music
{
    private float volume;
    private boolean playing;
    private List<Music> combination;

    public MusicCombination(Music ... combination) {
        this(Arrays.asList(combination));
    }

    public MusicCombination(List<Music> combination) {
        this.combination = combination;
        this.volume = 1f;
    }

    @Override
    public void play() {
        if (! playing) {
            playing = true;
            for (Music music: combination) {
                music.play();
                music.setVolume(volume);
            }
        }
    }

    @Override
    public void pause() {
        playing = false;
        for (Music music: combination) {
            music.pause();
        }
    }

    @Override
    public void stop() {
        playing = false;
        for (Music music: combination) {
            music.stop();
        }
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void dispose() {
        for (Music music: combination) {
            music.dispose();
        }
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public void setVolume(float volume) {
        Validate.inclusiveBetween(0, 1, volume);
        this.volume = volume;
        for (Music music: combination) {
            music.setVolume(volume);
        }
    }

    @Override
    public void setLooping(boolean isLooping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLooping() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPan(float pan, float volume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPosition(float position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        throw new UnsupportedOperationException();
    }
}