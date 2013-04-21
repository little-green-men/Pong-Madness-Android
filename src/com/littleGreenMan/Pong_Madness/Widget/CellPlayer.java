package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.littleGreenMan.Pong_Madness.FontTools;
import com.littleGreenMan.Pong_Madness.R;
import com.littleGreenMan.Pong_Madness.delegate.SelectPlayerDelegate;
import com.littleGreenMan.Pong_Madness.model.Player;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellPlayer extends LinearLayout implements CompoundButton.OnCheckedChangeListener{

    public boolean isChecked() {
        return (checkbox.getVisibility() == View.INVISIBLE && checkbox.isChecked());
    }

    public static enum Mode {
       SELECTABLE,
       EDITABLE
    }

    private Context context;
    private ImageView picture;
    private TextView nameTv;
    private TextView sinceDateTv;
    private CheckBox checkbox;
    private LinearLayout mainLayout;
    private Mode mode;
    private SelectPlayerDelegate delegate;

    public Player getPlayer() {
        return player;
    }

    private Player player;

    private OnClickListener checkboxListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            checkbox.setChecked(!checkbox.isChecked());
        }
    };

    public CellPlayer(Context context, SelectPlayerDelegate delegate, Player player) {
        super(context);
        this.context = context;
        this.player = player;
        this.delegate = delegate;
        setup();
    }

    public CellPlayer(Context context, Player player) {
        super(context);
        this.context = context;
        this.player = player;
        this.delegate = null;
        setup();
    }

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainLayout = (LinearLayout)inflater.inflate(R.layout.cell_player, this);

        picture = (ImageView) mainLayout.findViewById(R.id.cell_player_picture);
        nameTv = (TextView) mainLayout.findViewById(R.id.cell_player_name);
        sinceDateTv = (TextView) mainLayout.findViewById(R.id.cell_player_date);
        checkbox = (CheckBox) mainLayout.findViewById(R.id.cell_player_checkbox);

        nameTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        sinceDateTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));

        checkbox.setOnCheckedChangeListener(this);

        nameTv.setText(player.getUserName());
        sinceDateTv.setText("Since " + player.getSinceDate());
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        switch (mode) {
            case SELECTABLE :
                checkbox.setVisibility(View.VISIBLE);
                mainLayout.setOnClickListener(checkboxListener);
            break;


        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (delegate != null) {
            delegate.onPlayerSelectedChanged(player, isChecked);
        }
    }

    public void setUnselectedCellsEnable(boolean isEnable) {
        if (isEnable) {
            mainLayout.setAlpha(1);
        } else {
            mainLayout.setAlpha(0.2f);
        }
    }
}
