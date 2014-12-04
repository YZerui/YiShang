package com.dialog.animstyle;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lee on 2014/7/31.
 */
public class FlipV extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", -180, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "translationY", 2400, 0).setDuration(mDuration)
        );
    }
}
