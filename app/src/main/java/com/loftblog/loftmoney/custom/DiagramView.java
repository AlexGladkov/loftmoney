package com.loftblog.loftmoney.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.loftblog.loftmoney.R;

public class DiagramView extends View {

    private float mExpences;
    private float mIncomes;

    private Paint expensePaint = new Paint();
    private Paint incomePaint = new Paint();

    public DiagramView(Context context) {
        super(context);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void update(float expenses, float incomes) {
        mExpences = expenses;
        mIncomes = incomes;
        invalidate();
    }

    private void init() {
        expensePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        incomePaint.setColor(ContextCompat.getColor(getContext(), R.color.apple_green));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float total = mExpences + mIncomes;

        float expenseAngle = 360f * mExpences / total;
        float incomeAngle = 360f * mIncomes / total;

        int space = 10;
        int size = Math.min(getWidth(), getHeight()) - space * 2;
        int xMargin = (getWidth() - size) / 2;
        int yMargin = (getHeight() - size) / 2;

        canvas.drawArc(xMargin - space, yMargin, getWidth() - xMargin - space,
                getHeight() - yMargin, 180 - expenseAngle / 2, expenseAngle,
                true, expensePaint);

        canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space,
                getHeight() - yMargin, 360 - incomeAngle / 2, incomeAngle,
                true, incomePaint);
    }
}
