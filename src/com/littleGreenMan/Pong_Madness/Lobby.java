package com.littleGreenMan.Pong_Madness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.*;
import android.widget.*;
import com.littleGreenMan.Pong_Madness.quickgame.QuickGame;
import com.littleGreenMan.Pong_Madness.theplayers.ThePlayers;
import com.littleGreenMan.Pong_Madness.utils.FontTools;
import com.littleGreenMan.Pong_Madness.utils.Rotation3dAnimation;

public class Lobby extends Activity implements View.OnClickListener {

    private RelativeLayout mainLayout;
    private ImageView quickgameImageView;
    private ImageView knockoutImageView;
    private ImageView leaderboardImageView;
    private ImageView theplayersImageView;
    private ImageView coominSoonImage;
    private FrameLayout fullScreenFramelayout;
    private Button singleButton;
    private Button doubleButton;
    private LinearLayout buttonsLayout;
    private LinearLayout paddlesLayout;

    private int animationDuration;
    private int paddleWidth;

    private boolean isPaddleKnockOutBlack = false;
    private boolean isPaddleQuickGameBlack = false;

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
        mainLayout = (RelativeLayout) findViewById(R.id.lobby_layout_main);
        paddlesLayout= (LinearLayout) findViewById(R.id.lobby_layout_paddles);
        fullScreenFramelayout = (FrameLayout) findViewById(R.id.lobby_framelayout_fullscreen);
        coominSoonImage = (ImageView) findViewById(R.id.lobby_imageview_soon);

        fullScreenFramelayout.setVisibility(View.INVISIBLE);

