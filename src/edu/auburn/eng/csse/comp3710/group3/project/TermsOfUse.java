package edu.auburn.eng.csse.comp3710.group3.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class TermsOfUse extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms_of_use);
	}

	public void simulateBack(View view) {
		finish();
	}

}
