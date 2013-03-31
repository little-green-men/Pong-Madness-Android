package com.littleGreenMan.Pong_Madness.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PLAYER_BINOME.
*/
public class PlayerBinomeDao extends AbstractDao<PlayerBinome, Long> {

    public static final String TABLENAME = "PLAYER_BINOME";

    /**
     * Properties of entity PlayerBinome.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PlayerId = new Property(1, Long.class, "playerId", false, "PLAYER_ID");
        public final static Property BinomeId = new Property(2, Long.class, "binomeId", false, "BINOME_ID");
    };

    private Query<PlayerBinome> player_PlayerBinomeListQuery;
    private Query<PlayerBinome> binome_PlayerBinomeListQuery;

    public PlayerBinomeDao(DaoConfig config) {
        super(config);
    }
    
    public PlayerBinomeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PLAYER_BINOME' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'PLAYER_ID' INTEGER," + // 1: playerId
                "'BINOME_ID' INTEGER);"); // 2: binomeId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PLAYER_BINOME'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PlayerBinome entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long playerId = entity.getPlayerId();
        if (playerId != null) {
            stmt.bindLong(2, playerId);
        }
 
        Long binomeId = entity.getBinomeId();
        if (binomeId != null) {
            stmt.bindLong(3, binomeId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PlayerBinome readEntity(Cursor cursor, int offset) {
        PlayerBinome entity = new PlayerBinome( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // playerId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // binomeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PlayerBinome entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPlayerId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setBinomeId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PlayerBinome entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PlayerBinome entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "playerBinomeList" to-many relationship of Player. */
    public List<PlayerBinome> _queryPlayer_PlayerBinomeList(Long playerId) {
        synchronized (this) {
            if (player_PlayerBinomeListQuery == null) {
                QueryBuilder<PlayerBinome> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PlayerId.eq(null));
                player_PlayerBinomeListQuery = queryBuilder.build();
            }
        }
        Query<PlayerBinome> query = player_PlayerBinomeListQuery.forCurrentThread();
        query.setParameter(0, playerId);
        return query.list();
    }

    /** Internal query to resolve the "playerBinomeList" to-many relationship of Binome. */
    public List<PlayerBinome> _queryBinome_PlayerBinomeList(Long binomeId) {
        synchronized (this) {
            if (binome_PlayerBinomeListQuery == null) {
                QueryBuilder<PlayerBinome> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.BinomeId.eq(null));
                binome_PlayerBinomeListQuery = queryBuilder.build();
            }
        }
        Query<PlayerBinome> query = binome_PlayerBinomeListQuery.forCurrentThread();
        query.setParameter(0, binomeId);
        return query.list();
    }

}
