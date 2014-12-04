package com.dialog.animstyle;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lee on 2014/7/31.
 */
public class FlipH  extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", 90, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0,1)
//                ObjectAnimator.ofFloat(view, "rotationX", 180, 0).setDuration(mDuration)

        );
    }
}
