package com.cookndroid.flappybird;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Pipe {
    private int x, y;
    private final int width, gap, screenWidth, screenHeight;
    private boolean isScored;
    private final Bitmap pipeBitmap;
    private final Bitmap pipeBitmap_flipped;
    private final int pipeImageHeight;

    public Pipe(Context context, int screenWidth, int screenHeight) {
        this.width = 138;
        this.gap = 400;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.pipeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe);
        this.pipeImageHeight = pipeBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        this.pipeBitmap_flipped = Bitmap.createBitmap(pipeBitmap, 0, 0, pipeBitmap.getWidth(), pipeBitmap.getHeight(), matrix, true);
    }
    public void Initialize(){
        this.isScored = false;
        this.x = screenWidth;
        this.y = (int) (Math.random() * (screenHeight / 2));
    }
    public void update() { x -= 10; }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(pipeBitmap_flipped, x, -pipeImageHeight + y, null);
        canvas.drawBitmap(pipeBitmap, x, y + gap, null);
    }
    public void setScored(boolean isScored){ this.isScored = isScored; }
    public boolean isOffScreen() { return x + width < 0; }
    public boolean isScored() { return isScored; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getGap() { return gap; }
    public int getWidth() { return width; }
}
