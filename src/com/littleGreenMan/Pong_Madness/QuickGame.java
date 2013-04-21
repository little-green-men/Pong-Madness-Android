package com.littleGreenMan.Pong_Madness;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ScrollView;
import com.littleGreenMan.Pong_Madness.Widget.CellPlayer;
import com.littleGreenMan.Pong_Madness.Widget.PlayerDialogFragment;
import com.littleGreenMan.Pong_Madness.client.PlayerClient;
import com.littleGreenMan.Pong_Madness.delegate.SelectPlayerDelegate;
import com.littleGreenMan.Pong_Madness.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 4/6/13
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */


public class QuickGame extends FragmentActivity implements SelectPlayerDelegate{

    private ViewGroup container = null;
    private ScrollView parentScrollview;
    private ViewGroup parentLayout;
    private List<Player> players;

    private int maxPlayerToSelect = 2;
    private int gamemode;

    private List<Player> playersSelected = new ArrayList<Player>();

    public static int GAME_MODE_SINGLE = 0;
    public static int GAME_MODE_DOUBLE = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quickgame, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gamemode = getIntent().getIntExtra("gametype", GAME_MODE_SINGLE);
        setupConfiguration();

        setContentView(R.layout.quickgame_simple);

        container = new FixedGridLayout(this);
        ((FixedGridLayout)container).setCellHeight(246);
        ((FixedGridLayout)container).setCellWidth(196);
        final LayoutTransition transitioner = new LayoutTransition();
        container.setLayoutTransition(transitioner);

        parentLayout = (ViewGroup) findViewById(R.id.quickgame_lyt_main);
        parentScrollview = (ScrollView) findViewById(R.id.quickgame_scrollview);
        parentScrollview.addView(container);

        players = PlayerClient.getAllPlayer();
        Collections.sort(players);
        displayPlayers();
    }

    private void setupConfiguration() {
        switch (gamemode) {
            case 0 :
                maxPlayerToSelect = 2;
                break;
            case 1 :
                maxPlayerToSelect = 4;
                break;
        }
    }

    private void displayPlayers() {

        for (final Player player: players) {
            final CellPlayer newPlayerCell = new CellPlayer(QuickGame.this, this, player);
            newPlayerCell.setMode(CellPlayer.Mode.SELECTABLE);
            container.addView(newPlayerCell, Math.min(players.indexOf(player) , container.getChildCount()));
        }
    }

    @Override
    public void onPlayerSelectedChanged(Player player, boolean isSelected) {
        if (isSelected) {
            playersSelected.add(player);
            if (playersSelected.size() >= maxPlayerToSelect) {
                setUnselectedCellsEnable(false);
            }
        } else {
            playersSelected.remove(player);
            if (playersSelected.size() < maxPlayerToSelect) {
                setUnselectedCellsEnable(true);
            }
        }
    }

    private void setUnselectedCellsEnable(boolean isEnable) {
        int childCount = container.getChildCount();
        for (int i=0; i<childCount; i++) {
            if (container.getChildAt(i) instanceof CellPlayer) {
                CellPlayer cell = (CellPlayer) container.getChildAt(i);
                if(cell.getPlayer() != null && !playersSelected.contains(cell.getPlayer())) {
                     cell.setUnselectedCellsEnable(isEnable);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_play :
                clickOnPlay();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clickOnPlay() {
        //To change body of created methods use File | Settings | File Templates
    }
}
