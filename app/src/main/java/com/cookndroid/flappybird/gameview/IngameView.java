package com.cookndroid.flappybird.gameview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.cookndroid.flappybird.Bird;
import com.cookndroid.flappybird.Pipe;
import com.cookndroid.flappybird.PipePool;
import com.cookndroid.flappybird.R;
import com.cookndroid.flappybird.SoundManager;
import com.cookndroid.flappybird.eSoundEffect;
import com.cookndroid.flappybird.viewmanagement.ResultViewParam;
import com.cookndroid.flappybird.viewmanagement.ViewManager;

import java.util.ArrayList;
import java.util.Iterator;

public class IngameView extends GameView
{
    private final Bird bird;
    private final  ArrayList<Pipe> pipes;
    private int screenWidth, screenHeight;
    private int pipeSpawnTimer;

    private int score;
    private final Paint scorePaint;
    private Bitmap background;

    private PipePool pipePool;


    public IngameView(Context context)
    {
        super(context);

        bird = new Bird(context, 200, 300, 34, 24);
        pipes = new ArrayList<>();

        scorePaint = new Paint();
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(80);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    }

    @Override
    public void init()
    {
        bird.resetPosition();
        pipes.clear();
        pipeSpawnTimer = 0;
        score = 0;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight, true);
        pipePool = new PipePool(5, getContext(), screenWidth, screenHeight);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            bird.flap();

        return true;
    }

    @Override
    public void update()
    {
        boolean isGameOver = false;
        bird.applyGravity();

        isGameOver = bird.getY() < 0 || bird.getY() > screenHeight;

        pipeSpawnTimer++;
        if (pipeSpawnTimer >= 100)
        {
            pipes.add(pipePool.getPipe());
            pipeSpawnTimer = 0;
        }

        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext())
        {
            Pipe pipe = iterator.next();
            pipe.update();

            if (!pipe.isScored() && bird.isPass(pipe)) {
                score++;
                pipe.setScored(true);
                SoundManager.playOneShot(eSoundEffect.POINT);
            }

            isGameOver |= bird.isCollide(pipe);

            if (pipe.isOffScreen())
            {
                iterator.remove();
                pipePool.returnPipe(pipe);
            }
        }

        if(isGameOver)
            ViewManager.getInstance().changeView("RESULT", new ResultViewParam(score));
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawBitmap(background, 0, 0, null);

        bird.draw(canvas);

        for (Pipe pipe : pipes)
            pipe.draw(canvas);

        canvas.drawText("" + score, screenWidth / 2, screenHeight / 6, scorePaint);
    }
}
