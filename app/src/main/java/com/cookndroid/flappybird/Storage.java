package com.cookndroid.flappybird;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final Context context;

    public Storage(Context context)
    {
        this.context = context;
        initializeHighScoreFile();
    }

    private void initializeHighScoreFile()
    {
        File file = new File(context.getFilesDir(), Config.HIGH_SCORE_TIME);
        if (!file.exists())
        {
            try (FileWriter writer = new FileWriter(file))
            {
                writer.write("0");
            }
            catch (IOException e) { }
        }
    }

    public int readHighScore()
    {
        File file = new File(context.getFilesDir(), Config.HIGH_SCORE_TIME);
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            return Integer.parseInt(reader.readLine());
        }
        catch (IOException | NumberFormatException e)
        {
            return 0;
        }
    }

    public void writeHighScore(int highScore)
    {
        File file = new File(context.getFilesDir(), Config.HIGH_SCORE_TIME);
        try (FileWriter writer = new FileWriter(file))
        {
            writer.write(String.valueOf(highScore));
        }
        catch (IOException e) { }
    }
}
