package com.cookndroid.flappybird.gameview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import com.cookndroid.flappybird.R;
import com.cookndroid.flappybird.SoundManager;
import com.cookndroid.flappybird.eSoundEffect;
import com.cookndroid.flappybird.viewmanagement.ViewManager;

public class TitleView extends GameView
{
    private Paint textPaint;
    private Bitmap background;
    private int screenWidth, screenHeight;

    public TitleView(Context context) {
        super(context);

        this.textPaint = new Paint();
        this.textPaint.setColor(Color.BLACK);
        this.textPaint.setTextSize(100);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    }

    @Override
    public void init()
    {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight, true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        ViewManager.getInstance().changeView("GAME");
        SoundManager.playOneShot(eSoundEffect.WING);
        return true;
    }

    @Override
    public void update() { }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("FlappyBird", screenWidth / 2, screenHeight / 4, textPaint);
    }
}
