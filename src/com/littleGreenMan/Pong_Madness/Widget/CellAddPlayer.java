package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class CellAddPlayer extends RelativeLayout implements View.OnTouchListener, View.OnFocusChangeListener{


    private Context context;
    private EditText editText;
    private RelativeLayout mainLayout;
    private View parent;
    private View scrollParent;
    private OnClickListener clickListenerToAddplayer;

    public CellAddPlayer(Context context, View scrollView, View parentView) {
        super(context);
        this.context = context;
        this.parent = parentView;
        this.scrollParent = scrollView;
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
        mainLayout.setOnTouchListener(this);
        parent.setOnTouchListener(this);
        scrollParent.setOnTouchListener(this);
    }


    public String getEditTextName() {
        return editText.getText().toString();
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

    public void makeEditTextEmpty() {
        editText.setText(null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
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

        } else if (R.id.theplayers_scrollview == v.getId()) {
            mainLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.player_add));
            InputMethodManager imm = (InputMethodManager)context.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        }
        return true;
    }
}
