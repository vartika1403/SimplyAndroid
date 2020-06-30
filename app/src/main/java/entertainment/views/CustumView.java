package entertainment.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import entertainment.simplyandroid.R;

public class CustumView extends View {
    private static final String TAG = CustumView.class.getSimpleName();
    private Rect mRectSquare;
    private Paint mPaintSquare;
    private Paint mPaintCircle;
    private int mSquareColor;
    private int mSquareSize;
    private float mCircleX;
    private float mCircleY;
    float radius = 150f;


    private static final int SQUARE_SIZE = 200;
    public CustumView(Context context) {
        super(context);
        init(null);
    }

    public CustumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (set == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustumView);
        mSquareColor = typedArray.getColor(R.styleable.CustumView_square_color, Color.GREEN);
        mSquareSize = typedArray.getDimensionPixelSize(R.styleable.CustumView_square_size, SQUARE_SIZE);

        mPaintSquare.setColor(mSquareColor);
        mPaintCircle.setColor(mSquareColor);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;

        // draw a rectangle
        canvas.drawRect(mRectSquare, mPaintSquare);

        //draw a circle
        if (mCircleY == 0f || mCircleX == 0f) {
            mCircleX = getWidth()/2;
            mCircleY = getHeight()/2;
        }
        canvas.drawCircle(mCircleX, mCircleY, radius, mPaintCircle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean value = super.onTouchEvent(motionEvent);

        Log.d(TAG, "onTouchEvent value, " + value);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "onTouchEvent action down");

                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "onTouchEvent action move");

                float x = motionEvent.getX();
                float y = motionEvent.getY();
                double dx = Math.pow(x-mCircleX, 2);
                double dy = Math.pow(y-mCircleY, 2);
                Log.d(TAG, "onTouchEvent action move 1 x, " + x + "y " + y + "dx, " + dx + " dy, " + dy);

                if (dx + dy < Math.pow(mCircleX, 2)) {
                    Log.d(TAG, "onTouchEvent action move 2 x, " + x + "y " + y + "dx, " + dx + " dy, " + dy);
                    mCircleX = x;
                    mCircleY = y;
                    postInvalidate();
                    return true;
                }
            }
        }
        return value;

    }

    public void swapColor() {
        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
    }
}
