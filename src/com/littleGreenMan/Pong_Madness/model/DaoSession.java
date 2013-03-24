package com.littleGreenMan.Pong_Madness.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.littleGreenMan.Pong_Madness.model.Tournement;
import com.littleGreenMan.Pong_Madness.model.Leaderboard;
import com.littleGreenMan.Pong_Madness.model.LeaderboardPlayer;
import com.littleGreenMan.Pong_Madness.model.Player;
import com.littleGreenMan.Pong_Madness.model.PlayerTournement;
import com.littleGreenMan.Pong_Madness.model.Binome;
import com.littleGreenMan.Pong_Madness.model.PlayerBinome;
import com.littleGreenMan.Pong_Madness.model.Game;
import com.littleGreenMan.Pong_Madness.model.DoubleGame;
import com.littleGreenMan.Pong_Madness.model.SimpleGame;
import com.littleGreenMan.Pong_Madness.model.SimpleParticipant;
import com.littleGreenMan.Pong_Madness.model.DoubleParticipant;
import com.littleGreenMan.Pong_Madness.model.GameTournement;
import com.littleGreenMan.Pong_Madness.model.Team;

import com.littleGreenMan.Pong_Madness.model.TournementDao;
import com.littleGreenMan.Pong_Madness.model.LeaderboardDao;
import com.littleGreenMan.Pong_Madness.model.LeaderboardPlayerDao;
import com.littleGreenMan.Pong_Madness.model.PlayerDao;
import com.littleGreenMan.Pong_Madness.model.PlayerTournementDao;
import com.littleGreenMan.Pong_Madness.model.BinomeDao;
import com.littleGreenMan.Pong_Madness.model.PlayerBinomeDao;
import com.littleGreenMan.Pong_Madness.model.GameDao;
import com.littleGreenMan.Pong_Madness.model.DoubleGameDao;
import com.littleGreenMan.Pong_Madness.model.SimpleGameDao;
import com.littleGreenMan.Pong_Madness.model.SimpleParticipantDao;
import com.littleGreenMan.Pong_Madness.model.DoubleParticipantDao;
import com.littleGreenMan.Pong_Madness.model.GameTournementDao;
import com.littleGreenMan.Pong_Madness.model.TeamDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig tournementDaoConfig;
    private final DaoConfig leaderboardDaoConfig;
    private final DaoConfig leaderboardPlayerDaoConfig;
    private final DaoConfig playerDaoConfig;
    private final DaoConfig playerTournementDaoConfig;
    private final DaoConfig binomeDaoConfig;
    private final DaoConfig playerBinomeDaoConfig;
    private final DaoConfig gameDaoConfig;
    private final DaoConfig doubleGameDaoConfig;
    private final DaoConfig simpleGameDaoConfig;
    private final DaoConfig simpleParticipantDaoConfig;
    private final DaoConfig doubleParticipantDaoConfig;
    private final DaoConfig gameTournementDaoConfig;
    private final DaoConfig teamDaoConfig;

    private final TournementDao tournementDao;
    private final LeaderboardDao leaderboardDao;
    private final LeaderboardPlayerDao leaderboardPlayerDao;
    private final PlayerDao playerDao;
    private final PlayerTournementDao playerTournementDao;
    private final BinomeDao binomeDao;
    private final PlayerBinomeDao playerBinomeDao;
    private final GameDao gameDao;
    private final DoubleGameDao doubleGameDao;
    private final SimpleGameDao simpleGameDao;
    private final SimpleParticipantDao simpleParticipantDao;
    private final DoubleParticipantDao doubleParticipantDao;
    private final GameTournementDao gameTournementDao;
    private final TeamDao teamDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        tournementDaoConfig = daoConfigMap.get(TournementDao.class).clone();
        tournementDaoConfig.initIdentityScope(type);

        leaderboardDaoConfig = daoConfigMap.get(LeaderboardDao.class).clone();
        leaderboardDaoConfig.initIdentityScope(type);

        leaderboardPlayerDaoConfig = daoConfigMap.get(LeaderboardPlayerDao.class).clone();
        leaderboardPlayerDaoConfig.initIdentityScope(type);

        playerDaoConfig = daoConfigMap.get(PlayerDao.class).clone();
        playerDaoConfig.initIdentityScope(type);

        playerTournementDaoConfig = daoConfigMap.get(PlayerTournementDao.class).clone();
        playerTournementDaoConfig.initIdentityScope(type);

        binomeDaoConfig = daoConfigMap.get(BinomeDao.class).clone();
        binomeDaoConfig.initIdentityScope(type);

        playerBinomeDaoConfig = daoConfigMap.get(PlayerBinomeDao.class).clone();
        playerBinomeDaoConfig.initIdentityScope(type);

        gameDaoConfig = daoConfigMap.get(GameDao.class).clone();
        gameDaoConfig.initIdentityScope(type);

        doubleGameDaoConfig = daoConfigMap.get(DoubleGameDao.class).clone();
        doubleGameDaoConfig.initIdentityScope(type);

        simpleGameDaoConfig = daoConfigMap.get(SimpleGameDao.class).clone();
        simpleGameDaoConfig.initIdentityScope(type);

        simpleParticipantDaoConfig = daoConfigMap.get(SimpleParticipantDao.class).clone();
        simpleParticipantDaoConfig.initIdentityScope(type);

        doubleParticipantDaoConfig = daoConfigMap.get(DoubleParticipantDao.class).clone();
        doubleParticipantDaoConfig.initIdentityScope(type);

        gameTournementDaoConfig = daoConfigMap.get(GameTournementDao.class).clone();
        gameTournementDaoConfig.initIdentityScope(type);

        teamDaoConfig = daoConfigMap.get(TeamDao.class).clone();
        teamDaoConfig.initIdentityScope(type);

        tournementDao = new TournementDao(tournementDaoConfig, this);
        leaderboardDao = new LeaderboardDao(leaderboardDaoConfig, this);
        leaderboardPlayerDao = new LeaderboardPlayerDao(leaderboardPlayerDaoConfig, this);
        playerDao = new PlayerDao(playerDaoConfig, this);
        playerTournementDao = new PlayerTournementDao(playerTournementDaoConfig, this);
        binomeDao = new BinomeDao(binomeDaoConfig, this);
        playerBinomeDao = new PlayerBinomeDao(playerBinomeDaoConfig, this);
        gameDao = new GameDao(gameDaoConfig, this);
        doubleGameDao = new DoubleGameDao(doubleGameDaoConfig, this);
        simpleGameDao = new SimpleGameDao(simpleGameDaoConfig, this);
        simpleParticipantDao = new SimpleParticipantDao(simpleParticipantDaoConfig, this);
        doubleParticipantDao = new DoubleParticipantDao(doubleParticipantDaoConfig, this);
        gameTournementDao = new GameTournementDao(gameTournementDaoConfig, this);
        teamDao = new TeamDao(teamDaoConfig, this);

        registerDao(Tournement.class, tournementDao);
        registerDao(Leaderboard.class, leaderboardDao);
        registerDao(LeaderboardPlayer.class, leaderboardPlayerDao);
        registerDao(Player.class, playerDao);
        registerDao(PlayerTournement.class, playerTournementDao);
        registerDao(Binome.class, binomeDao);
        registerDao(PlayerBinome.class, playerBinomeDao);
        registerDao(Game.class, gameDao);
        registerDao(DoubleGame.class, doubleGameDao);
        registerDao(SimpleGame.class, simpleGameDao);
        registerDao(SimpleParticipant.class, simpleParticipantDao);
        registerDao(DoubleParticipant.class, doubleParticipantDao);
        registerDao(GameTournement.class, gameTournementDao);
        registerDao(Team.class, teamDao);
    }
    
    public void clear() {
        tournementDaoConfig.getIdentityScope().clear();
        leaderboardDaoConfig.getIdentityScope().clear();
        leaderboardPlayerDaoConfig.getIdentityScope().clear();
        playerDaoConfig.getIdentityScope().clear();
        playerTournementDaoConfig.getIdentityScope().clear();
        binomeDaoConfig.getIdentityScope().clear();
        playerBinomeDaoConfig.getIdentityScope().clear();
        gameDaoConfig.getIdentityScope().clear();
        doubleGameDaoConfig.getIdentityScope().clear();
        simpleGameDaoConfig.getIdentityScope().clear();
        simpleParticipantDaoConfig.getIdentityScope().clear();
        doubleParticipantDaoConfig.getIdentityScope().clear();
        gameTournementDaoConfig.getIdentityScope().clear();
        teamDaoConfig.getIdentityScope().clear();
    }

    public TournementDao getTournementDao() {
        return tournementDao;
    }

    public LeaderboardDao getLeaderboardDao() {
        return leaderboardDao;
    }

    public LeaderboardPlayerDao getLeaderboardPlayerDao() {
        return leaderboardPlayerDao;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public PlayerTournementDao getPlayerTournementDao() {
        return playerTournementDao;
    }

    public BinomeDao getBinomeDao() {
        return binomeDao;
    }

    public PlayerBinomeDao getPlayerBinomeDao() {
        return playerBinomeDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public DoubleGameDao getDoubleGameDao() {
        return doubleGameDao;
    }

    public SimpleGameDao getSimpleGameDao() {
        return simpleGameDao;
    }

    public SimpleParticipantDao getSimpleParticipantDao() {
        return simpleParticipantDao;
    }

    public DoubleParticipantDao getDoubleParticipantDao() {
        return doubleParticipantDao;
    }

    public GameTournementDao getGameTournementDao() {
        return gameTournementDao;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

}
