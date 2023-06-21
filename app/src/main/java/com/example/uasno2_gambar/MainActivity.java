package com.example.uasno2_gambar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        //SOURCE CODE NOMOR 3
        AnimatorSet animatorPulseSet = new AnimatorSet();

        ObjectAnimator flipAnimation = ObjectAnimator.ofFloat(mImgView, "rotationY", 0f, 180f);
        flipAnimation.setDuration(1000); // Set the duration of the animation in milliseconds
        flipAnimation.setInterpolator(new AccelerateDecelerateInterpolator()); // Set the animation interpolator

        ObjectAnimator fadeInAnimation = ObjectAnimator.ofFloat(mImgView, "alpha", 0f, 1f);
        fadeInAnimation.setDuration(1500); // Set the duration of the animation in milliseconds

        ObjectAnimator fadeOutAnimation = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0f);
        fadeInAnimation.setDuration(1500); // Set the duration of the animation in milliseconds

        // Call the flip animation method
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorPulseSet.play(fadeInAnimation).before(flipAnimation);
                animatorPulseSet.play(fadeOutAnimation).after(flipAnimation);
                animatorPulseSet.start();
            }
        });
        //AKHIR SOURCE CODE NOMOR 3
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead(vWidth, vHeight);
        drawRightEye(vWidth, vHeight);
        drawLeftEye(vWidth, vHeight);
        drawEyeConnector(vWidth, vHeight);

    }

    private void drawHead(int vWidth, int vHeight) {
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        int radiusX = halfWidth - 200; // Horizontal radius
        int radiusY = halfHeight - 750; // Vertical radius

        RectF head = new RectF(
                halfWidth - radiusX,
                halfHeight - radiusY,
                halfWidth + radiusX,
                halfHeight + radiusY
        );
        mCanvas.drawOval(head, mHeadPaint);
    }

    private void drawRightEye(int vWidth, int vHeight) {
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        mCanvas.drawCircle(halfWidth-200, halfHeight,
                halfHeight/20, mCirclePaint);
    }

    private void drawLeftEye(int vWidth, int vHeight) {
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        mCanvas.drawCircle(halfWidth+200, halfHeight,
                halfHeight/20, mCirclePaint);
    }

    private void drawEyeConnector(int vWidth, int vHeight) {
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        mCanvas.drawRect(halfWidth-200, halfHeight+15,
                halfWidth+200, halfHeight-15, mCirclePaint);
    }
}