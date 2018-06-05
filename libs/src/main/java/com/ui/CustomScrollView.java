package com.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Phicos on 2017/2/23.
 */
public class CustomScrollView extends ScrollView {


    private YScrollDetector mYScrollDetector;

    private Context context;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mYScrollDetector = new YScrollDetector();
        this.context = context;
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mYScrollDetector = new YScrollDetector();
        this.context = context;
    }

    public CustomScrollView(Context context) {
        super(context);
        mYScrollDetector = new YScrollDetector();
        this.context = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && new GestureDetector(context, mYScrollDetector).onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            double angle = Math.atan2(Math.abs(distanceY), Math.abs(distanceX));
            System.out.println("angle-->" + (180 * angle) / Math.PI);
            if ((180 * angle) / Math.PI < 180) {
                return false;
            }
            return true;
        }
    }
}
