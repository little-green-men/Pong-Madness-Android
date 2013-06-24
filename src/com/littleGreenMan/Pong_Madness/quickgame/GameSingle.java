package com.littleGreenMan.Pong_Madness.quickgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.littleGreenMan.Pong_Madness.R;
import com.littleGreenMan.Pong_Madness.Widget.PlayerStat;
import com.littleGreenMan.Pong_Madness.model.Player;
import com.littleGreenMan.Pong_Madness.utils.FontTools;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 4/21/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameSingle extends FragmentActivity implements View.OnClickListener{


    int matchPoints = 11;

    //Views
    private ImageView switcher;
    private Button startFinish;
    private Player playerA;
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
    TextView scoreLeftTv;
    TextView scoreRightTv;
    FrameLayout scoreLeftFrame;
    FrameLayout scoreRightFrame;
    LinearLayout playersLayout;
    ImageView pingpongTableImageView;
    LinearLayout winnerLabelLayout;
    TextView winnerDefeatedTv;

    //Points
    int scoreLeft = 0;
    int scoreRight = 0;
    boolean isFinished = false;

    //Animation
    int duration = 2000;
    Animation buttonTranslationFromRightToLeft;
    Animation buttonTranslationFromLeftToRight;
    AlphaAnimation fadeAnimation;



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
        plusLeft = (ImageView) findViewById(R.id.game_single_left_plus);
        minusRight = (ImageView) findViewById(R.id.game_single_right_minus);
        plusRight = (ImageView) findViewById(R.id.game_single_right_plus);
        switcher = (ImageView) findViewById(R.id.game_single_switcher);
        startFinish = (Button) findViewById(R.id.game_single_button_start);
        chronometer = (Chronometer) findViewById(R.id.game_single_chronometer);
        scoreLeftTv = (TextView) findViewById(R.id.game_single_score_left);
        scoreRightTv = (TextView) findViewById(R.id.game_single_score_right);
        scoreLeftFrame = (FrameLayout) findViewById(R.id.game_single_score_frame_left);
        scoreRightFrame = (FrameLayout) findViewById(R.id.game_single_score_frame_right);
        playersLayout = (LinearLayout) findViewById(R.id.game_single_lyt_players);
        pingpongTableImageView = (ImageView) findViewById(R.id.game_single_iv_table);
        winnerLabelLayout = (LinearLayout) findViewById(R.id.game_single_lyt_thewinnerlabel);
        winnerDefeatedTv = (TextView) findViewById(R.id.game_single_defeated_tv);

        TextView andTheTv = (TextView) findViewById(R.id.game_single_tv_andthe);
        TextView winnerTv = (TextView) findViewById(R.id.game_single_tv_winner);
        TextView isTv = (TextView) findViewById(R.id.game_single_tv_is);

        switcher.setOnClickListener(this);
        startFinish.setOnClickListener(this);
        minusLeft.setOnClickListener(this);
        minusRight.setOnClickListener(this);
        plusLeft.setOnClickListener(this);
        plusRight.setOnClickListener(this);

        startFinish.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        scoreLeftTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        scoreRightTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        chronometer.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        andTheTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        winnerTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        isTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        winnerDefeatedTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
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
            checkEndOfGame();
        } else if (R.id.game_single_button_start == v.getId()) {
            if (isFinished) {
                makeLooserFallingAndWinnerCentering();
                makeButtonAndScoreDisappear();
                makeTableMovingUp();

            } else {
                startFinish.setVisibility(View.INVISIBLE);
                makeScoreButtonVisible();
                makeScoreVisible();
                chronometer.setBase(SystemClock.elapsedRealtime());

                chronometer.setVisibility(View.VISIBLE);
                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                        Log.d("CHRONO", "base with format:" + SystemClock.elapsedRealtime());
                    }
                });
            }
        }  else if (R.id.game_single_left_plus == v.getId()) {
            scoreLeft++;
            scoreLeftTv.setText(String.valueOf(scoreLeft));
            checkEndOfGame();

        }  else if (R.id.game_single_left_minus == v.getId()) {
            if (scoreLeft>0) {
                scoreLeft--;
                scoreLeftTv.setText(String.valueOf(scoreLeft));
                checkEndOfGame();
            }

        }else if (R.id.game_single_right_plus == v.getId()) {
            scoreRight++;
            scoreRightTv.setText(String.valueOf(scoreRight));
            checkEndOfGame();

        }else if (R.id.game_single_right_minus == v.getId()) {
            if (scoreRight>0) {
                scoreRight--;
                scoreRightTv.setText(String.valueOf(scoreRight));
                checkEndOfGame();
            }
        }
    }

    private void checkEndOfGame() {
        if ((scoreRight >= matchPoints || scoreLeft >= matchPoints) && Math.abs(scoreRight-scoreLeft) >= 2) {
            chronometer.stop();
            chronometer.setVisibility(View.INVISIBLE);
            startFinish.setVisibility(View.VISIBLE);
            isFinished = true;
            startFinish.setText(R.string.finish);
        } else {
            if (isFinished == true) {

                startFinish.setText(R.string.start);
                isFinished = false;
                chronometer.start();
                chronometer.setVisibility(View.VISIBLE);
                startFinish.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void makeScoreButtonVisible() {
        buttonTranslationFromLeftToRight = new TranslateAnimation(-leftButtons.getWidth(), 0, 0, 0);
        buttonTranslationFromLeftToRight.setFillAfter(true);
        buttonTranslationFromLeftToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                leftButtons.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                chronometer.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        //Right buttons animaton
        buttonTranslationFromLeftToRight.setDuration(duration);
        buttonTranslationFromRightToLeft = new TranslateAnimation(rightButtons.getWidth(), 0, 0, 0);
        buttonTranslationFromRightToLeft.setDuration(duration);
        buttonTranslationFromRightToLeft.setFillAfter(true);
        buttonTranslationFromRightToLeft.setAnimationListener(new Animation.AnimationListener() {
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

        rightButtons.startAnimation(buttonTranslationFromRightToLeft);
        leftButtons.startAnimation(buttonTranslationFromLeftToRight);

    }

    private void makeScoreVisible() {
        fadeAnimation = new AlphaAnimation(0f, 1f);
        fadeAnimation.setDuration(duration);
        fadeAnimation.setFillAfter(true);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                scoreLeftFrame.setVisibility(View.VISIBLE);
                scoreRightFrame.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scoreRightFrame.startAnimation(fadeAnimation);
        scoreLeftFrame.startAnimation(fadeAnimation);

    }

    private void makeLooserFallingAndWinnerCentering() {
        int translationDelta = playerStatA.getWidth()*2;

        final PlayerStat winner;
        final PlayerStat looser;
        if (scoreLeft > scoreRight) {
            winner = playerStatA;
            looser = playerStatB;

        } else {
            winner = playerStatB;
            looser = playerStatA;
            translationDelta = 0 - translationDelta;
        }
        // Looser animation
        TranslateAnimation looserTranslateAnimation = new TranslateAnimation(0, translationDelta, 0, 0);
        looserTranslateAnimation.setFillAfter(true);
        looserTranslateAnimation.setDuration(duration);
        looserTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Winner Animation
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int w = dm.widthPixels;
                int y = dm.heightPixels;
                int endX = w/2 - winner.getWidth()/2;
                int endY = pingpongTableImageView.getHeight() - winner.getHeight() - winner.getTop() + 15;
                TranslateAnimation winnerTranslateAnimation = new TranslateAnimation(winner.getX(), endX, 0, endY);
                winnerTranslateAnimation.setDuration(duration);
                winnerTranslateAnimation.setFillAfter(true);
                winner.startAnimation(winnerTranslateAnimation);

                AlphaAnimation fadeAnimation = new AlphaAnimation(0, 1);
                fadeAnimation.setDuration(duration);
                fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        winnerLabelLayout.bringToFront();
                        winnerLabelLayout.setVisibility(View.VISIBLE);
                        winnerDefeatedTv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                winnerLabelLayout.setAnimation(fadeAnimation);
                winnerDefeatedTv.setAnimation(fadeAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        looser.startAnimation(looserTranslateAnimation);

    }

    private void makeTableMovingUp() {

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -pingpongTableImageView.getHeight());
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        pingpongTableImageView.startAnimation(translateAnimation);
    }

    private  void makeButtonAndScoreDisappear() {
        Interpolator interpolator = new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return Math.abs(input - 1f);
            }
        };

        buttonTranslationFromRightToLeft.setInterpolator(interpolator);
        rightButtons.startAnimation(buttonTranslationFromRightToLeft);

        buttonTranslationFromLeftToRight.setInterpolator(interpolator);
        leftButtons.startAnimation(buttonTranslationFromLeftToRight);

        fadeAnimation.setInterpolator(interpolator);
        scoreRightFrame.startAnimation(fadeAnimation);
        scoreLeftFrame.startAnimation(fadeAnimation);
        startFinish.setAnimation(fadeAnimation);
        switcher.startAnimation(fadeAnimation);
    }
}

