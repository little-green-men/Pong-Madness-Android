package com.littleGreenMan.Pong_Madness.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.littleGreenMan.Pong_Madness.model.DoubleGame;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DOUBLE_GAME.
*/
public class DoubleGameDao extends AbstractDao<DoubleGame, Long> {

    public static final String TABLENAME = "DOUBLE_GAME";

    /**
     * Properties of entity DoubleGame.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Identifier = new Property(0, long.class, "identifier", true, "IDENTIFIER");
    };


    public DoubleGameDao(DaoConfig config) {
        super(config);
    }
    
    public DoubleGameDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DOUBLE_GAME' (" + //
                "'IDENTIFIER' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL );"); // 0: identifier
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DOUBLE_GAME'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DoubleGame entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIdentifier());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DoubleGame readEntity(Cursor cursor, int offset) {
        DoubleGame entity = new DoubleGame( //
            cursor.getLong(offset + 0) // identifier
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DoubleGame entity, int offset) {
        entity.setIdentifier(cursor.getLong(offset + 0));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DoubleGame entity, long rowId) {
        entity.setIdentifier(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DoubleGame entity) {
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
