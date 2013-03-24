package com.littleGreenMan.Pong_Madness.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.littleGreenMan.Pong_Madness.model.Team;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TEAM.
*/
public class TeamDao extends AbstractDao<Team, Long> {

    public static final String TABLENAME = "TEAM";

    /**
     * Properties of entity Team.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Identifier = new Property(0, long.class, "identifier", true, "IDENTIFIER");
        public final static Property Logo = new Property(1, String.class, "logo", false, "LOGO");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
    };

    private DaoSession daoSession;


    public TeamDao(DaoConfig config) {
        super(config);
    }
    
    public TeamDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TEAM' (" + //
                "'IDENTIFIER' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: identifier
                "'LOGO' TEXT," + // 1: logo
                "'NAME' TEXT);"); // 2: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TEAM'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Team entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIdentifier());
 
        String logo = entity.getLogo();
        if (logo != null) {
            stmt.bindString(2, logo);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    @Override
    protected void attachEntity(Team entity) {
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
    public Team readEntity(Cursor cursor, int offset) {
        Team entity = new Team( //
            cursor.getLong(offset + 0), // identifier
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // logo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Team entity, int offset) {
        entity.setIdentifier(cursor.getLong(offset + 0));
        entity.setLogo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Team entity, long rowId) {
        entity.setIdentifier(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Team entity) {
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