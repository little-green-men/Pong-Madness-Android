/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.littleGreenMan.Pong_Madness.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * A layout that arranges its children in a grid.  The size of the
 * cells is set by the  method and the
 * android:cell_width and android:cell_height attributes in XML.
 * The number of rows and columns is determined at runtime.  Each
 * cell contains exactly one view, and they flow in the natural
 * child order (the order in which they were added, or the index
 * in .  Views can not span multiple cells.
 *
 * <p>This class was copied from the FixedGridLayout Api demo; see that demo for
 * more information on using the layout.</p>
 */
public class FixedGridLayout extends RelativeLayout {
    int mCellWidth;
    int mCellHeight;
    Context context;

    public FixedGridLayout(Context context) {
        super(context);
        this.context = context;
    }

    public void setCellWidth(int dp) {
        mCellWidth = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
        requestLayout();
    }

    public void setCellHeight(int dp) {
        mCellHeight = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth,
                MeasureSpec.AT_MOST);
        int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight,
                MeasureSpec.AT_MOST);
        setGravity(Gravity.RIGHT);

        int count = getChildCount();
        for (int index=0; index<count; index++) {
            final View child = getChildAt(index);
            child.measure(cellWidthSpec, cellHeightSpec);
        }
        // Use the size our parents gave us, but default to a minimum size to avoid
        // clipping transitioning children
        int minCount =  count > 5 ? count : 5;
        int row = 1;
        if (count % 5 == 0) {
            row = count/5;
        } else {
            row = count/5+1;
        }
        setMeasuredDimension(resolveSize(mCellWidth * minCount, widthMeasureSpec),
                mCellHeight * row);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cellWidth = mCellWidth;
        int cellHeight = mCellHeight;
        int columns = (r - l) / cellWidth;
        if (columns < 0) {
            columns = 1;
        }
        int x = 0;
        int y = 0;
        int i = 0;
        int count = getChildCount();
        for (int index=0; index<count; index++) {
            final View child = getChildAt(index);

            int w = child.getMeasuredWidth();
            int h = child.getMeasuredHeight();

            int left = x + ((cellWidth-w)/2);
            int top = y + ((cellHeight-h)/2);

            child.layout(left, top, left+w, top+h);
            if (i >= (columns-1)) {
                // advance to next row
                i = 0;
                x = 0;
                y += cellHeight;
            } else {
                i++;
                x += cellWidth;
            }
        }
    }
}

