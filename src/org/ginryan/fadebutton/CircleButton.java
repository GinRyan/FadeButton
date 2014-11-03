package org.ginryan.fadebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class CircleButton extends Button {
	/**
	 * 圆心x坐标
	 */
	private float centerX;
	/**
	 * 圆心y坐标
	 */
	private float centerY;
	/**
	 * 半径
	 */
	private float radius = 80f;
	private Paint paint;
	/**
	 * 默认颜色
	 */
	private int mNormalColor = Color.GRAY;
	private int mPressedColor = Color.TRANSPARENT;

	public CircleButton(Context context) {
		super(context);
		init();
	}

	public CircleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void setTransitionColor(int normalColor, int pressedColor) {
		this.mNormalColor = normalColor;
		this.mPressedColor = pressedColor;
		setBackgroundColor(mNormalColor);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(mPressedColor);
		paint.setStyle(Style.FILL_AND_STROKE);// 描边
		// paint.setStrokeWidth(strokeWidth);
		paint.setAlpha(255);// 设置透明度0~255 0全透明
		this.radius = 0;
		// rectF = new RectF(centerX - radius, centerY - radius, centerX +
		// radius, centerY + radius);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			centerX = event.getX();
			centerY = event.getY();
			// 重新初始化画笔
			init();
			removeCallbacks(runner);
			// 刷新状态
			invalidateState();
			break;
		}
		return super.onTouchEvent(event);
	}

	Runnable runner = new Runnable() {

		@Override
		public void run() {
			invalidateState();
		}
	};

	protected void invalidateState() {
		// 半径增加
		radius += 13;
		int step = 10;
		int nextAlpha = 0;

		nextAlpha = paint.getAlpha() - step;
		if (radius >= getWidth()) {
			if (nextAlpha <= step) {
				nextAlpha = 0;
			}
			paint.setAlpha(nextAlpha);
		}
		// 更新页面执行onDraw()
		invalidate();
		if (paint.getAlpha() != 0) {
			postDelayed(runner, 2);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(mNormalColor);//先绘制一层颜色，看看有没有问题
		canvas.drawCircle(centerX, centerY, radius, paint);
		// canvas.drawRoundRect(rectF, 0, 0, paint);
		super.onDraw(canvas);
	}

}
