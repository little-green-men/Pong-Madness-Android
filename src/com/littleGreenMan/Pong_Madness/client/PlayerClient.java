package com.littleGreenMan.Pong_Madness.client;

import com.littleGreenMan.Pong_Madness.utils.DatabaseManager;
import com.littleGreenMan.Pong_Madness.model.Player;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerClient {

    public static void addPlayer(Player newPlayer) {
        DatabaseManager.getDaoSession().getPlayerDao().insert(newPlayer);
    }

    public static void updatePlayer(Player player) {
        DatabaseManager.getDaoSession().getPlayerDao().update(player);
    }

    public static int getRankOfPlayer(Player player) {
        return 0;
    }

    public static int getWinsOfPlayer(Player player) {
        return 0;
    }

    public static int getPlayedOfPlayer(Player player) {
        return 0;
    }

    public static int getLossesOfPlayer(Player player) {
        return 0;
    }

    public static int getPointsOfPlayer(Player player) {
        return 0;
    }

    public static List<Player> getAllPlayer() {
        return DatabaseManager.getDaoSession().getPlayerDao().loadAll();
    }
}
