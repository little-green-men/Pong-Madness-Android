package com.littleGreenMan.Pong_Madness.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.littleGreenMan.Pong_Madness.model.DaoMaster;
import com.littleGreenMan.Pong_Madness.model.DaoSession;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseManager {
    private static String TAG = "DATABASE_MANAGER";
    private static Context context;
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    protected static DaoSession daoSession;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public static void startPongMadnessDatabase() {

        // Instantiate database relative
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,
                getDatabaseName(), null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        Log.i(TAG, "Database Path : " + context.getDatabasePath(getDatabaseName()));

        // Delete database if the current application version is not the one in database
        if (!checkDatabaseVersion()) {
            context.deleteDatabase(getDatabaseName());

            Log.i(TAG, "Wrong database version : delete and recreate");

            // Instantiate database relative
            helper = new DaoMaster.DevOpenHelper(context, getDatabaseName(), null);
            db = helper.getWritableDatabase();

            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();

            Log.i(TAG, "Database Path : " + context.getDatabasePath(getDatabaseName()));

            // Update newly created database
            updateCurrentDatabaseVersion();
        }
    }

    private static void updateCurrentDatabaseVersion() {
    }

    protected static String getDatabaseName() {
        return "pongMadness-db";
    }

    protected static boolean checkDatabaseVersion() {
        // TODO make this abstract
        return true;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }


    public static void closeDatabase() {
        db.close();
    }

}
