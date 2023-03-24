package com.bokaro.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Asrar Ali on 21/07/2022.
 */
public class CustomTextView extends TextView
{

    public CustomTextView(Context context) {

        super(context);
        applyCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }
    private void applyCustomFont(Context context) {
        //Typeface customFont = FontCache.getTypeface("SF-Pro-Text-Regular.otf", context);
        Typeface customFont = FontCache.getTypeface("Poppins-Light.otf", context);
        setTypeface(customFont);
    }
}
