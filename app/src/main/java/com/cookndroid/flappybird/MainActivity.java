package com.cookndroid.flappybird;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.cookndroid.flappybird.gameview.IngameView;
import com.cookndroid.flappybird.gameview.ResultView;
import com.cookndroid.flappybird.gameview.TitleView;
import com.cookndroid.flappybird.viewmanagement.ViewManager;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SoundManager.Init(this);
        ViewManager.getInstance().setActivity(this);
        ViewManager.getInstance().addView("TITLE", new TitleView(this));
        ViewManager.getInstance().addView("GAME", new IngameView(this));
        ViewManager.getInstance().addView("RESULT", new ResultView(this));
        ViewManager.getInstance().changeView("TITLE");
    }
}
