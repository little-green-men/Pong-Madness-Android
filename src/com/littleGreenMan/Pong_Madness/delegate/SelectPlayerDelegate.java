package com.littleGreenMan.Pong_Madness.delegate;

import com.littleGreenMan.Pong_Madness.model.Player;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 4/13/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SelectPlayerDelegate {

    void onPlayerSelectedChanged(Player player, boolean isSelected);
}
