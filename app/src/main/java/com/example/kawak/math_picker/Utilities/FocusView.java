package com.example.kawak.math_picker.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;

import com.example.kawak.math_picker.R;

public class FocusView extends View {
    private Paint mPaint;
    private Paint mStrokePaint;
    private Path mPath = new Path();

    public FocusView(Context context) {
        super(context);
        initPaints();
    }

    public FocusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public FocusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#40000000"));

        mStrokePaint = new Paint();
        mStrokePaint.setColor(getResources().getColor(R.color.colorPrimary));
        mStrokePaint.setStrokeWidth(2);
        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();

        float radius = 0;
        float strokeWidth = 0;
        if (canvas.getWidth() < canvas.getHeight()) {
            radius = canvas.getWidth() / 2 - 10;
            strokeWidth = canvas.getHeight();
        } else {
            radius = canvas.getHeight() / 2 - 10;
            strokeWidth = (canvas.getWidth() - canvas.getHeight())/2;
        }

        mPaint.setStrokeWidth(strokeWidth);
        //RectF rect = new RectF(90, 170, 1000, 400);
        float rds[] = new float[]{2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f};
        if(android.os.Build.VERSION.SDK_INT >= 21) {
            mPath.addRoundRect(60, 170, 670, 400, rds, Path.Direction.CW);
        }
        //mPath.addCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, radius, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        //canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, radius, mStrokePaint);
        canvas.drawRect(new RectF(60, 170, 670, 400), mStrokePaint);

        canvas.drawPath(mPath, mPaint);
    }
}
