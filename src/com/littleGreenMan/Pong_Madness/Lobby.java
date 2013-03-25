package com.littleGreenMan.Pong_Madness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Lobby extends Activity implements View.OnClickListener {
    private FrameLayout mainLayout;
    private ImageView quickgameImageView;
    private ImageView knockoutImageView;
    private ImageView leaderboardImageView;
    private ImageView theplayersImageView;
    private FrameLayout fullScreenFramelayout;
    private Button singleButton;
    private Button doubleButton;
    private LinearLayout buttonsLayout;
    private LinearLayout paddlesLayout;

    private int animationDuration;
    private int paddleWidth;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        animationDuration = 400;
        //Variable declarations
        quickgameImageView = (ImageView) findViewById(R.id.lobby_imageview_quickgame);
        knockoutImageView = (ImageView) findViewById(R.id.lobby_imageview_knockout);
        leaderboardImageView = (ImageView) findViewById(R.id.lobby_imageview_leaderboard);
        theplayersImageView = (ImageView) findViewById(R.id.lobby_imageview_theplayers);
        singleButton = (Button)findViewById(R.id.lobby_button_single);
        doubleButton = (Button)findViewById(R.id.lobby_button_double);
        buttonsLayout = (LinearLayout) findViewById(R.id.lobby_linearlayout_singledouble);
        mainLayout = (FrameLayout) findViewById(R.id.lobby_layout_main);
        paddlesLayout= (LinearLayout) findViewById(R.id.lobby_layout_paddles);
        fullScreenFramelayout = (FrameLayout) findViewById(R.id.lobby_framelayout_fullscreen);

        fullScreenFramelayout.setVisibility(View.INVISIBLE);

        buttonsLayout.setAlpha(1);
        //Set the text font
        singleButton.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        doubleButton.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));

        //Add listener
        quickgameImageView.setOnClickListener(this);
        fullScreenFramelayout.setOnClickListener(this);
        singleButton.setOnClickListener(this);

        //Add listener to know modify layout
        ViewTreeObserver observer = mainLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //in here, place the code that requires you to know the dimensions.
                paddleWidth = quickgameImageView.getWidth();
                buttonsLayout.setX(paddleWidth * 1.2f);
                //this will be called as the layout is finished, prior to displaying.
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lobby_imageview_quickgame) {
            clickOnQuickGameAnimation();
            turnFirstPaddle();
            makeButtonAppear();
            fullScreenFramelayout.bringToFront();
        } else if (v.getId() == R.id.lobby_button_single) {
             Intent intent = new Intent(this, ThePlayers.class);
            startActivity(intent);
        } else if (v.getId() == R.id.lobby_button_double) {

        } else if (v.getId() == R.id.lobby_framelayout_fullscreen) {
            paddlesLayout.bringToFront();
            fullScreenFramelayout.setVisibility(View.INVISIBLE);
            Interpolator interpolator = new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    return Math.abs(input - 1f);
                }
            };

            Animation leaderboardAnimationReverse = leaderboardImageView.getAnimation();
            leaderboardAnimationReverse.setInterpolator(interpolator);
            leaderboardImageView.startAnimation(leaderboardAnimationReverse);


            Animation knockoutAnimationReverse = knockoutImageView.getAnimation();
            knockoutAnimationReverse.setInterpolator(interpolator);
            knockoutImageView.startAnimation(knockoutAnimationReverse);

            Animation theplayersAnimationReverse = theplayersImageView.getAnimation();
            theplayersAnimationReverse.setInterpolator(interpolator);
            theplayersImageView.startAnimation(theplayersAnimationReverse);

            makeButtonDisappear();
        }
    }

    private void makeButtonAppear() {
        buttonsLayout.setVisibility(View.VISIBLE);
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buttonsLayout.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        fadeIn.setFillAfter(true);
        fadeIn.setDuration(500);

        buttonsLayout.startAnimation(fadeIn);

    }

    private void makeButtonDisappear() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buttonsLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        fadeOut.setDuration(500);

        buttonsLayout.startAnimation(fadeOut);
    }

    private void clickOnQuickGameAnimation() {

        fullScreenFramelayout.setVisibility(View.VISIBLE);
        leaderboardImageView.bringToFront();
        knockoutImageView.bringToFront();

        //knockout animation
        AnimationSet knockoutAnimationSet = new AnimationSet(true);
        double knockoutAngle = Math.PI/9;
        int knockoutDeltaX = theplayersImageView.getLeft() - knockoutImageView.getLeft();
        TranslateAnimation knockoutTranslation = new TranslateAnimation(0, Math.round(knockoutDeltaX - 0.2 * paddleWidth), 0, -Math.round(1.3 * knockoutDeltaX * Math.tan(knockoutAngle)));
        RotateAnimation knockoutRotation = new RotateAnimation(0, Math.round(knockoutAngle*180/Math.PI));
        knockoutAnimationSet.addAnimation(knockoutTranslation);
        knockoutAnimationSet.addAnimation(knockoutRotation);
        knockoutAnimationSet.setDuration(animationDuration);
        knockoutAnimationSet.setFillAfter(true);

        //leaderboard animation
        AnimationSet leaderboardAnimationSet = new AnimationSet(true);
        double leaderboardAngle = - Math.PI/20;
        int leaderboardDeltaX = theplayersImageView.getLeft() - leaderboardImageView.getLeft();
        TranslateAnimation leaderboardTranslation = new TranslateAnimation(0, Math.round(leaderboardDeltaX * 0.90 - 0.2 * paddleWidth), 0, -Math.round(1.15 * leaderboardDeltaX * Math.tan(leaderboardAngle)));
        RotateAnimation leaderboardRotation = new RotateAnimation(0, Math.round(leaderboardAngle*180/Math.PI));
        leaderboardAnimationSet.addAnimation(leaderboardTranslation);
        leaderboardAnimationSet.addAnimation(leaderboardRotation);
        leaderboardAnimationSet.setDuration(animationDuration);
        leaderboardAnimationSet.setFillAfter(true);

        //theplayersAnimation
        AnimationSet theplayersAnimationSet = new AnimationSet(true);

        TranslateAnimation theplayersTranslation = new TranslateAnimation(0, -Math.round(0.2 * paddleWidth), 0, 0);
        double thePlayersAngle = Math.PI/30;
        RotateAnimation theplayersRotation = new RotateAnimation(0,Math.round(thePlayersAngle*180/Math.PI));
        theplayersAnimationSet.addAnimation(theplayersTranslation);
        theplayersAnimationSet.addAnimation(theplayersRotation);
        theplayersAnimationSet.setDuration(animationDuration);
        theplayersAnimationSet.setFillAfter(true);

        theplayersImageView.startAnimation(theplayersAnimationSet);
        leaderboardImageView.startAnimation(leaderboardAnimationSet);
        knockoutImageView.startAnimation(knockoutAnimationSet);
    }

    private void turnFirstPaddle() {
        applyRotation(quickgameImageView, 0, 90);

    }

    private void applyRotation(final View view, float start, float end) {
        // Find the center of the container
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotation3dAnimation rotation =
                new Rotation3dAnimation(start, end, centerX, centerY, 0, true);
        rotation.setDuration(200);
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                final Rotation3dAnimation rotationSuite =
                        new Rotation3dAnimation(-90, 0, centerX, centerY, 0, true);
                rotationSuite.setDuration(200);
                view.startAnimation(rotationSuite);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        view.startAnimation(rotation);
    }
}
