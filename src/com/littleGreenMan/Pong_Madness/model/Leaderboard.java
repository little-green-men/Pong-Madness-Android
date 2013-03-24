package com.littleGreenMan.Pong_Madness.model;

import java.util.List;
import com.littleGreenMan.Pong_Madness.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table LEADERBOARD.
 */
public class Leaderboard {

    private long identifier;
    private String type;
    private Long tournementId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient LeaderboardDao myDao;

    private Tournement tournement;
    private Long tournement__resolvedKey;

    private List<LeaderboardPlayer> leaderboardPlayerList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Leaderboard() {
    }

    public Leaderboard(long identifier) {
        this.identifier = identifier;
    }

    public Leaderboard(long identifier, String type, Long tournementId) {
        this.identifier = identifier;
        this.type = type;
        this.tournementId = tournementId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLeaderboardDao() : null;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTournementId() {
        return tournementId;
    }

    public void setTournementId(Long tournementId) {
        this.tournementId = tournementId;
    }

    /** To-one relationship, resolved on first access. */
    public Tournement getTournement() {
        Long __key = this.tournementId;
        if (tournement__resolvedKey == null || !tournement__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TournementDao targetDao = daoSession.getTournementDao();
            Tournement tournementNew = targetDao.load(__key);
            synchronized (this) {
                tournement = tournementNew;
            	tournement__resolvedKey = __key;
            }
        }
        return tournement;
    }

    public void setTournement(Tournement tournement) {
        synchronized (this) {
            this.tournement = tournement;
            tournementId = tournement == null ? null : tournement.getIdentifier();
            tournement__resolvedKey = tournementId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<LeaderboardPlayer> getLeaderboardPlayerList() {
        if (leaderboardPlayerList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeaderboardPlayerDao targetDao = daoSession.getLeaderboardPlayerDao();
            List<LeaderboardPlayer> leaderboardPlayerListNew = targetDao._queryLeaderboard_LeaderboardPlayerList(identifier);
            synchronized (this) {
                if(leaderboardPlayerList == null) {
                    leaderboardPlayerList = leaderboardPlayerListNew;
                }
            }
        }
        return leaderboardPlayerList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetLeaderboardPlayerList() {
        leaderboardPlayerList = null;
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