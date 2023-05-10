package com.example.fifthhomeworkoneunitfour;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CatFlight extends View {
    private float cPlaneY;
    private float cTouchY;
    private float cMidY;

    public CatFlight(Context context, AttributeSet attrs) {
        super(context, attrs);
        cMidY = getHeight() / 2;
        cPlaneY = cMidY;
        cTouchY = cMidY;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float diffY = y - cTouchY;
                cPlaneY += diffY;
                if (cPlaneY < 0) {
                    cPlaneY = 0;
                } else if (cPlaneY > getHeight()) {
                    cPlaneY = getHeight();
                }
                cTouchY = y;
                break;
            case MotionEvent.ACTION_UP:
                cPlaneY = cMidY;
                break;
        }
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
    }
}

