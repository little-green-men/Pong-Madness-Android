package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
public class CellAddPlayer extends RelativeLayout implements View.OnClickListener, View.OnFocusChangeListener{


    private Context context;
    private EditText editText;
    private RelativeLayout mainLayout;
    private View parent;
    private OnClickListener clickListenerToAddplayer;

    public CellAddPlayer(Context context, View parentView) {
        super(context);
        this.context = context;
        this.parent = parentView;
        setup();
    }

    public void setClickListenerToAddplayer(OnClickListener listener) {
        this.clickListenerToAddplayer = listener;
    }

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.cell_add_player, this, true);


        mainLayout = (RelativeLayout) layout.findViewById(R.id.cell_add_lyt_main);
        editText = (EditText) layout.findViewById(R.id.cell_add_edittext);
        editText.setOnFocusChangeListener(this);
        //mainLayout.setOnFocusChangeListener(this);
        mainLayout.setOnClickListener(this);
        parent.setOnClickListener(this);

    }


    public String getEditTextName() {
        return editText.getText().toString();
    }


    @Override
    public void onClick(View v) {
        if (R.id.cell_add_lyt_main == v.getId()) {
            if (!editText.getText().toString().trim().isEmpty()){
                clickListenerToAddplayer.onClick(this);
                mainLayout.requestFocus();

            } else {
                if (!editText.hasFocus()) {
                    editText.requestFocus();
                }
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
            }

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (R.id.cell_add_edittext == v.getId()) {
            if (hasFocus) {
                mainLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.player_add_button));
            } else {
                mainLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.player_add));
                InputMethodManager imm = (InputMethodManager)context.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (!editText.getText().toString().trim().isEmpty()){
                    clickListenerToAddplayer.onClick(this);
                }
            }
        }
    }
}
