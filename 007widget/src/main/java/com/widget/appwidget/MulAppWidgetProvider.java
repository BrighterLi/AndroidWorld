package com.widget.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Implementation of App Widget functionality.
 */
public class MulAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("onReceive");
        Log.i("MulAppWidgetProvider", "onReceive");
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // TODO Auto-generated method stub
        System.out.println("onUpdate");
        Log.i("MulAppWidgetProvider", "onUpdate");

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // TODO Auto-generated method stub
        System.out.println("onDeleted");
        Log.i("MulAppWidgetProvider", "onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // TODO Auto-generated method stub
        System.out.println("onEnabled");
        Log.i("MulAppWidgetProvider", "onEnabled");
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        // TODO Auto-generated method stub
        System.out.println("onDisabled");
        Log.i("MulAppWidgetProvider", "onDisabled");
        super.onDisabled(context);
    }

}

