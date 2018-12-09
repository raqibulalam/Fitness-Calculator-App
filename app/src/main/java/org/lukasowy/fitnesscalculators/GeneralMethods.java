package org.lukasowy.fitnesscalculators;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Łukasz on 2017-11-22.
 */

public class GeneralMethods {


    Typeface textFont;

    //Set fonts for all activity
    public static void setFontToAllChilds(ViewGroup myMostParentLayout, Typeface tf) {
        int childCount = myMostParentLayout.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View child = myMostParentLayout.getChildAt(i);

            if (child instanceof ViewGroup)
                setFontToAllChilds((ViewGroup) child, tf);
            else if (child instanceof TextView)
                ((TextView) child).setTypeface(tf);
        }
    }

    // Round a double to 2 decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
