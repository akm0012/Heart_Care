package edu.auburn.eng.csse.comp3710.group3.project;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.*;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class HeartView extends View {

	// Square 1
	BitmapDrawable heartBitmap;
	BitmapDrawable hamburgerMenuDrawable;
	BitmapDrawable healthyDrawable;
	// BitmapDrawable(Resources res, Bitmap bitmap)
	ShapeDrawable mShape1 = new ShapeDrawable();

	Rect mRect1 = new Rect(128 * 0, 128 * 0, 128 * 1, 128 * 1);
	// Rect mRect1 = new Rect(LEFT,TOP,RIGHT,BOTTOM);
	boolean mOnRect1 = false;
	boolean mCollisionDetected = false;
	private float mPosX;
	private float mPosY;
	Toast mToast;
	boolean doOnce = true;
	boolean doOnce2 = true;
	int score = 0;
	TextView scoreView;
	MediaPlayer mediaPlayer;
	MediaPlayer mediaPlayer2;
	MediaPlayer mediaPlayer3;
	Context context;
	boolean gameOver = false;

	// Square 2
	ShapeDrawable mShape2 = new ShapeDrawable();
	Rect mRect2 = new Rect(128 * 0, 128 * 2, 128 * 1, 128 * 3);
	boolean mOnRect2 = false;
	private int height;
	private int width;
	Random r;

	// Square 3
	Rect mRect3 = new Rect(128 * 3, 128 * 2, 128 * 4, 128 * 3);

	private float mLastTouchY;
	private float mLastTouchX;

	private Rect generateValidRectangle() {

		int randomX = r.nextInt(width - 128);
		int randomY = r.nextInt(height - 384);
		return new Rect(0 + randomX, 0 + randomY, 128 + randomX, 128 + randomY);
	}

	public HeartView(Context context) {
		this(context, null, 0);
	}

	public HeartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HeartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mShape1.getPaint().setColor(0xff74AC23);
		mShape2.getPaint().setColor(Color.BLUE);
		this.setBackgroundColor(Color.BLACK);
		heartBitmap = (BitmapDrawable) this.getResources().getDrawable(
				R.drawable.heart_small);
		hamburgerMenuDrawable = (BitmapDrawable) this.getResources()
				.getDrawable(R.drawable.hamburger_menu_small);
		healthyDrawable = (BitmapDrawable) this.getResources().getDrawable(
				R.drawable.healthy_small);

		this.context = context;
		// no need to call prepare(); create() does that for you
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);

		if (hasWindowFocus) {
			mToast = Toast
					.makeText(
							this.getContext(),
							"How to Play: Drag the heart to collect healthy food - Get 2,000 points to win!",
							Toast.LENGTH_LONG);
			mToast.show();
		}

		this.height = this.getHeight();
		this.width = this.getWidth();
		r = new Random(57474);
		mRect2.set(generateValidRectangle());
		mRect3.set(generateValidRectangle());

		if (mediaPlayer3 == null) {
			mediaPlayer3 = MediaPlayer.create(context.getApplicationContext(),
					R.raw.gamemusic);
			mediaPlayer3.setVolume((float) .5, (float) .5);
			mediaPlayer3.setLooping(true);
			mediaPlayer3.start();
		}
		if (mediaPlayer2 == null) {

			mediaPlayer2 = MediaPlayer.create(context.getApplicationContext(),
					R.raw.error);
			mediaPlayer2.setVolume((float) .125, (float) .125);
		}
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(context.getApplicationContext(),
					R.raw.bleep);
			mediaPlayer.setVolume((float) .125, (float) .125);
		}

		if (!hasWindowFocus) {
			mediaPlayer.release();
			mediaPlayer = null;
			mediaPlayer2.release();
			mediaPlayer2 = null;
			mediaPlayer3.release();
			mediaPlayer3 = null;
			mToast.cancel();
		}

	}

	public void updateScore(int scoreChange) {
		score += scoreChange;
		if (score >= 2000) {

			gameOverVictory();
		}
	}

	public void gameOverVictory() {
		if (!gameOver) {
			gameOver = true;
			mToast = Toast.makeText(this.getContext(),
					"You Win! Keep Eating healthy! (Press Back to Quit)",
					Toast.LENGTH_LONG);
			mToast.show();
		}
	}

	public boolean onTouchEvent(MotionEvent e) {
		final int action = e.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (mRect1.contains((int) e.getX(), (int) e.getY())) {
				Log.i("TEST", "YES");
				final float x = (int) e.getX();
				final float y = (int) e.getY();
				mOnRect1 = true;
				mLastTouchX = x;
				mLastTouchY = y;
				mPosX = mRect1.left;
				mPosY = mRect1.top;

			}

			break;
		case MotionEvent.ACTION_MOVE:
			if (mOnRect1) {
				if (Rect.intersects(mRect1, mRect2) && doOnce) {

					doOnce = false;
					mRect2.set(generateValidRectangle());
					mRect3.set(generateValidRectangle());
					this.updateScore(-100);
					mediaPlayer2.start();

				}

				if (!Rect.intersects(mRect1, mRect2) && !doOnce) {

					doOnce = true;
				}

				if (Rect.intersects(mRect1, mRect3) && doOnce2) {

					doOnce2 = false;
					mRect3.set(generateValidRectangle());
					mRect2.set(generateValidRectangle());
					mediaPlayer.start();
					this.updateScore(100);
				}

				if (!Rect.intersects(mRect1, mRect3) && !doOnce2) {

					doOnce2 = true;
				}

				final float x = (int) e.getX();
				final float y = (int) e.getY();

				final float dx = x - mLastTouchX;
				final float dy = y - mLastTouchY;

				mPosX = mPosX + dx;
				mPosY = mPosY + dy;

				mLastTouchX = x;
				mLastTouchY = y;

				mRect1 = new Rect((int) mPosX, (int) mPosY, (int) mPosX + 128,
						(int) mPosY + 128);
			}
			break;

		case MotionEvent.ACTION_UP:
			mOnRect1 = false;
			mOnRect2 = false;
			mCollisionDetected = false;
			invalidate();
			break;
		}

		invalidate();
		return true;
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas c) {

		mShape1.setBounds(mRect1);
		mShape2.setBounds(mRect2);

		hamburgerMenuDrawable.setBounds(mRect2);
		hamburgerMenuDrawable.draw(c);

		healthyDrawable.setBounds(mRect3);
		healthyDrawable.draw(c);
		heartBitmap.setBounds(mRect1);
		heartBitmap.draw(c);

		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);

		paint.setTextSize(60);
		c.drawText("Score: " + score, (width / 2) - 150, 100, paint);

	}

}