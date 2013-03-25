package com.littleGreenMan.Pong_Madness;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.littleGreenMan.Pong_Madness.Widget.CellPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThePlayers extends Activity {

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

        /*LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainLayout = inflater.inflate(R.layout.cell_add_player, container);
        */
        parent.addView(container);
        CellPlayer addPlayer = new CellPlayer(ThePlayers.this);

        container.addView(addPlayer , Math.min(0, container.getChildCount()));


       addPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CellPlayer newPlayer = new CellPlayer(ThePlayers.this);
                newPlayer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //container.removeView(v);
                    }
                });
                container.addView(newPlayer, Math.min(1, container.getChildCount()));
            }
        });

        //To change body of overridden methods use File | Settings | File Templates.
    }
}