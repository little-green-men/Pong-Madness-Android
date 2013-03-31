package com.littleGreenMan.Pong_Madness;

import android.app.Application;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PongMadnessApplication extends Application {

    @Override
    public void onCreate() {

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.startPongMadnessDatabase();

        super.onCreate();
    }


    @Override
    public void onTerminate() {
        DatabaseManager.closeDatabase();
        super.onTerminate();
    }
}
