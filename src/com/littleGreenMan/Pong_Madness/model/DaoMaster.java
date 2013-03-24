package com.littleGreenMan.Pong_Madness.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.littleGreenMan.Pong_Madness.model.TournementDao;
import com.littleGreenMan.Pong_Madness.model.LeaderboardDao;
import com.littleGreenMan.Pong_Madness.model.LeaderboardPlayerDao;
import com.littleGreenMan.Pong_Madness.model.PlayerDao;
import com.littleGreenMan.Pong_Madness.model.PlayerTournementDao;
import com.littleGreenMan.Pong_Madness.model.BinomeDao;
import com.littleGreenMan.Pong_Madness.model.PlayerBinomeDao;
import com.littleGreenMan.Pong_Madness.model.GameDao;
import com.littleGreenMan.Pong_Madness.model.DoubleGameDao;
import com.littleGreenMan.Pong_Madness.model.SimpleGameDao;
import com.littleGreenMan.Pong_Madness.model.SimpleParticipantDao;
import com.littleGreenMan.Pong_Madness.model.DoubleParticipantDao;
import com.littleGreenMan.Pong_Madness.model.GameTournementDao;
import com.littleGreenMan.Pong_Madness.model.TeamDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        TournementDao.createTable(db, ifNotExists);
        LeaderboardDao.createTable(db, ifNotExists);
        LeaderboardPlayerDao.createTable(db, ifNotExists);
        PlayerDao.createTable(db, ifNotExists);
        PlayerTournementDao.createTable(db, ifNotExists);
        BinomeDao.createTable(db, ifNotExists);
        PlayerBinomeDao.createTable(db, ifNotExists);
        GameDao.createTable(db, ifNotExists);
        DoubleGameDao.createTable(db, ifNotExists);
        SimpleGameDao.createTable(db, ifNotExists);
        SimpleParticipantDao.createTable(db, ifNotExists);
        DoubleParticipantDao.createTable(db, ifNotExists);
        GameTournementDao.createTable(db, ifNotExists);
        TeamDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        TournementDao.dropTable(db, ifExists);
        LeaderboardDao.dropTable(db, ifExists);
        LeaderboardPlayerDao.dropTable(db, ifExists);
        PlayerDao.dropTable(db, ifExists);
        PlayerTournementDao.dropTable(db, ifExists);
        BinomeDao.dropTable(db, ifExists);
        PlayerBinomeDao.dropTable(db, ifExists);
        GameDao.dropTable(db, ifExists);
        DoubleGameDao.dropTable(db, ifExists);
        SimpleGameDao.dropTable(db, ifExists);
        SimpleParticipantDao.dropTable(db, ifExists);
        DoubleParticipantDao.dropTable(db, ifExists);
        GameTournementDao.dropTable(db, ifExists);
        TeamDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(TournementDao.class);
        registerDaoClass(LeaderboardDao.class);
        registerDaoClass(LeaderboardPlayerDao.class);
        registerDaoClass(PlayerDao.class);
        registerDaoClass(PlayerTournementDao.class);
        registerDaoClass(BinomeDao.class);
        registerDaoClass(PlayerBinomeDao.class);
        registerDaoClass(GameDao.class);
        registerDaoClass(DoubleGameDao.class);
        registerDaoClass(SimpleGameDao.class);
        registerDaoClass(SimpleParticipantDao.class);
        registerDaoClass(DoubleParticipantDao.class);
        registerDaoClass(GameTournementDao.class);
        registerDaoClass(TeamDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
