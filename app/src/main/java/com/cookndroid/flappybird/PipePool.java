package com.cookndroid.flappybird;

import android.content.Context;

import java.util.Stack;

public class PipePool {
    private Stack<Pipe> pool;

    public PipePool(int initialSize, Context context, int screenWidth, int screenHeight)
    {
        this.pool = new Stack<>();

        for (int i = 0; i < initialSize; i++)
            pool.push(new Pipe(context,screenWidth, screenHeight));
    }

    public Pipe getPipe()
    {
        if (!pool.isEmpty())
        {
            Pipe pipe = pool.pop();
            pipe.Initialize();
            return pipe;
        }
        return null;
    }

    public void returnPipe(Pipe pipe)
    {
        pool.push(pipe);
    }
}