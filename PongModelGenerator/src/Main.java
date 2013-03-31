import de.greenrobot.daogenerator.*;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private static Schema schema = null;
    public static void main(String ...args){
        schema = new Schema(1, "com.littleGreenMan.Pong_Madness.model");
        schema.enableKeepSectionsByDefault();
        createTournament();
        createLeaderboard();
        createLeaderboardPlayer();
        createPlayer();
        createPlayerTournement();
        createBinome();
        createPlayerBinome();
        createGame();
        createDoubleGame();
        createSimpleGame();
        createSimpleParticipant();
        createDoubleParticipant();

        createGameTournement();

        createTeam();

        // Relative path where all classes will be generated
        try {
            new DaoGenerator().generateAll(schema, "src/");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void createTournament() {
        final Entity tournament = schema.addEntity("Tournement");
        tournament.addIdProperty().autoincrement();

        tournament.addStringProperty("name");
        tournament.addDateProperty("startDate");
    }

    private static void createLeaderboard() {
        final Entity leaderboard = schema.addEntity("Leaderboard");
        leaderboard.addIdProperty().autoincrement();
        leaderboard.addStringProperty("type");

        //To One
        Entity tournement = getEntityFromName("Tournement");
        Property tournementId = leaderboard.addLongProperty("tournementId").getProperty();
        leaderboard.addToOne(tournement, tournementId);


    }

    private static void createLeaderboardPlayer() {
        final Entity leaderboardPlayer = schema.addEntity("LeaderboardPlayer");
        leaderboardPlayer.addIdProperty().autoincrement();

        leaderboardPlayer.addIntProperty("gamesPlayedCount");
        leaderboardPlayer.addIntProperty("gamesWonCount");
        leaderboardPlayer.addIntProperty("rating");

        Entity leaderboard = getEntityFromName("Leaderboard");
        Property leaderboardId = leaderboardPlayer.addLongProperty("leaderboardId").getProperty();
        ToMany tomany = leaderboard.addToMany(leaderboardPlayer, leaderboardId);
        leaderboardPlayer.addToOne(leaderboard, leaderboardId);
        tomany.setName("leaderboardPlayerList");

    }

    private static void createPlayer() {
        final Entity player = schema.addEntity("Player");
        player.addIdProperty().autoincrement();

        player.addBooleanProperty("active");
        player.addStringProperty("email");
        player.addStringProperty("handedness");
        player.addStringProperty("photo");
        player.addStringProperty("sinceDate");
        player.addStringProperty("userName");
        player.addStringProperty("company");

        Entity leaderboardPlayer = getEntityFromName("LeaderboardPlayer");
        Property playerId = leaderboardPlayer.addLongProperty("playerId").getProperty();
        ToMany tomany = player.addToMany(leaderboardPlayer, playerId);
        leaderboardPlayer.addToOne(player, playerId);
        tomany.setName("leaderboardPlayerList");


    }

    private static void createPlayerTournement() {
        final Entity playerTournement  = schema.addEntity("PlayerTournement");
        playerTournement.addIdProperty().autoincrement();

        Entity player = getEntityFromName("Player");
        Property playerId = playerTournement.addLongProperty("playerId").getProperty();
        ToMany tomanyPlayer = player.addToMany(playerTournement, playerId);
        playerTournement.addToOne(player, playerId);
        tomanyPlayer.setName("playerTournementList");


        Entity tournement = getEntityFromName("Tournement");
        Property tournementId = playerTournement.addLongProperty("tournementId").getProperty();
        ToMany tomanyTournement = tournement.addToMany(playerTournement, tournementId);
        playerTournement.addToOne(tournement, tournementId);
        tomanyTournement.setName("playerTournementList");

    }

    private static void createBinome() {
        final Entity binome = schema.addEntity("Binome");
        binome.addIdProperty().autoincrement();
        binome.addStringProperty("name");

    }

    private static void createPlayerBinome() {
        final Entity playerBinome = schema.addEntity("PlayerBinome");
        playerBinome.addIdProperty().autoincrement();

        final Entity player = getEntityFromName("Player");
        final Entity binome = getEntityFromName("Binome");
        Property playerId = playerBinome.addLongProperty("playerId").getProperty();
        Property binomeId = playerBinome.addLongProperty("binomeId").getProperty();
        ToMany tomanyPlayer = player.addToMany(playerBinome, playerId);
        ToMany tomanyBinome = binome.addToMany(playerBinome, binomeId);
        tomanyPlayer.setName("playerBinomeList");
        tomanyBinome.setName("playerBinomeList");

    }

    private static void createTeam() {
        final Entity team = schema.addEntity("Team");
        team.addIdProperty().autoincrement();
        team.addStringProperty("logo");
        team.addStringProperty("name");

        final Entity player = getEntityFromName("Player");
        Property teamId = player.addLongProperty("teamId").getProperty();
        ToMany tomany = team.addToMany(player, teamId);
        player.addToOne(team, teamId);
        tomany.setName("playerList");
    }

    private static void createGame() {
        final Entity game = schema.addEntity("Game");
        game.addIdProperty().autoincrement();
        game.addDateProperty("startDate");
        game.addIntProperty("timePlayed");
        game.addStringProperty("type");
    }

    private static void createGameTournement() {
        final Entity gameTournement = schema.addEntity("GameTournement");
        gameTournement.addIdProperty().autoincrement();

        final Entity game = getEntityFromName("Game");
        final Entity tournement = getEntityFromName("Tournement");
        Property gameId = gameTournement.addLongProperty("gameId").getProperty();
        Property tournementId = gameTournement.addLongProperty("tournementId").getProperty();
        ToMany toManyTournement = tournement.addToMany(gameTournement, tournementId);
        ToMany toManyGame = game.addToMany(gameTournement, gameId);
        toManyTournement.setName("gameTournementList");
        toManyGame.setName("gameTournementList");
    }


    private static void createSimpleGame() {
        final Entity simpleGame= schema.addEntity("SimpleGame");
        simpleGame.setSuperclass("Game");
        simpleGame.addIdProperty().autoincrement();

    }

    private static void createDoubleGame() {
        final Entity doubleGame= schema.addEntity("DoubleGame");
        doubleGame.setSuperclass("Game");
        doubleGame.addIdProperty().autoincrement();

    }

    private static void createSimpleParticipant() {
        final Entity simpleParticipant= schema.addEntity("SimpleParticipant");
        simpleParticipant.addIdProperty().autoincrement();
        simpleParticipant.addIntProperty("score");

        final Entity simpleGame = getEntityFromName("SimpleGame");
        final Entity player = getEntityFromName("Player");
        Property simpleGameId = simpleParticipant.addLongProperty("simpleGameId").getProperty();
        Property playerId = simpleParticipant.addLongProperty("playerId").getProperty();
        player.addToMany(simpleParticipant, playerId).setName("simpleParticipantList");
        simpleGame.addToMany(simpleParticipant, simpleGameId).setName("simpleParticipantList");

    }


    private static void createDoubleParticipant() {
        final Entity doubleParticipant= schema.addEntity("DoubleParticipant");
        doubleParticipant.addIdProperty().autoincrement();
        doubleParticipant.addIntProperty("score");

        final Entity doubleGame = getEntityFromName("DoubleGame");
        final Entity binome = getEntityFromName("Binome");
        Property doubleGameId = doubleParticipant.addLongProperty("doubleGameId").getProperty();
        Property binomeId = doubleParticipant.addLongProperty("pbinomeId").getProperty();
        binome.addToMany(doubleParticipant, binomeId).setName("doubleParticipantList");
        doubleParticipant.addToMany(binome, doubleGameId).setName("doubleParticipantList");

    }


    private static Entity getEntityFromName(final String entityName) {
        for (final Entity e : schema.getEntities()) {
            if (e.getClassName().equals(entityName)) {
                return e;
            }
        }
        return null;
    }
}