        buttonsLayout.setAlpha(1);
        //Set the text font
        singleButton.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));
        doubleButton.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, this));

        //Add listener
        quickgameImageView.setOnClickListener(this);
        knockoutImageView.setOnClickListener(this);
        fullScreenFramelayout.setOnClickListener(this);
        singleButton.setOnClickListener(this);
        theplayersImageView.setOnClickListener(this);
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
            fullScreenFramelayout.bringToFront();
            makeViewAppearOrDesappear(buttonsLayout);

        } else if (v.getId() == R.id.lobby_imageview_knockout) {
            clickOnKnockOutAnimation();
            turnSecondPaddle();
            fullScreenFramelayout.bringToFront();
            makeViewAppearOrDesappear(coominSoonImage);

        } else if (v.getId() == R.id.lobby_button_single) {
            Intent intent = new Intent(this, QuickGame.class);
            intent.putExtra("gametype", QuickGame.GAME_MODE_SINGLE);
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
            if (knockoutAnimationReverse != null) {
                knockoutAnimationReverse.setInterpolator(interpolator);
                knockoutImageView.startAnimation(knockoutAnimationReverse);
            }

            Animation quickGameAnimationReverse = quickgameImageView.getAnimation();
            if (quickGameAnimationReverse != null) {
                quickGameAnimationReverse.setInterpolator(interpolator);
                quickgameImageView.startAnimation(quickGameAnimationReverse);
            }

            Animation theplayersAnimationReverse = theplayersImageView.getAnimation();
            theplayersAnimationReverse.setInterpolator(interpolator);
            theplayersImageView.startAnimation(theplayersAnimationReverse);



            if (isPaddleQuickGameBlack) {
                turnFirstPaddle();
                makeViewAppearOrDesappear(buttonsLayout);
            }
            if (isPaddleKnockOutBlack) {
                turnSecondPaddle();
                makeViewAppearOrDesappear(coominSoonImage);
            }
        } else if (R.id.lobby_imageview_theplayers == v.getId()) {
            Intent intent = new Intent(this, ThePlayers.class);
            startActivity(intent);
        }
    }

    private void makeViewAppearOrDesappear(final View view) {
        Animation fade;
        final Boolean isInvisible = (view.getVisibility() == View.INVISIBLE);
        if (isInvisible) {
            view.setVisibility(View.VISIBLE);
            fade = new AlphaAnimation(0,1);

        } else {
            fade = new AlphaAnimation(1,0);
        }
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 if (isInvisible) {
                    fullScreenFramelayout.setVisibility(View.VISIBLE);
                    fullScreenFramelayout.bringToFront();
                    view.bringToFront();
                } else {
                    view.setVisibility(View.INVISIBLE);
                    fullScreenFramelayout.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        fade.setFillAfter(true);
        fade.setDuration(500);

        view.startAnimation(fade);


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

    private void clickOnKnockOutAnimation() {
        fullScreenFramelayout.setVisibility(View.VISIBLE);
        fullScreenFramelayout.bringToFront();

        //theplayer animation
        theplayersImageView.bringToFront();
        AnimationSet thePlayerAnimationSet = new AnimationSet(true);
        TranslateAnimation thePlayerTranslation = new TranslateAnimation(0, Math.round(0.2*paddleWidth), 0, -Math.round(0.1 * paddleWidth));
        RotateAnimation thePlayerRotation = new RotateAnimation(0, 25, theplayersImageView.getWidth()/2.0f, theplayersImageView.getHeight()/2.0f);
        thePlayerAnimationSet.addAnimation(thePlayerTranslation);
        thePlayerAnimationSet.addAnimation(thePlayerRotation);
        thePlayerAnimationSet.setDuration(animationDuration);
        thePlayerAnimationSet.setFillAfter(true);


        //quickgame animation
        RotateAnimation quickgameRotation = new RotateAnimation(0, -35, quickgameImageView.getWidth()/2.0f, quickgameImageView.getHeight()/2.0f);
        quickgameRotation.setDuration(animationDuration);
        quickgameRotation.setFillAfter(true);

        //leaderboard animation
        leaderboardImageView.bringToFront();
        AnimationSet leaderboardAnimationSet = new AnimationSet(true);
        double leaderboardAngle = -Math.PI/10;
        TranslateAnimation leaderboardTranslation = new TranslateAnimation(0, Math.round(1.4*paddleWidth), 0, Math.round(1.1 * paddleWidth * Math.tan(leaderboardAngle)));
        RotateAnimation leaderboardRotation = new RotateAnimation(0, Math.round(leaderboardAngle*180/Math.PI), leaderboardImageView.getWidth()/2.0f, leaderboardImageView.getHeight()/2.0f);
        leaderboardAnimationSet.addAnimation(leaderboardTranslation);
        leaderboardAnimationSet.addAnimation(leaderboardRotation);
        leaderboardAnimationSet.setDuration(animationDuration);
        leaderboardAnimationSet.setFillAfter(true);



        quickgameImageView.startAnimation(quickgameRotation);
        leaderboardImageView.startAnimation(leaderboardAnimationSet );
        theplayersImageView.startAnimation(thePlayerAnimationSet);


    }

    private void turnFirstPaddle() {
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isPaddleQuickGameBlack) {
                    isPaddleQuickGameBlack = false;
                    quickgameImageView.setImageDrawable(getResources().getDrawable(R.drawable.paddle_quickgame));
                } else {
                    isPaddleQuickGameBlack = true;
                    quickgameImageView.setImageDrawable(getResources().getDrawable(R.drawable.paddle_quickgame_active));

                }
                Rotation3dAnimation rotationSuite = null;
                if (isPaddleQuickGameBlack) {
                    rotationSuite = new Rotation3dAnimation(-90, 0, quickgameImageView.getWidth() / 2.0f, quickgameImageView.getHeight() / 2.0f, 0, true);
                    rotationSuite.setDuration(200);
                } else {
                    rotationSuite = new Rotation3dAnimation(90, 0, quickgameImageView.getWidth() / 2.0f, quickgameImageView.getHeight() / 2.0f, 0, true);
                    rotationSuite.setDuration(200);
                }
                quickgameImageView.startAnimation(rotationSuite);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        if (isPaddleQuickGameBlack) {
            applyRotation(quickgameImageView, 0, -90, listener);
        } else {
            applyRotation(quickgameImageView, 0, 90, listener);
        }


    }

    private void turnSecondPaddle() {
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isPaddleKnockOutBlack) {
                    isPaddleKnockOutBlack = false;
                    knockoutImageView.setImageDrawable(getResources().getDrawable(R.drawable.paddle_knockout));
                } else {
                    isPaddleKnockOutBlack = true;
                    knockoutImageView.setImageDrawable(getResources().getDrawable(R.drawable.paddle_knockout_active));

                }

                Rotation3dAnimation rotationSuite = null;
                if (isPaddleQuickGameBlack) {
                    rotationSuite = new Rotation3dAnimation(-90, 0, knockoutImageView.getWidth() / 2.0f, knockoutImageView.getHeight() / 2.0f, 0, true);
                    rotationSuite.setDuration(200);
                } else {
                    rotationSuite = new Rotation3dAnimation(90, 0, knockoutImageView.getWidth() / 2.0f, knockoutImageView.getHeight() / 2.0f, 0, true);
                    rotationSuite.setDuration(200);
                }

                knockoutImageView.startAnimation(rotationSuite);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        if (isPaddleKnockOutBlack) {
            applyRotation(knockoutImageView, 0, -90, listener);
        } else {
            applyRotation(knockoutImageView, 0, 90, listener);
        }

    }

    private void applyRotation(final View view, float start, float end, Animation.AnimationListener listener) {
        // Find the center of the container
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotation3dAnimation rotation =
                new Rotation3dAnimation(start, end, centerX, centerY, 0, true);
        rotation.setDuration(200);
        rotation.setAnimationListener(listener);

        view.startAnimation(rotation);
    }
}
