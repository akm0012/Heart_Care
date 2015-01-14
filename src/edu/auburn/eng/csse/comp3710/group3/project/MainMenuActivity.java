package edu.auburn.eng.csse.comp3710.group3.project;

import android.view.View;

//my new change

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.heartmenu);
	}

	public void editProfilePressed(View view) {
		Intent intent = new Intent(this, EditProfile.class);
		startActivity(intent);
	}

	public void educatePressed(View view) {
		Intent intent = new Intent(this, AbcsAspirinActivity.class);
		startActivity(intent);
	}

	public void motivatePressed(View view) {
		Intent intent = new Intent(this, HeartGameActivity.class);
		startActivity(intent);
	}

	public void showTermsOfUse(View view) {
		Intent intent = new Intent(this, TermsOfUse.class);
		startActivity(intent);
	}

	//
}
