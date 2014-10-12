package org.ginryan.fadebutton.demoui;

import org.ginryan.fadebutton.CircleButton;
import org.ginryan.fadebutton.FadeButton;
import org.ginryan.fadebutton.R;
import org.ginryan.fadebutton.R.id;
import org.ginryan.fadebutton.R.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	CircleButton circle;
	FadeButton fadebutton;

	ButtonClicked buttonClicked = new ButtonClicked();

	int normalColor = Color.parseColor("#8AD5F0");
	int pressedColor = Color.parseColor("#0099CC");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		circle = (CircleButton) findViewById(R.id.circle);
		fadebutton = (FadeButton) findViewById(R.id.fadebutton);
		circle.setTransitionColor(normalColor, pressedColor);
		fadebutton.setTransitionColor(normalColor, pressedColor);

		fadebutton.setOnClickListener(buttonClicked);
		circle.setOnClickListener(buttonClicked);
	}

	private final class ButtonClicked implements OnClickListener {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), v.getClass().getSimpleName() + " pressed", Toast.LENGTH_SHORT).show();
		}
	}
}
