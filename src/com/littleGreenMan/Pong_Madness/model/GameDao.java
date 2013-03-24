package com.littleGreenMan.Pong_Madness.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.littleGreenMan.Pong_Madness.model.Game;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GAME.
*/
public class GameDao extends AbstractDao<Game, Long> {

    public static final String TABLENAME = "GAME";

    /**
     * Properties of entity Game.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Identifier = new Property(0, long.class, "identifier", true, "IDENTIFIER");
        public final static Property StartDate = new Property(1, java.util.Date.class, "startDate", false, "START_DATE");
        public final static Property TimePlayed = new Property(2, Integer.class, "timePlayed", false, "TIME_PLAYED");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
    };

    private DaoSession daoSession;


    public GameDao(DaoConfig config) {
        super(config);
    }
    
    public GameDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GAME' (" + //
                "'IDENTIFIER' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: identifier
                "'START_DATE' INTEGER," + // 1: startDate
                "'TIME_PLAYED' INTEGER," + // 2: timePlayed
                "'TYPE' TEXT);"); // 3: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GAME'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Game entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIdentifier());
 
        java.util.Date startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(2, startDate.getTime());
        }
 
        Integer timePlayed = entity.getTimePlayed();
        if (timePlayed != null) {
            stmt.bindLong(3, timePlayed);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
    }

    @Override
    protected void attachEntity(Game entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Game readEntity(Cursor cursor, int offset) {
        Game entity = new Game( //
            cursor.getLong(offset + 0), // identifier
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // startDate
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // timePlayed
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // type
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Game entity, int offset) {
        entity.setIdentifier(cursor.getLong(offset + 0));
        entity.setStartDate(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setTimePlayed(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Game entity, long rowId) {
        entity.setIdentifier(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Game entity) {
        if(entity != null) {
            return entity.getIdentifier();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
