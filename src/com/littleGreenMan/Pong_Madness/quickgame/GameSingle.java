package com.littleGreenMan.Pong_Madness.quickgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.littleGreenMan.Pong_Madness.R;
import com.littleGreenMan.Pong_Madness.Widget.PlayerStat;
import com.littleGreenMan.Pong_Madness.model.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 4/21/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameSingle extends FragmentActivity implements View.OnClickListener{

    private ImageView switcher;
    private Button startFinish;
    int matchPoints = 11;
    Player playerA;
    Player playerB;
    PlayerStat playerStatA;
    PlayerStat playerStatB;
    Chronometer chronometer;
    LinearLayout leftButtons;
    LinearLayout rightButtons;
    ImageView plusLeft;
    ImageView minusLeft;
    ImageView plusRight;
    ImageView minusRight;
    int scoreLeft = 0;
    int scoreRight = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_single);

        ArrayList<Player> players = (ArrayList<Player>) getIntent().getSerializableExtra(Player.IDENTIFIER);
        playerStatA = (PlayerStat) findViewById(R.id.game_single_playerA);
        playerStatB = (PlayerStat) findViewById(R.id.game_single_playerB);

        if (players.size() ==2) {
            playerA = players.get(0);
            playerStatA.setPlayerAndUpdate(playerA);
            playerB = players.get(1);
            playerStatB.setPlayerAndUpdate(playerB);
        }

        leftButtons = (LinearLayout) findViewById(R.id.game_single_left_buttons);
        rightButtons = (LinearLayout) findViewById(R.id.game_single_right_buttons);
        minusLeft = (ImageView) findViewById(R.id.game_single_left_minus);
        plusLeft = (ImageView) findViewById(R.id.game_single_left_minus);
        minusRight = (ImageView) findViewById(R.id.game_single_right_minus);
        plusRight = (ImageView) findViewById(R.id.game_single_right_plus);
        switcher = (ImageView) findViewById(R.id.game_single_switcher);
        startFinish = (Button) findViewById(R.id.game_single_button_start);
        chronometer = (Chronometer) findViewById(R.id.game_single_chronometer);
        switcher.setOnClickListener(this);
        startFinish.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        TranslateAnimation comeInAnimation = new TranslateAnimation(0, 0, -300, 0);
        comeInAnimation.setDuration(1000);
        playerStatA.startAnimation(comeInAnimation);
        playerStatB.startAnimation(comeInAnimation);
    }

    @Override
    public void onClick(View v) {
        if (R.id.game_single_switcher == v.getId()) {
            if (matchPoints == 11) {
                switcher.setImageDrawable(getResources().getDrawable(R.drawable.game_switch_21));
                matchPoints =21;
            } else {
                switcher.setImageDrawable(getResources().getDrawable(R.drawable.game_switch_11));
                matchPoints = 11;
            }
        } else if (R.id.game_single_button_start == v.getId()) {
            startFinish.setVisibility(View.INVISIBLE);
            makeScoreButtonVisible();
            chronometer.setVisibility(View.VISIBLE);
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    //Log.d("CHRONO", "base with format:" + elapsedMillis/1000);
                }
            });
            chronometer.start();

        }
    }

    private void makeScoreButtonVisible() {
        int duration = 2000;
        TranslateAnimation translationFromLeftToRight = new TranslateAnimation(-100, 0, 0, 0);
        translationFromLeftToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                leftButtons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        translationFromLeftToRight.setDuration(duration);
        TranslateAnimation translationFromRightToLeft = new TranslateAnimation(100, 0, 0, 0);
        translationFromRightToLeft.setDuration(duration);
        translationFromRightToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rightButtons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        rightButtons.startAnimation(translationFromRightToLeft);
        leftButtons.startAnimation(translationFromLeftToRight);

    }
}
