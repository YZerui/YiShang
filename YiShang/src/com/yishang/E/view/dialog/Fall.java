package com.yishang.E.view.dialog;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lee on 2014/7/31.
 */
public class Fall extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 5, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view,"scaleY",5,1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

        );
    }
}
