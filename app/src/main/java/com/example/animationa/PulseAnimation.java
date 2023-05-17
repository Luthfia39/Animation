package com.example.animationa;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class PulseAnimation extends View {
    private float mRadius;
    private final Paint paint = new Paint();
    private final static int COLOR_ADJUSTER = 5;
    private float mX, mY;
    // animation duration (4 second)
    private static final int ANIMATION_DURATION = 4000;
    // animation delay (1 second)
    private static final int ANIMATION_DELAY = 1000;
    //
    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();

    // animation will playing when size changed
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // grow animation
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this, "radius",
                0, getWidth());
        // set duration animation
        growAnimator.setDuration(ANIMATION_DURATION);
        //
        growAnimator.setInterpolator(new LinearInterpolator());

        // create shrink animation
        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius",
                getWidth(), 0);
        // set duration animatio
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        //
        shrinkAnimator.setInterpolator(new LinearInterpolator());
        //
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        // create repeat animation
        ObjectAnimator repeatAnimation = ObjectAnimator.ofFloat(
                this, "radius", 0, getWidth()
        );
        repeatAnimation.setStartDelay(ANIMATION_DELAY);
        repeatAnimation.setDuration(ANIMATION_DURATION);
        repeatAnimation.setRepeatCount(3);
        repeatAnimation.setRepeatMode(ObjectAnimator.RESTART);

        // play animation
        // mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        mPulseAnimatorSet.play(repeatAnimation).after(shrinkAnimator);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // get the x and y when the view was touched
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            mX = event.getX();
            mY = event.getY();
            if (mPulseAnimatorSet != null && mPulseAnimatorSet.isRunning()){
                mPulseAnimatorSet.cancel();
            }
            mPulseAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(float Radius) {
        // get radius
        mRadius = Radius;
        // set color
        paint.setColor(Color.GREEN + (int) Radius/COLOR_ADJUSTER);
        invalidate();
    }

    public PulseAnimation(Context context) {
        super(context);
    }

    public PulseAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
