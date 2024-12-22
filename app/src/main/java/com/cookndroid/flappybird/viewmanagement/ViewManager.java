package com.cookndroid.flappybird.viewmanagement;

import android.app.Activity;
import android.view.ViewGroup;

import com.cookndroid.flappybird.gameview.GameView;

import java.util.HashMap;

public class ViewManager
{
    private static ViewManager instance;
    public static ViewManager getInstance()
    {
        if (instance == null)
            instance = new ViewManager();
        return instance;
    }

    private Activity currentActivity = null;
    private HashMap<String, GameView> viewDictionary = new HashMap<>();
    private GameView currentView;

    public void setActivity(Activity activity) { currentActivity = activity;}
    public void addView(String viewName, GameView view)
    {
        if(viewDictionary.containsKey(viewName))
            return;

        viewDictionary.put(viewName, view);
    }
    public void changeView(String viewName)
    {
        changeView(viewName, null);
    }
    public void changeView(String viewName, ViewParam viewParam)
    {
        if(!viewDictionary.containsKey(viewName))
            return;

        if(currentView != null)
        {
            currentView.stop();
            if(currentView.getParent() != null)
                ((ViewGroup)currentView.getParent()).removeView(currentView);
        }

        currentView = viewDictionary.get(viewName);
        currentView.setViewParam(viewParam);
        currentView.start();
        currentActivity.setContentView(currentView);
    }
}
