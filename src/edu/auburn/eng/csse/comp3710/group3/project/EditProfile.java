package edu.auburn.eng.csse.comp3710.group3.project;

import edu.auburn.eng.csse.comp3710.group3.project.Profile;

import edu.auburn.eng.csse.comp3710.group3.project.SendToEmail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class EditProfile extends Activity {

	public Profile myProfile;

	private EditText nameField, ageField, cholField, hdlField, bpField;
	private RadioButton maleGenderField, femaleGenderField;
	private CheckBox onMedsField, smokerField;
	private TextView riskScore;
	private Toast updateToast;

	private boolean isAgeSet, isCholSet, isHDLSet, isBPSet, isRiskSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);

		// Create Update Toast
		updateToast = Toast.makeText(this,
				"Info saved... need more info to calculate risk.",
				Toast.LENGTH_LONG);

		// Instantiate all the UI buttons / Fields
		nameField = (EditText) findViewById(R.id.editName);
		ageField = (EditText) findViewById(R.id.editAge);
		cholField = (EditText) findViewById(R.id.editChol);
		hdlField = (EditText) findViewById(R.id.editHDL);
		bpField = (EditText) findViewById(R.id.editBP);

		maleGenderField = (RadioButton) findViewById(R.id.isMale);
		femaleGenderField = (RadioButton) findViewById(R.id.isFemale);

		onMedsField = (CheckBox) findViewById(R.id.isOnMedsCheckBox);
		smokerField = (CheckBox) findViewById(R.id.isSmokerCheckBox);

		riskScore = (TextView) findViewById(R.id.riskScoreDisplay);

		SharedPreferences profileSettings = getSharedPreferences(
				Profile.PREFS_NAME, 0);

		// Create a Profile from the Shared Preferences
		myProfile = new Profile(profileSettings);

		// Restore Preferences

		// Name Prefs
		if (myProfile.getName().equals("")) {
			nameField.setHint("Enter Your Name");
		}

		else {
			nameField.setText(myProfile.getName());
		}

		// Age Prefs
		if (myProfile.getAge() == 0) {
			ageField.setHint("Enter Age (20 -79)");
			isAgeSet = false;
		}

		else {
			ageField.setText("" + myProfile.getAge());
			isAgeSet = true;
		}

		// Chol Prefs
		if (myProfile.getChol() == 0) {
			cholField.setHint("Enter Cholesterol");
			isCholSet = false;
		}

		else {
			cholField.setText("" + myProfile.getChol());
			isCholSet = true;
		}

		// hdl Prefs
		if (myProfile.getHdl() == 0) {
			hdlField.setHint("Enter HDL Cholesterol");
			isHDLSet = false;
		}

		else {
			hdlField.setText("" + myProfile.getHdl());
			isHDLSet = true;
		}

		// bp Prefs
		if (myProfile.getBp() == 0) {
			bpField.setHint("Enter Blood Pressure");
			isBPSet = false;
		}

		else {
			bpField.setText("" + myProfile.getBp());
			isBPSet = true;
		}

		// onMeds Prefs
		onMedsField.setChecked(myProfile.isOnMeds());

		// smoker Prefs
		smokerField.setChecked(myProfile.isSmoker());

		// gender Prefs
		if (myProfile.getGender() == FraminghamRiskScore.MALE) {
			maleGenderField.toggle();
		}

		else {
			femaleGenderField.toggle();
		}

		// risk score prefs
		if (isAgeSet && isCholSet && isHDLSet && isBPSet) {
			calculateFramScore();

			if (myProfile.getRisk() == 0) {
				riskScore.setText(" <1%");
			} else {
				riskScore.setText(" " + myProfile.getRisk() + "%");
			}

			isRiskSet = true;
		}

		else {
			riskScore.setText("N/A");
			isRiskSet = false;
		}

	}

	public void save(View activity_edit_profile) {
		save();
	}

	public void save() {
		SharedPreferences profileSettings = getSharedPreferences(
				Profile.PREFS_NAME, 0);
		SharedPreferences.Editor editor = profileSettings.edit();

		// Save Name Prefs
		String nameContents = nameField.getText().toString();

		if (nameContents.matches("")) {
			// If the Name field is blank, they did not enter a name.
			myProfile.setName("");
			editor.putString("name", "");
			nameField.setHint("Enter Your Name");
		}

		else {
			myProfile.setName(nameField.getText().toString());
			editor.putString("name", myProfile.getName());
		}

		// Save Age Prefs
		String ageContents = ageField.getText().toString();

		if (ageContents.matches("")) {
			// If the field is blank, we know it's 0.
			myProfile.setAge(0);
			editor.putInt("age", myProfile.getAge());
			ageField.setHint("Enter Age (20 -79)");
			isAgeSet = false;
		}

		else {

			if ((Integer.parseInt(ageField.getText().toString()) > 19)
					&& (Integer.parseInt(ageField.getText().toString()) < 80)) {
				myProfile.setAge(Integer
						.parseInt(ageField.getText().toString()));
				editor.putInt("age", myProfile.getAge());
				isAgeSet = true;
			} else {
				myProfile.setAge(0);
				editor.putInt("age", myProfile.getAge());
				ageField.setText("");
				ageField.setHint("Enter Age (20 -79)");
				Toast.makeText(this, "Enter a valid age.", Toast.LENGTH_SHORT)
						.show();
				isAgeSet = false;
			}

		}

		// Save Chol Prefs
		String cholContents = cholField.getText().toString();

		if (cholContents.matches("")) {
			// If the field is blank, we know it's 0.
			myProfile.setChol(0);
			editor.putInt("chol", myProfile.getChol());
			cholField.setHint("Enter Cholesterol");
			isCholSet = false;
		}

		else {
			myProfile.setChol(Integer.parseInt(cholField.getText().toString()));
			editor.putInt("chol", myProfile.getChol());
			isCholSet = true;
		}

		// Save HDL Prefs
		String hdlContents = hdlField.getText().toString();

		if (hdlContents.matches("")) {
			// If the field is blank, we know it's 0.
			myProfile.setHdl(0);
			editor.putInt("hdl", myProfile.getHdl());
			hdlField.setHint("Enter HDL Cholesterol");
			isHDLSet = false;
		}

		else {
			myProfile.setHdl(Integer.parseInt(hdlField.getText().toString()));
			editor.putInt("hdl", myProfile.getHdl());
			isHDLSet = true;
		}

		// Save BP Prefs
		String bpContents = bpField.getText().toString();

		if (bpContents.matches("")) {
			// If the field is blank, we know it's 0.
			myProfile.setBp(0);
			editor.putInt("bp", myProfile.getBp());
			bpField.setHint("Enter Blood Pressure");
			isBPSet = false;
		}

		else {
			myProfile.setBp(Integer.parseInt(bpField.getText().toString()));
			editor.putInt("bp", myProfile.getBp());
			isBPSet = true;
		}

		// Save isOnMeds Prefs
		myProfile.setOnMeds(onMedsField.isChecked());
		editor.putBoolean("isOnMeds", myProfile.isOnMeds());

		// Save Smoker Prefs
		myProfile.setSmoker(smokerField.isChecked());
		editor.putBoolean("isSmoker", myProfile.isSmoker());

		// Save Gender Prefs
		if (maleGenderField.isChecked()) {
			myProfile.setGender(FraminghamRiskScore.MALE);
			editor.putInt("gender", FraminghamRiskScore.MALE);
		}

		else {
			myProfile.setGender(FraminghamRiskScore.FEMALE);
			editor.putInt("gender", FraminghamRiskScore.FEMALE);
		}

		// Save and calculate riskScore Prefs
		if (isAgeSet && isCholSet && isHDLSet && isBPSet) {
			calculateFramScore();
			editor.putInt("riskScore", myProfile.getRisk());

			if (myProfile.getRisk() == 0) {
				riskScore.setText(" <1%");
			} else {
				riskScore.setText(" " + myProfile.getRisk() + "%");
			}

			isRiskSet = true;

		}

		else {
			riskScore.setText("N/A");
			isRiskSet = false;

			updateToast.show();
		}

		// Commit the edits!
		editor.commit();

	}

	public void calculateFramScore() {

		myProfile.setRisk(FraminghamRiskScore.calculateRiskAsInt(
				myProfile.getAge(), myProfile.getGender(), myProfile.getChol(),
				myProfile.getHdl(), myProfile.getBp(), myProfile.isOnMeds(),
				myProfile.isSmoker()));

		riskScore.setText("" + myProfile.getRisk() + "%");

	}

	/** Called when the user clicks the "Send to HealthCare Provider" button */
	public void sendToEmailActivity(View activity_edit_profile) {

		save();
		updateToast.cancel();

		Intent emailIntent = new Intent(this, SendToEmail.class);

		// Pass the value of isRiskSet
		emailIntent.putExtra("riskSetStatus", isRiskSet);
		startActivity(emailIntent);
	}

	public void showFramInfo(View view) {
		Intent intent = new Intent(this, FramInfoDialog.class);
		startActivity(intent);

	}

	@Override
	protected void onPause() {
		super.onPause();
		save();
		updateToast.cancel();
		Log.i("termProject", "EditProfile onPause_Here");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("termProject", "EditProfile onStop_Here");

		save();
		updateToast.cancel();

	}

}
