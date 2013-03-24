package com.littleGreenMan.Pong_Madness.model;

import java.util.List;
import com.littleGreenMan.Pong_Madness.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table PLAYER.
 */
public class Player {

    private long identifier;
    private Boolean active;
    private String email;
    private String handedness;
    private String photo;
    private java.util.Date sinceDate;
    private String userName;
    private Long teamId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PlayerDao myDao;

    private Team team;
    private Long team__resolvedKey;

    private List<LeaderboardPlayer> leaderboardPlayerList;
    private List<PlayerTournement> playerTournementList;
    private List<PlayerBinome> playerBinomeList;
    private List<SimpleParticipant> simpleParticipantList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Player() {
    }

    public Player(long identifier) {
        this.identifier = identifier;
    }

    public Player(long identifier, Boolean active, String email, String handedness, String photo, java.util.Date sinceDate, String userName, Long teamId) {
        this.identifier = identifier;
        this.active = active;
        this.email = email;
        this.handedness = handedness;
        this.photo = photo;
        this.sinceDate = sinceDate;
        this.userName = userName;
        this.teamId = teamId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlayerDao() : null;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandedness() {
        return handedness;
    }

    public void setHandedness(String handedness) {
        this.handedness = handedness;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public java.util.Date getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(java.util.Date sinceDate) {
        this.sinceDate = sinceDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /** To-one relationship, resolved on first access. */
    public Team getTeam() {
        Long __key = this.teamId;
        if (team__resolvedKey == null || !team__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team teamNew = targetDao.load(__key);
            synchronized (this) {
                team = teamNew;
            	team__resolvedKey = __key;
            }
        }
        return team;
    }

    public void setTeam(Team team) {
        synchronized (this) {
            this.team = team;
            teamId = team == null ? null : team.getIdentifier();
            team__resolvedKey = teamId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<LeaderboardPlayer> getLeaderboardPlayerList() {
        if (leaderboardPlayerList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeaderboardPlayerDao targetDao = daoSession.getLeaderboardPlayerDao();
            List<LeaderboardPlayer> leaderboardPlayerListNew = targetDao._queryPlayer_LeaderboardPlayerList(identifier);
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<PlayerTournement> getPlayerTournementList() {
        if (playerTournementList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerTournementDao targetDao = daoSession.getPlayerTournementDao();
            List<PlayerTournement> playerTournementListNew = targetDao._queryPlayer_PlayerTournementList(identifier);
            synchronized (this) {
                if(playerTournementList == null) {
                    playerTournementList = playerTournementListNew;
                }
            }
        }
        return playerTournementList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetPlayerTournementList() {
        playerTournementList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<PlayerBinome> getPlayerBinomeList() {
        if (playerBinomeList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerBinomeDao targetDao = daoSession.getPlayerBinomeDao();
            List<PlayerBinome> playerBinomeListNew = targetDao._queryPlayer_PlayerBinomeList(identifier);
            synchronized (this) {
                if(playerBinomeList == null) {
                    playerBinomeList = playerBinomeListNew;
                }
            }
        }
        return playerBinomeList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetPlayerBinomeList() {
        playerBinomeList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<SimpleParticipant> getSimpleParticipantList() {
        if (simpleParticipantList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SimpleParticipantDao targetDao = daoSession.getSimpleParticipantDao();
            List<SimpleParticipant> simpleParticipantListNew = targetDao._queryPlayer_SimpleParticipantList(identifier);
            synchronized (this) {
                if(simpleParticipantList == null) {
                    simpleParticipantList = simpleParticipantListNew;
                }
            }
        }
        return simpleParticipantList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetSimpleParticipantList() {
        simpleParticipantList = null;
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