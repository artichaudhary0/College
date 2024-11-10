package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private Paint paint;
    private Bitmap bitmap;
    private float x = 100, y = 100;
    private ValueAnimator animator;

    // Constructors
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize the Paint object
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);

        // Load a scaled-down bitmap to prevent memory issues
        bitmap = loadScaledBitmap(R.drawable.imagee);

        // Set up animation for moving the circle horizontally
        animator = ValueAnimator.ofFloat(100, 800);
        animator.setDuration(3000); // 3 seconds
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();
                invalidate(); // Redraw the view
            }
        });
        animator.start(); // Start the animation
    }

    // Method to load a scaled bitmap to reduce memory usage
    private Bitmap loadScaledBitmap(int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4; // Scale down by a factor of 4
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw a moving circle
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 50, paint);

        // Draw text
        paint.setColor(Color.BLACK);
        canvas.drawText("Moving circle", 100, 300, paint);

        // Draw the bitmap below the text
        canvas.drawBitmap(bitmap, 100, 350, paint);

        // Draw a green rectangle for an additional shape
        paint.setColor(Color.GREEN);
        RectF rect = new RectF(100, 500, 500, 600);
        canvas.drawRect(rect, paint);
    }
}
