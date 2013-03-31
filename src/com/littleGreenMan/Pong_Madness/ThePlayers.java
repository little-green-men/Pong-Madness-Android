package com.littleGreenMan.Pong_Madness;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.littleGreenMan.Pong_Madness.Widget.CellAddPlayer;
import com.littleGreenMan.Pong_Madness.Widget.CellPlayer;
import com.littleGreenMan.Pong_Madness.Widget.PlayerDialogFragment;
import com.littleGreenMan.Pong_Madness.client.PlayerClient;
import com.littleGreenMan.Pong_Madness.model.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThePlayers extends FragmentActivity {

    ViewGroup container = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_players);

        container = new FixedGridLayout(this);
        ((FixedGridLayout)container).setCellHeight(224);
        ((FixedGridLayout)container).setCellWidth(175);
        final LayoutTransition transitioner = new LayoutTransition();
        container.setLayoutTransition(transitioner);

        ViewGroup parent = (ViewGroup) findViewById(R.id.theplayers_layout_main);

        parent.addView(container);
        final CellAddPlayer addPlayer = new CellAddPlayer(ThePlayers.this);

        container.addView(addPlayer , Math.min(0, container.getChildCount()));


       addPlayer.setClickListenerToAddplayer(new View.OnClickListener() {
           public void onClick(View v) {

               //Today date
               Calendar cal = Calendar.getInstance();
               SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
               Date today = new Date(cal.getTimeInMillis());
               String dateFormated = format.format(today);

               //New player creation
               final Player newPlayer = new Player();
               newPlayer.setUserName(addPlayer.getEditTextName());
               newPlayer.setSinceDate(dateFormated);

               PlayerClient.addPlayerWithName(newPlayer);
               CellPlayer newPlayerCell = new CellPlayer(ThePlayers.this);
               newPlayerCell.setName(addPlayer.getEditTextName());
               newPlayerCell.setSinceDate(dateFormated);

               newPlayerCell.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       PlayerDialogFragment dialog = PlayerDialogFragment.newInstance(ThePlayers.this);
                       dialog.setPlayer(newPlayer);
                       dialog.show(getSupportFragmentManager(), "player");
                   }
               });
               container.addView(newPlayerCell, Math.min(1, container.getChildCount()));
           }
       });
    }
}