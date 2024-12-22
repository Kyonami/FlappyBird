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
import com.cookndroid.flappybird.Storage;
import com.cookndroid.flappybird.eSoundEffect;
import com.cookndroid.flappybird.viewmanagement.ResultViewParam;
import com.cookndroid.flappybird.viewmanagement.ViewManager;

public class ResultView extends GameView<ResultViewParam>
{
    private Paint textPaint;
    private Bitmap background;
    private Storage storage;

    private int screenWidth, screenHeight;
    private int score, highScore;

    public ResultView(Context context) {
        super(context);

        this.storage = new Storage(context);
        this.textPaint = new Paint();
        this.textPaint.setColor(Color.RED);
        this.textPaint.setTextSize(100);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    }


    @Override
    public void init()
    {
        score = getViewParam().score;
        highScore = storage.readHighScore();

        if (score > highScore)
        {
            highScore = score;
            storage.writeHighScore(highScore);
        }

        SoundManager.playOneShot(eSoundEffect.DIE);
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
        return true;
    }

    @Override
    public void update() { }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Game Over", screenWidth / 2, screenHeight / 2, textPaint);
        canvas.drawText("Final Score: " + score, screenWidth / 2, screenHeight / 2 + 120, textPaint);
        canvas.drawText("High Score: " + highScore, screenWidth / 2, screenHeight / 2 + 240, textPaint);
        canvas.drawText("Tap to Restart", screenWidth / 2, screenHeight / 2 + 360, textPaint);
    }
}
