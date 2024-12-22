package com.cookndroid.flappybird;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import java.util.HashMap;


public class SoundManager
{
    private static SoundPool soundPool;
    private static HashMap<eSoundEffect,Integer> soundMap;
    private static boolean isInitialized = false;

    public static void Init(Context context)
    {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();

        soundMap = new HashMap<>(3);
        soundMap.put(eSoundEffect.DIE, soundPool.load(context, R.raw.die, 1));
        soundMap.put(eSoundEffect.POINT, soundPool.load(context, R.raw.point, 1));
        soundMap.put(eSoundEffect.WING, soundPool.load(context, R.raw.wing, 1));

        isInitialized = true;
    }

    public static void playOneShot(eSoundEffect effect)
    {
        if (isInitialized && soundMap.containsKey(effect))
            soundPool.play(soundMap.get(effect), 1, 1, 1, 0, 1f);
    }
}