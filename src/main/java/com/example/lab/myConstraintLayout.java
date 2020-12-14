package com.example.fit2081lab1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class myConstraintLayout extends ConstraintLayout {
    public myConstraintLayout(Context context) {
        super(context);
    }

    public myConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    boolean value = super.onTouchEvent(event);
    return value;

    }
}

