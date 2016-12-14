package com.cygnet.customfonttext;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Hashtable;


public class CustomTextView extends TextView {
    private static final String TAG = "TextView";
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);
        setTypeface(getTypeFace(ctx, customFont));
        a.recycle();
    }

    /**
     * This method is used to get typeface from a path of font.
     *
     * @Name getTypeFace
     */
    public static Typeface getTypeFace(Context aContext, String assetFile) {
        synchronized (cache) {
            if (!cache.containsKey(assetFile)) {
                try {
                    Typeface t = Typeface.createFromAsset(aContext.getAssets(),
                            assetFile);
                    cache.put(assetFile, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get1 typeface '" + assetFile
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetFile);
        }
    }
}