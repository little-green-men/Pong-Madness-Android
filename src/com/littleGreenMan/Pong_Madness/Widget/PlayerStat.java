package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.littleGreenMan.Pong_Madness.FontTools;
import com.littleGreenMan.Pong_Madness.R;
import com.littleGreenMan.Pong_Madness.client.PlayerClient;
import com.littleGreenMan.Pong_Madness.model.Player;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerStat extends LinearLayout {

    private Context context;
    private Player player;
    private ImageView picture;
    private TextView name;
    private TextView rank, points, wins, losses, played, rankTitle, pointsTitle, winsTitle, lossesTitle, playedTitle;
    private ImageView hand;

    public PlayerStat(Context context, Player player) {
        super(context);
        this.context = context;
        this.player = player;
        setup();
        update();
    }

    public PlayerStat(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setup();
    }

    private void update() {
        name.setText(player.getUserName());

        rank.setText(PlayerClient.getRankOfPlayer(player));
        wins.setText(PlayerClient.getWinsOfPlayer(player));
        losses.setText(PlayerClient.getLossesOfPlayer(player));
        points.setText(PlayerClient.getPointsOfPlayer(player));
        played.setText(PlayerClient.getPlayedOfPlayer(player));

        if(player.getHandedness().equals("L")) {
            hand.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_left_active));
        } else if (player.getHandedness().equals("R")) {
            hand.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_right_active));
        }
    }

    public void setPlayerAndUpdate(Player player) {
        this.player = player;
        update();
    };

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainLayout = (LinearLayout)inflater.inflate(R.layout.player_statistic, this);

        picture = (ImageView) mainLayout.findViewById(R.id.cell_player_picture);

        picture = (ImageView) mainLayout.findViewById(R.id.player_stat_imageview_picture);
        name = (TextView) mainLayout.findViewById(R.id.player_stat_textview_name);
        rank = (TextView) mainLayout.findViewById(R.id.player_stat_textview_rank);
        losses = (TextView) mainLayout.findViewById(R.id.player_stat_textview_losses);
        played = (TextView) mainLayout.findViewById(R.id.player_stat_textview_played);
        wins = (TextView) mainLayout.findViewById(R.id.player_stat_textview_wins);
        points = (TextView) mainLayout.findViewById(R.id.player_stat_textview_points);
        rankTitle = (TextView) mainLayout.findViewById(R.id.player_stat_textview_ranktitle);
        lossesTitle = (TextView) mainLayout.findViewById(R.id.player_stat_textview_lossestitle);
        playedTitle = (TextView) mainLayout.findViewById(R.id.player_stat_textview_playedtitle);
        winsTitle = (TextView) mainLayout.findViewById(R.id.player_stat_textview_winstitle);
        pointsTitle = (TextView) mainLayout.findViewById(R.id.player_stat_textview_pointstitle);
        hand = (ImageView) mainLayout.findViewById(R.id.player_stat_imageview_hand);

        name.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold,context));
        rank.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold,context));
        wins.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        losses.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        points.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        played.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        rankTitle.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold,context));
        winsTitle.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        lossesTitle.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        pointsTitle.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        playedTitle.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));

    }


}
