package com.cookndroid.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bird {
    private int x, y;
    private final int initialY;
    private final int width;
    private final int height;
    private float velocity;
    private final float gravity = 2.0f;

    private Bitmap birdBitmap;


    public Bird(Context context, int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.initialY = y;
        this.width = width;
        this.height = height;
        this.velocity = 0;
        this.birdBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
    }

    public void resetPosition()
    {
        this.y = initialY;
        this.velocity = 0;
    }

    public void flap()
    {
        this.velocity = -30;
        SoundManager.playOneShot(eSoundEffect.WING);
    }
    public void applyGravity()
    {
        this.velocity += gravity;
        this.y += velocity;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(birdBitmap, x - width / 2, y - height / 2, null);
    }
    public float getY() { return y; }
    public boolean isPass(Pipe pipe)
    {
        return pipe.getX() + pipe.getWidth() < x;
    }
    public boolean isCollide(Pipe pipe)
    {
        return pipe.getX() < x + width && x < pipe.getX() + pipe.getWidth() && (y < pipe.getY() || pipe.getY() + pipe.getGap() < y + height);
    }
}