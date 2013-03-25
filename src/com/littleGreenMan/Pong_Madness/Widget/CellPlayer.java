package com.littleGreenMan.Pong_Madness.Widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.littleGreenMan.Pong_Madness.FontTools;
import com.littleGreenMan.Pong_Madness.R;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/24/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellPlayer extends LinearLayout {

    private Context context;
    private ImageView picture;
    private TextView nameTv;
    private TextView sinceDateTv;

    public CellPlayer(Context context) {
        super(context);
        this.context = context;
        setup();
    }

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainLayout = (LinearLayout)inflater.inflate(R.layout.cell_player, this);

        picture = (ImageView) mainLayout.findViewById(R.id.cell_player_picture);
        nameTv = (TextView) mainLayout.findViewById(R.id.cell_player_name);
        sinceDateTv = (TextView) mainLayout.findViewById(R.id.cell_player_date);

        nameTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
        sinceDateTv.setTypeface(FontTools.getTypefaceFromRessource(R.raw.brothers_bold, context));
    }
}
