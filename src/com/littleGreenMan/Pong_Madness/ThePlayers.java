package com.littleGreenMan.Pong_Madness;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.littleGreenMan.Pong_Madness.Widget.CellAddPlayer;
import com.littleGreenMan.Pong_Madness.Widget.CellPlayer;
import com.littleGreenMan.Pong_Madness.Widget.PlayerDialogFragment;
import com.littleGreenMan.Pong_Madness.client.PlayerClient;
import com.littleGreenMan.Pong_Madness.model.Player;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThePlayers extends FragmentActivity {

    private ViewGroup container = null;
    private ScrollView parentScrollview;
    private ViewGroup parentLayout;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_players);

        container = new FixedGridLayout(this);
        ((FixedGridLayout)container).setCellHeight(246);
        ((FixedGridLayout)container).setCellWidth(196);
        final LayoutTransition transitioner = new LayoutTransition();
        container.setLayoutTransition(transitioner);

        parentLayout = (ViewGroup) findViewById(R.id.theplayers_layout_main);
        parentScrollview = (ScrollView) findViewById(R.id.theplayers_scrollview);
        parentScrollview.addView(container);

        addFirstCell();
        players = PlayerClient.getAllPlayer();
        Collections.sort(players);
        displayPlayers();


    }

    private void displayPlayers() {
        for (final Player player: players) {
            CellPlayer newPlayerCell = new CellPlayer(ThePlayers.this);
            newPlayerCell.setName(player.getUserName());
            newPlayerCell.setSinceDate("Since "+ player.getSinceDate());
            newPlayerCell.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    PlayerDialogFragment dialog = PlayerDialogFragment.newInstance(ThePlayers.this);
                    dialog.setPlayer(player);
                    dialog.show(getSupportFragmentManager(), "player");
                }
            });
            container.addView(newPlayerCell, Math.min(players.indexOf(player) +1, container.getChildCount()));

        }
    }

    public void addFirstCell() {
        final CellAddPlayer addPlayer = new CellAddPlayer(ThePlayers.this, parentScrollview, parentLayout);
        container.addView(addPlayer , Math.min(0, container.getChildCount()));


        addPlayer.setClickListenerToAddplayer(new View.OnClickListener() {
            public void onClick(View v) {

                //Today date
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
                Date today = new Date(cal.getTimeInMillis());
                String dateFormated = format.format(today);

                //New player creation
                final Player newPlayer = new Player();
                newPlayer.setUserName(addPlayer.getEditTextName());
                newPlayer.setSinceDate(dateFormated);
                players.add(newPlayer);
                Collections.sort(players);
                PlayerClient.addPlayerWithName(newPlayer);
                CellPlayer newPlayerCell = new CellPlayer(ThePlayers.this);
                newPlayerCell.setName(addPlayer.getEditTextName());
                newPlayerCell.setSinceDate("Since " + dateFormated);
                addPlayer.makeEditTextEmpty();
                newPlayerCell.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        PlayerDialogFragment dialog = PlayerDialogFragment.newInstance(ThePlayers.this);
                        dialog.setPlayer(newPlayer);
                        dialog.show(getSupportFragmentManager(), "player");
                    }
                });
                container.addView(newPlayerCell, Math.min(players.indexOf(newPlayer) +1, container.getChildCount()));
            }
        });
    }
}