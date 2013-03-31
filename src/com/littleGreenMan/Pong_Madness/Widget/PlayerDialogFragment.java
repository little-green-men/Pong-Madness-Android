package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.littleGreenMan.Pong_Madness.R;
import com.littleGreenMan.Pong_Madness.client.PlayerClient;
import com.littleGreenMan.Pong_Madness.model.Player;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerDialogFragment extends DialogFragment implements View.OnClickListener {

    static private Context context;
    private Player player;
    private Button edit;
    private Button save;
    private ImageView lefthand;
    private ImageView righthand;
    private EditText company;
    private EditText email;
    private PlayerStat playerStat;

    private LinearLayout lytToSave;
    private LinearLayout lytToEdit;

    private ViewSwitcher viewSwitcher;
    public static PlayerDialogFragment newInstance(Context context) {
        PlayerDialogFragment frag = new PlayerDialogFragment();
        PlayerDialogFragment.context = context;
        return frag;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("aaa");
        View v = inflater.inflate(R.layout.player_dialog, container, true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        edit = (Button) v.findViewById(R.id.player_dialog_button_edit);
        save = (Button) v.findViewById(R.id.player_dialog_button_save);
        lefthand = (ImageView) v.findViewById(R.id.player_dialog_imageview_lefthand);
        righthand = (ImageView) v.findViewById(R.id.player_dialog_imageview_righthand);
        lytToEdit = (LinearLayout) v.findViewById(R.id.player_dialog_layout_toedit);
        lytToSave = (LinearLayout) v.findViewById(R.id.player_dialog_layout_tosave);
        email = (EditText) v.findViewById(R.id.player_dialog_edittext_mail);
        company = (EditText) v.findViewById(R.id.player_dialog_edittext_company);
        playerStat = (PlayerStat) v.findViewById(R.id.player_dialog_playerstat);
        viewSwitcher = (ViewSwitcher) v.findViewById(R.id.player_dialog_switcher);

        playerStat.setPlayerAndUpdate(player);

        edit.setOnClickListener(this);
        save.setOnClickListener(this);
        lefthand.setOnClickListener(this);
        righthand.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View v) {
        if (R.id.player_dialog_button_edit == v.getId()) {
            viewSwitcher.showNext();
            edit.setVisibility(View.GONE);
            save.setVisibility(View.VISIBLE);
            //viewSwitcher.setInAnimation(context,android.R.anim.slide_out_right);
            //viewSwitcher.setOutAnimation(context, android.R.anim.slide_in_left);
        } else if (R.id.player_dialog_button_save == v.getId()) {
            viewSwitcher.showPrevious();
            edit.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
            player.setEmail(email.getText().toString().trim());
            player.setCompany(company.getText().toString().trim());
            if (righthand.getBackground() == getResources().getDrawable(R.drawable.edit_handedness_right_active)) {
                player.setHandedness("R");
            } else if (lefthand.getBackground() == getResources().getDrawable(R.drawable.edit_handedness_left_active)) {
                player.setHandedness("L");
            }
            PlayerClient.updatePlayer(player);
            refreshPlayerDialog();
            //viewSwitcher.setInAnimation(context,android.R.anim.slide_in_left);
            //viewSwitcher.setOutAnimation(context, android.R.anim.slide_out_right);
        } else if (R.id.player_dialog_imageview_righthand == v.getId()) {
            if (v.getBackground() != getResources().getDrawable(R.drawable.edit_handedness_right_active)) {
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_right_active));
                lefthand.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_left_inactive));
            }
        }else if (R.id.player_dialog_imageview_lefthand == v.getId()) {
            if (v.getBackground() != getResources().getDrawable(R.drawable.edit_handedness_left_active)) {
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_left_active));
                righthand.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_handedness_right_inactive));
            }

        }
    }

    private void refreshPlayerDialog() {
    }
}
