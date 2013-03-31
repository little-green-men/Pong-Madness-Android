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
 * DAO for table SIMPLE_PARTICIPANT.
*/
public class SimpleParticipantDao extends AbstractDao<SimpleParticipant, Long> {

    public static final String TABLENAME = "SIMPLE_PARTICIPANT";

    /**
     * Properties of entity SimpleParticipant.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Score = new Property(1, Integer.class, "score", false, "SCORE");
        public final static Property SimpleGameId = new Property(2, Long.class, "simpleGameId", false, "SIMPLE_GAME_ID");
        public final static Property PlayerId = new Property(3, Long.class, "playerId", false, "PLAYER_ID");
    };

    private Query<SimpleParticipant> player_SimpleParticipantListQuery;
    private Query<SimpleParticipant> simpleGame_SimpleParticipantListQuery;

    public SimpleParticipantDao(DaoConfig config) {
        super(config);
    }
    
    public SimpleParticipantDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SIMPLE_PARTICIPANT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'SCORE' INTEGER," + // 1: score
                "'SIMPLE_GAME_ID' INTEGER," + // 2: simpleGameId
                "'PLAYER_ID' INTEGER);"); // 3: playerId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SIMPLE_PARTICIPANT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SimpleParticipant entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer score = entity.getScore();
        if (score != null) {
            stmt.bindLong(2, score);
        }
 
        Long simpleGameId = entity.getSimpleGameId();
        if (simpleGameId != null) {
            stmt.bindLong(3, simpleGameId);
        }
 
        Long playerId = entity.getPlayerId();
        if (playerId != null) {
            stmt.bindLong(4, playerId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SimpleParticipant readEntity(Cursor cursor, int offset) {
        SimpleParticipant entity = new SimpleParticipant( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // score
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // simpleGameId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // playerId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SimpleParticipant entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setScore(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setSimpleGameId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setPlayerId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SimpleParticipant entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SimpleParticipant entity) {
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
    
    /** Internal query to resolve the "simpleParticipantList" to-many relationship of Player. */
    public List<SimpleParticipant> _queryPlayer_SimpleParticipantList(Long playerId) {
        synchronized (this) {
            if (player_SimpleParticipantListQuery == null) {
                QueryBuilder<SimpleParticipant> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PlayerId.eq(null));
                player_SimpleParticipantListQuery = queryBuilder.build();
            }
        }
        Query<SimpleParticipant> query = player_SimpleParticipantListQuery.forCurrentThread();
        query.setParameter(0, playerId);
        return query.list();
    }

    /** Internal query to resolve the "simpleParticipantList" to-many relationship of SimpleGame. */
    public List<SimpleParticipant> _querySimpleGame_SimpleParticipantList(Long simpleGameId) {
        synchronized (this) {
            if (simpleGame_SimpleParticipantListQuery == null) {
                QueryBuilder<SimpleParticipant> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.SimpleGameId.eq(null));
                simpleGame_SimpleParticipantListQuery = queryBuilder.build();
            }
        }
        Query<SimpleParticipant> query = simpleGame_SimpleParticipantListQuery.forCurrentThread();
        query.setParameter(0, simpleGameId);
        return query.list();
    }

}
