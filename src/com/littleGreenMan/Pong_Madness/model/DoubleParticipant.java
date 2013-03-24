package com.littleGreenMan.Pong_Madness.model;

import java.util.List;
import com.littleGreenMan.Pong_Madness.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table DOUBLE_PARTICIPANT.
 */
public class DoubleParticipant {

    private long identifier;
    private Integer score;
    private Long doubleGameId;
    private Long pbinomeId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DoubleParticipantDao myDao;

    private List<Binome> doubleParticipantList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public DoubleParticipant() {
    }

    public DoubleParticipant(long identifier) {
        this.identifier = identifier;
    }

    public DoubleParticipant(long identifier, Integer score, Long doubleGameId, Long pbinomeId) {
        this.identifier = identifier;
        this.score = score;
        this.doubleGameId = doubleGameId;
        this.pbinomeId = pbinomeId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDoubleParticipantDao() : null;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getDoubleGameId() {
        return doubleGameId;
    }

    public void setDoubleGameId(Long doubleGameId) {
        this.doubleGameId = doubleGameId;
    }

    public Long getPbinomeId() {
        return pbinomeId;
    }

    public void setPbinomeId(Long pbinomeId) {
        this.pbinomeId = pbinomeId;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Binome> getDoubleParticipantList() {
        if (doubleParticipantList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BinomeDao targetDao = daoSession.getBinomeDao();
            List<Binome> doubleParticipantListNew = targetDao._queryDoubleParticipant_DoubleParticipantList(identifier);
            synchronized (this) {
                if(doubleParticipantList == null) {
                    doubleParticipantList = doubleParticipantListNew;
                }
            }
        }
        return doubleParticipantList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetDoubleParticipantList() {
        doubleParticipantList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
