package com.example.anime;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {

	Circle circle;
	FadeButton fadebutton;
	
	int normalColor = Color.parseColor("#8AD5F0");
	int pressedColor = Color.parseColor("#0099CC");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		circle = (Circle) findViewById(R.id.circle);
		fadebutton = (FadeButton) findViewById(R.id.fadebutton);
		circle.setTransitionColor(normalColor, pressedColor);
		fadebutton.setTransitionColor(normalColor, pressedColor);
	}

}
