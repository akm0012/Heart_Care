package edu.auburn.eng.csse.comp3710.group3.project;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class HeartGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Display display = getWindowManager().getDefaultDisplay();
		// Rect rectSize = new Rect();
		// display.getRectSize(rectSize);

		// Log.i("rect",rectSize.toString());
		setContentView(R.layout.heartgame);
	}



	@Override
	public void onPause() {
		super.onPause();
		Log.i("onPause", "onPause");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("onDestroy", "onDestroy");
	}

}
