package com.bafoly.tutorials.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.bafoly.tutorials.R;


public class ProgressView extends View {

    float angle = 500f;

    float radius = 40;

    int insideColor;
    int outsideColor;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgress, 0, 0);

        try {
            // Retrieve the values from the TypedArray and store into
            // fields of this class.
            //
            // The R.styleable.CustomProgress_* constants represent the index for
            // each custom attribute in the R.styleable.CustomProgress array.
            insideColor = a.getColor(R.styleable.CustomProgress_colorInside,0xffff0000);
            outsideColor= a.getColor(R.styleable.CustomProgress_colorOutside,0xff0000ff);
            radius = a.getInt(R.styleable.CustomProgress_radius, 40);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }
        init();
    }

    Paint loaderPaint;
    ValueAnimator animator;

    private void init(){
        loaderPaint = new Paint(); // paint object will be used during drawing
        loaderPaint.setStyle(Paint.Style.STROKE); // this will draw line
        loaderPaint.setStrokeWidth(6f); // and the width of line is set here
        loaderPaint.setPathEffect(new DashPathEffect(new float[]{10, 15}, 0)); // it won't be straight line, it will be dashed line
        loaderPaint.setAntiAlias(true); // for smoother edges

        // value animator will be generating values between 0 to 360 with the duration of 10 seconds
        animator = new ValueAnimator().ofFloat(0,360).setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE); // it will repeat forever
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) { // here we listen the values of animator
                angle = (Float) valueAnimator.getAnimatedValue(); // we received a new value and we set the angle of our view
                invalidate(); // and calling the view to be re drawn

            }

        });
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // we will draw two circles. this will be the one at the outside

        canvas.save();
        // rotating the canvas with the latest position
        canvas.rotate(angle,getWidth() / 2, getHeight() / 2);
        loaderPaint.setColor(outsideColor);
        // drawing the outer circle
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, loaderPaint);
        canvas.restore();

        // preparing the canvas for the second circle
        canvas.save();
        // this will be rotated in reverse angle so we use the -angle here
        canvas.rotate(-angle, getWidth() / 2, getHeight() / 2);
        // updating the paint object with inside color
        loaderPaint.setColor(insideColor);
        // drawing the circle. This inner circle will have smaller radius. I randomly set it to -16 of outer circle
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius-16, loaderPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int finalWidth = -1;
        int finalHeight = -1;
        // Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            // Must be this size
            finalWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            finalWidth = Math.min(desiredWidth, widthSize);
        } else {
            // Be whatever you want
            finalWidth = desiredWidth;
        }

        // Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            // Must be this size
            finalHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            finalHeight = Math.min(desiredHeight, heightSize);
        } else {
            // Be whatever you want
            finalHeight = desiredHeight;
        }

        // MUST CALL THIS
        setMeasuredDimension(finalWidth, finalHeight);
    }
}
