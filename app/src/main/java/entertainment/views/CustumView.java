package entertainment.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustumView extends View {
    private static final String TAG = CustumView.class.getSimpleName();
    private Rect mRectSquare;
    private Paint mPaintSquare;

    private static final int SQUARE_SIZE = 100;
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
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectSquare.left = 10;
        mRectSquare.top = 10;
        mRectSquare.right = 10 + SQUARE_SIZE;
        mRectSquare.bottom = 10 + SQUARE_SIZE;

        mPaintSquare.setColor(Color.GREEN);
        canvas.drawRect(mRectSquare, mPaintSquare);
    }
}
