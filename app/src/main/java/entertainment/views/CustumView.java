package entertainment.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import entertainment.simplyandroid.R;

public class CustumView extends View {
    private static final String TAG = CustumView.class.getSimpleName();
    private Rect mRectSquare;
    private Paint mPaintSquare;
    private int mSquareColor;
    private int mSquareSize;

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

        if (set == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustumView);
        mSquareColor = typedArray.getColor(R.styleable.CustumView_square_color, Color.GREEN);
        mSquareSize = typedArray.getDimensionPixelSize(R.styleable.CustumView_square_size, SQUARE_SIZE);

        mPaintSquare.setColor(mSquareColor);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;

        canvas.drawRect(mRectSquare, mPaintSquare);
    }

    public void swapColor() {
        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
    }
}
