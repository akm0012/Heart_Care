package edu.auburn.eng.csse.comp3710.group3.project;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Splash extends Activity {

	AnimationDrawable ekgAnimation;
	MediaPlayer mediaPlayer;
	private final int SPLASH_DISPLAY_LENGTH = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		ImageView ekg = (ImageView) findViewById(R.id.imageView1);
		ekg.setBackgroundResource(R.drawable.ekg);
		ekgAnimation = (AnimationDrawable) ekg.getBackground();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(Splash.this,
						MainMenuActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mediaPlayer.release();
		mediaPlayer = null;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		ekgAnimation.start();

		if (hasFocus) {
			mediaPlayer = MediaPlayer.create(this, R.raw.ekg);
			mediaPlayer.setVolume((float) .125, (float) .125);
			mediaPlayer.start(); // no need to call prepare(); create() does
									// that for you

		}

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void clickAnimation(View view) {

		// ekgAnimation.start();

	}

}
