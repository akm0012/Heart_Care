package edu.auburn.eng.csse.comp3710.group3.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class FramInfoDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fram_info_dialog);
	}

	public void simulateBack(View view) {
		finish();
	}

}
