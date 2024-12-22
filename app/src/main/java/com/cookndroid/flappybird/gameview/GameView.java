package com.cookndroid.flappybird.gameview;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.cookndroid.flappybird.Config;
import com.cookndroid.flappybird.viewmanagement.ViewParam;

public abstract class GameView<T extends ViewParam> extends View
{
    private final Handler handler;
    private final Runnable updateRunnable;
    private ViewParam viewParam;

    private boolean isStopped;

    public void setViewParam(ViewParam viewParam) { this.viewParam = viewParam; }
    protected T getViewParam() { return (T)viewParam; }

    public GameView(Context context)
    {
        super(context);
        isStopped = false;
        handler = new Handler();
        updateRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                if(isStopped)
                    return;

                update();
                invalidate();
                handler.postDelayed(this, 1000 / Config.FPS);
            }
        };
    }
    public void start()
    {
        init();
        isStopped = false;
        handler.post(updateRunnable);
    }
    public void stop()
    {
        isStopped = true;
        handler.removeCallbacks(updateRunnable);
    }

    public abstract void init();
    public abstract void update();
    // render()는 GameEngine에서 invalidate();로 onDraw()를 호출하기 때문에 구현이 불필요함
}
