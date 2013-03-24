package com.littleGreenMan.Pong_Madness.model;

import com.littleGreenMan.Pong_Madness.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table PLAYER_TOURNEMENT.
 */
public class PlayerTournement {

    private Long identifier;
    private Long playerId;
    private Long tournementId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PlayerTournementDao myDao;

    private Player player;
    private Long player__resolvedKey;

    private Tournement tournement;
    private Long tournement__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public PlayerTournement() {
    }

    public PlayerTournement(Long identifier) {
        this.identifier = identifier;
    }

    public PlayerTournement(Long identifier, Long playerId, Long tournementId) {
        this.identifier = identifier;
        this.playerId = playerId;
        this.tournementId = tournementId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlayerTournementDao() : null;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournementId() {
        return tournementId;
    }

    public void setTournementId(Long tournementId) {
        this.tournementId = tournementId;
    }

    /** To-one relationship, resolved on first access. */
    public Player getPlayer() {
        Long __key = this.playerId;
        if (player__resolvedKey == null || !player__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            Player playerNew = targetDao.load(__key);
            synchronized (this) {
                player = playerNew;
            	player__resolvedKey = __key;
            }
        }
        return player;
    }

    public void setPlayer(Player player) {
        synchronized (this) {
            this.player = player;
            playerId = player == null ? null : player.getIdentifier();
            player__resolvedKey = playerId;
        }
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
