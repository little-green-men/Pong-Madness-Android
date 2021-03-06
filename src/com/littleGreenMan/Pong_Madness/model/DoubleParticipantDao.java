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
 * DAO for table DOUBLE_PARTICIPANT.
*/
public class DoubleParticipantDao extends AbstractDao<DoubleParticipant, Long> {

    public static final String TABLENAME = "DOUBLE_PARTICIPANT";

    /**
     * Properties of entity DoubleParticipant.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Score = new Property(1, Integer.class, "score", false, "SCORE");
        public final static Property DoubleGameId = new Property(2, Long.class, "doubleGameId", false, "DOUBLE_GAME_ID");
        public final static Property PbinomeId = new Property(3, Long.class, "pbinomeId", false, "PBINOME_ID");
    };

    private DaoSession daoSession;

    private Query<DoubleParticipant> binome_DoubleParticipantListQuery;

    public DoubleParticipantDao(DaoConfig config) {
        super(config);
    }
    
    public DoubleParticipantDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DOUBLE_PARTICIPANT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'SCORE' INTEGER," + // 1: score
                "'DOUBLE_GAME_ID' INTEGER," + // 2: doubleGameId
                "'PBINOME_ID' INTEGER);"); // 3: pbinomeId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DOUBLE_PARTICIPANT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DoubleParticipant entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer score = entity.getScore();
        if (score != null) {
            stmt.bindLong(2, score);
        }
 
        Long doubleGameId = entity.getDoubleGameId();
        if (doubleGameId != null) {
            stmt.bindLong(3, doubleGameId);
        }
 
        Long pbinomeId = entity.getPbinomeId();
        if (pbinomeId != null) {
            stmt.bindLong(4, pbinomeId);
        }
    }

    @Override
    protected void attachEntity(DoubleParticipant entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DoubleParticipant readEntity(Cursor cursor, int offset) {
        DoubleParticipant entity = new DoubleParticipant( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // score
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // doubleGameId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // pbinomeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DoubleParticipant entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setScore(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDoubleGameId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setPbinomeId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DoubleParticipant entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DoubleParticipant entity) {
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
    
    /** Internal query to resolve the "doubleParticipantList" to-many relationship of Binome. */
    public List<DoubleParticipant> _queryBinome_DoubleParticipantList(Long pbinomeId) {
        synchronized (this) {
            if (binome_DoubleParticipantListQuery == null) {
                QueryBuilder<DoubleParticipant> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PbinomeId.eq(null));
                binome_DoubleParticipantListQuery = queryBuilder.build();
            }
        }
        Query<DoubleParticipant> query = binome_DoubleParticipantListQuery.forCurrentThread();
        query.setParameter(0, pbinomeId);
        return query.list();
    }

}
