package com.exercise.test;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class ArcView extends View {

    private static final String TAG = "tag";
    private Paint paint;
    private int mHeight, mWidth;
    private int angel;/*弧形的角度*/
    private RectF oval;/*绘制弧形使用*/
    private Paint txPaint;
    private int denominator = 30;/*分母*/
    private int numerator;/*分子*/
    private Paint linePaint;/*绘制分割线*/
    private Rect boundRect;/*计算分子的长宽使用*/
    private float textSize;

    /*暂时只使用代码的方式创建
    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }*/

    public ArcView(Context context) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);

        txPaint = new Paint();
        txPaint.setAntiAlias(true);
        txPaint.setColor(Color.WHITE);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setAntiAlias(true);

        boundRect = new Rect();
        oval = new RectF();
    }

    /*
        * w:当前控件的宽
         * h:当前控件的高
         *
         * oldw:控件之前的宽
         * oldh:控件之前的高
        * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w >= h) {
            w = h;
        }

        mWidth = mHeight = w;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        calcAngle();
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
        calcAngle();
    }

    private void calcAngle() {
        if (denominator == 0) {
            return;
        }
        this.angel = (int) (numerator * 360 / denominator);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);/*画布透明*/

        int padding = 10;
        oval.left = padding;
        oval.top = padding;
        oval.right = mWidth - padding;
        oval.bottom = mHeight - padding;

        textSize = mHeight / 3;
        //设置弧性的宽度
        paint.setStrokeWidth(mWidth / 20);
        canvas.drawArc(oval, 360, angel, false, paint);

        txPaint.setTextSize(textSize);

        if (numerator > 9) {
            txPaint.getTextBounds(numerator + "", 0, 2, boundRect);
        } else {
            txPaint.getTextBounds(numerator + "", 0, 1, boundRect);
        }

        int height = boundRect.height();
        int width = boundRect.width();

        float numeratorEndX = mWidth / 2 + 20;/*分子绘制的结束X坐标*/
        float numeratorStartX = numeratorEndX - width;/*分子开始绘制的X坐标*/

        //绘制分子
        float numeratorX = mWidth / 2 - width / 2 - 20;
        float numeratorY = mHeight / 2 + height / 2;
        canvas.drawText(numerator + "", numeratorStartX, numeratorY, txPaint);

        float lineHeight = mHeight / 7;/*定义的一个常量，表示分隔线的高度*/
        //分割线
//        canvas.drawLine(this.mWidth / 2 + 40, this.mHeight / 2 + lineHeight, numeratorEndX + lineHeight, this.mHeight / 2, linePaint);
        canvas.drawLine(numeratorStartX + width + 10, mHeight / 2 + lineHeight, numeratorEndX + lineHeight, mHeight / 2, linePaint);

        //绘制分母   分母的大小为分子的1/4
        txPaint.setTextSize(textSize / 4);
        canvas.drawText(denominator + "", numeratorEndX + lineHeight, mHeight / 2 + lineHeight, txPaint);
    }
}
