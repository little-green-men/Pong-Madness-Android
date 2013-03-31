package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.littleGreenMan.Pong_Madness.R;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/30/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class CellAddPlayer extends RelativeLayout implements TextView.OnEditorActionListener, View.OnClickListener{


    private Context context;
    private EditText editText;
    private RelativeLayout mainLayout;
    private OnClickListener clickListenerToAddplayer;

    public CellAddPlayer(Context context) {
        super(context);
        this.context = context;
        setup();
    }

    public void setClickListenerToAddplayer(OnClickListener listener) {
        this.clickListenerToAddplayer = listener;
    }

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainLayout = (RelativeLayout)inflater.inflate(R.layout.cell_add_player, this);
        mainLayout.setOnClickListener(this);


        editText = (EditText) mainLayout.findViewById(R.id.cell_add_edittext);
        editText.setOnEditorActionListener(this);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (mainLayout.getBackground() == context.getResources().getDrawable(R.drawable.player_add_button)
                && !v.getText().toString().trim().isEmpty()) {
            mainLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.player_add));
        } else if (mainLayout.getBackground() == context.getResources().getDrawable(R.drawable.player_add)
                && v.getText().toString().trim().isEmpty()) {
            mainLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.player_add_button));
        }
        return true;
    }

    public String getEditTextName() {
        return editText.getText().toString();
    }


    @Override
    public void onClick(View v) {

        if (!editText.isFocused()) {
            editText.requestFocus();
        } else if (!editText.getText().toString().trim().isEmpty()){
            clickListenerToAddplayer.onClick(this);
        }
    }
}
