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
 * DAO for table GAME_TOURNEMENT.
*/
public class GameTournementDao extends AbstractDao<GameTournement, Long> {

    public static final String TABLENAME = "GAME_TOURNEMENT";

    /**
     * Properties of entity GameTournement.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GameId = new Property(1, Long.class, "gameId", false, "GAME_ID");
        public final static Property TournementId = new Property(2, Long.class, "tournementId", false, "TOURNEMENT_ID");
    };

    private Query<GameTournement> tournement_GameTournementListQuery;
    private Query<GameTournement> game_GameTournementListQuery;

    public GameTournementDao(DaoConfig config) {
        super(config);
    }
    
    public GameTournementDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GAME_TOURNEMENT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'GAME_ID' INTEGER," + // 1: gameId
                "'TOURNEMENT_ID' INTEGER);"); // 2: tournementId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GAME_TOURNEMENT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GameTournement entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long gameId = entity.getGameId();
        if (gameId != null) {
            stmt.bindLong(2, gameId);
        }
 
        Long tournementId = entity.getTournementId();
        if (tournementId != null) {
            stmt.bindLong(3, tournementId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GameTournement readEntity(Cursor cursor, int offset) {
        GameTournement entity = new GameTournement( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // gameId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // tournementId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GameTournement entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGameId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTournementId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GameTournement entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GameTournement entity) {
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
    
    /** Internal query to resolve the "gameTournementList" to-many relationship of Tournement. */
    public List<GameTournement> _queryTournement_GameTournementList(Long tournementId) {
        synchronized (this) {
            if (tournement_GameTournementListQuery == null) {
                QueryBuilder<GameTournement> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TournementId.eq(null));
                tournement_GameTournementListQuery = queryBuilder.build();
            }
        }
        Query<GameTournement> query = tournement_GameTournementListQuery.forCurrentThread();
        query.setParameter(0, tournementId);
        return query.list();
    }

    /** Internal query to resolve the "gameTournementList" to-many relationship of Game. */
    public List<GameTournement> _queryGame_GameTournementList(Long gameId) {
        synchronized (this) {
            if (game_GameTournementListQuery == null) {
                QueryBuilder<GameTournement> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.GameId.eq(null));
                game_GameTournementListQuery = queryBuilder.build();
            }
        }
        Query<GameTournement> query = game_GameTournementListQuery.forCurrentThread();
        query.setParameter(0, gameId);
        return query.list();
    }

}
