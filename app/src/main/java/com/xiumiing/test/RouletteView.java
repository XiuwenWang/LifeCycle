package com.xiumiing.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * ----------BigGod be here!----------/
 * ***┏┓******┏┓*********
 * *┏━┛┻━━━━━━┛┻━━┓*******
 * *┃             ┃*******
 * *┃     ━━━     ┃*******
 * *┃             ┃*******
 * *┃  ━┳┛   ┗┳━  ┃*******
 * *┃             ┃*******
 * *┃     ━┻━     ┃*******
 * *┃             ┃*******
 * *┗━━━┓     ┏━━━┛*******
 * *****┃     ┃神兽保佑*****
 * *****┃     ┃代码无BUG！***
 * *****┃     ┗━━━━━━━━┓*****
 * *****┃              ┣┓****
 * *****┃              ┏┛****
 * *****┗━┓┓┏━━━━┳┓┏━━━┛*****
 * *******┃┫┫****┃┫┫********
 * *******┗┻┛****┗┻┛*********
 * ━━━━━━神兽出没━━━━━━
 * 版权所有：个人
 * 作者：Created by a.wen.
 * 创建时间：2018/6/23
 * Email：13872829574@163.com
 * 内容描述：
 * 修改人：a.wen
 * 修改时间：${DATA}
 * 修改备注：
 * 修订历史：1.0
 */
public class RouletteView extends FrameLayout {

    private Paint p;
    private float X;
    private float Y;
    private float firstX;
    private float firstY;
    private float childViewSize = 100;
    private float marginOutRoulette = 40;
    private double angle = 30;


    public RouletteView(Context context) {
        super(context);
        init();
    }

    public RouletteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RouletteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#00000000"));
        p = new Paint();
        p.setColor(Color.RED);// 设置红色
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        for (int i = 0; i < 12; i++) {
            CircleImageView child = new CircleImageView(getContext());
            child.setLayoutParams(new LayoutParams(150, 150));
            Drawable myIcon = getResources().getDrawable(R.drawable.ic_launcher);
            child.setImageDrawable(myIcon);
            addView(child);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getMode(heightMeasureSpec)));
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        X = (r - l) / 2;
        Y = (b - t) / 2;
        firstX = (childViewSize / 2 + marginOutRoulette);
        firstY = getMeasuredWidth() / 2;
        float childRadius = getMeasuredWidth() / 2 - (childViewSize / 2 + marginOutRoulette);
        for (int i = 0; i < getChildCount(); i++) {
            double childAngle = i * angle;
            double childHeight = Math.sin(Math.toRadians(childAngle)) * childRadius;
            double childWidth = Math.cos(Math.toRadians(childAngle)) * childRadius;

            View childView = getChildAt(i);
            // 获取在onMeasure中计算的视图尺寸
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();

            childView.layout((int) (X + childWidth) - (measuredWidth / 2),
                    (int) (Y - childHeight) - (measureHeight / 2),
                    (int) (X + childWidth) + measuredWidth / 2,
                    (int) (Y - childHeight) + (measureHeight / 2));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, p);// 大圆
    }

    float y;
    float x;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                y = event.getY();
                x = event.getX();
                double degrees = Math.toDegrees(Math.atan( (Y - event.getY()) / (event.getX() - X))) - Math.toDegrees(Math.atan( (Y -y)/( x - X)));
                setRotation((float) degrees);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }


    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(final float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
