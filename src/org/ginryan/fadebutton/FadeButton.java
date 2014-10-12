package org.ginryan.fadebutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Button;

public class FadeButton extends Button {
	int durationMillis = 360;
	int normalColor = Color.parseColor("#8AD5F0");
	int pressedColor = Color.parseColor("#0099CC");
	private TransitionDrawable mTransitionDrawable;
	GestureDetector mGestureDetector = null;
	private OnGestureListener mListener = new GestureDetector.SimpleOnGestureListener() {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			postDelayed(new delayedReverseTransition(), durationMillis);
			return super.onSingleTapUp(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			mTransitionDrawable.reverseTransition(durationMillis);
			super.onLongPress(e);
		}

		@Override
		public boolean onDown(MotionEvent e) {
			mTransitionDrawable.startTransition(durationMillis);
			return super.onDown(e);
		}
	};

	public FadeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initSelfElements();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void initSelfElements() {
		mGestureDetector = new GestureDetector(getContext(), mListener);
		ColorDrawable normalStateColor = new ColorDrawable(normalColor);
		ColorDrawable pressedStateColor = new ColorDrawable(pressedColor);
		mTransitionDrawable = new TransitionDrawable(new Drawable[] { normalStateColor, pressedStateColor });
		if (Build.VERSION.SDK_INT > 16) {
			setBackground(mTransitionDrawable);
		} else {
			setBackgroundDrawable(mTransitionDrawable);
		}

	}

	public void setTransitionColor(int normalColor, int pressedColor) {
		this.normalColor = normalColor;
		this.pressedColor = pressedColor;
	}

	boolean hasReversedColor = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGestureDetector.onTouchEvent(event);
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_UP:
			// now do nothing
			break;
		}
		return super.onTouchEvent(event);
	}

	private final class delayedReverseTransition implements Runnable {

		@Override
		public void run() {
			mTransitionDrawable.reverseTransition(durationMillis);
		}
	}
}
