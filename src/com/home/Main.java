package com.home;

import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class Main extends Activity implements OnTouchListener
{
    private ViewFlipper flipper = null;
    private float fromPosition;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouts[] = new int[]{ R.layout.first, R.layout.second, R.layout.third, R.layout.fourth };
        for (int layout : layouts)
            flipper.addView(inflater.inflate(layout, null));
    }

    public boolean onTouch(View view, MotionEvent event)
    {
        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            fromPosition = event.getX();
            break;
        case MotionEvent.ACTION_UP:
            float toPosition = event.getX();
            if (fromPosition > toPosition)
            {
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
                flipper.showNext();
            }
            else if (fromPosition < toPosition)
            {
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
                flipper.showPrevious();
            }
        default:
            break;
        }
        return true;
    }
}