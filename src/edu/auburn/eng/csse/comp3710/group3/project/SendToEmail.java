package edu.auburn.eng.csse.comp3710.group3.project;

import edu.auburn.eng.csse.comp3710.group3.project.SelectContact;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class SendToEmail extends Activity {

	private CheckBox nameCheckBox, ageCheckBox, genderCheckBox, cholCheckBox,
			hdlCheckBox, bpCheckBox, smokerCheckBox, bpMedsCheckBox,
			riskCheckBox;

	private TextView nameText, ageText, genderText, cholText, hdlText, bpText,
			smokerText, bpMedsText, riskText;

	private EditText manualNameField, manualEmailField, manualPhoneField;

	private SmsManager smsManager;

	AlertDialog textMethodSelector;

	AlertDialog.Builder builder;

	Toast smsSentToast, smsCancelToast;

	DialogInterface.OnClickListener dialogClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_to_email);

		// SMS Manager, used for sending texts via an Intent
		this.smsManager = SmsManager.getDefault();

		// Toasts
		smsCancelToast = Toast.makeText(this, "Text Cancelled",
				Toast.LENGTH_SHORT);
		smsSentToast = Toast.makeText(this, "Text Sent", Toast.LENGTH_SHORT);

		// Instantiate all the UI buttons / Fields
		nameCheckBox = (CheckBox) findViewById(R.id.nameStatic);
		ageCheckBox = (CheckBox) findViewById(R.id.ageStatic);
		genderCheckBox = (CheckBox) findViewById(R.id.genderStatic);
		cholCheckBox = (CheckBox) findViewById(R.id.cholStatic);
		hdlCheckBox = (CheckBox) findViewById(R.id.hdlStatic);
		bpCheckBox = (CheckBox) findViewById(R.id.bpStatic);
		smokerCheckBox = (CheckBox) findViewById(R.id.smokerStatic);
		bpMedsCheckBox = (CheckBox) findViewById(R.id.bpMedsStatic);
		riskCheckBox = (CheckBox) findViewById(R.id.riskScoreStatic);

		nameText = (TextView) findViewById(R.id.nameStaticTextField);
		ageText = (TextView) findViewById(R.id.ageStaticTextField);
		genderText = (TextView) findViewById(R.id.genderStaticTextField);
		cholText = (TextView) findViewById(R.id.cholStaticTextField);
		hdlText = (TextView) findViewById(R.id.hdlStaticTextField);
		bpText = (TextView) findViewById(R.id.bpStaticTextField);
		smokerText = (TextView) findViewById(R.id.smokerStaticTextField);
		bpMedsText = (TextView) findViewById(R.id.bpMedsStaticTextField);
		riskText = (TextView) findViewById(R.id.riskScoreStaticTextField);

		manualNameField = (EditText) findViewById(R.id.editName);
		manualEmailField = (EditText) findViewById(R.id.editEmailAddr);
		manualPhoneField = (EditText) findViewById(R.id.editPhoneNumber);

		Profile myProfile;

		SharedPreferences profileSettings = getSharedPreferences(
				Profile.PREFS_NAME, 0);

		// Create a Profile from the Shared Preferences
		myProfile = new Profile(profileSettings);

		// Set Field Values
		setNameFields(myProfile, profileSettings);

		// Fill in the manual name fields if they have been set in SelectContact
		// name = null if coming from EditProfile
		String name = this.getIntent().getExtras().getString("contactName");
		String email = this.getIntent().getExtras().getString("contactEmail");
		String phone = this.getIntent().getExtras().getString("contactPhone");

		/*
		 * This will be the case only when we are entering this activity from
		 * the EditProfile.class. We want to clear the check boxes so a patient
		 * will never forget to un-check a box (as some of the fields are hidden
		 * in the scroll view) before he/she sends their information. We want to
		 * make sure the user always knows what health information they are
		 * about to send, as some information may be confidential. However, if
		 * the user is returning to this activity after selecting a contact, we
		 * want all of the boxes that were previously checked to remain checked
		 * when they return.
		 */
		if (name == null && phone == null && email == null) {
			clearCheckPrefs();
		}

		if (name != null) {
			if (name.equals("No Contacts Found.") == false) {
				manualNameField.setText(name);
			}

		}

		if (email != null) {
			if (email.equals("None listed.") == false) {
				manualEmailField.setText(email);
			}

		}

		if (phone != null) {
			if (phone.equals("None listed.") == false) {
				manualPhoneField.setText(phone);
			}

		}

		// Check the checkBoxes that were previously checked.
		setCheckBoxes(profileSettings);

	}

	private void setCheckBoxes(SharedPreferences profileSettings) {

		// Name Check Box Prefs
		if (profileSettings.getBoolean("isNameChecked", false)) {
			nameCheckBox.setChecked(true);
		}

		// Age Check Box Prefs
		if (profileSettings.getBoolean("isAgeChecked", false)) {
			ageCheckBox.setChecked(true);
		}

		// Gender Check Box Prefs
		if (profileSettings.getBoolean("isGenderChecked", false)) {
			genderCheckBox.setChecked(true);
		}

		// Chol Check Box Prefs
		if (profileSettings.getBoolean("isCholChecked", false)) {
			cholCheckBox.setChecked(true);
		}

		// HDL Check Box Prefs
		if (profileSettings.getBoolean("isHDLChecked", false)) {
			hdlCheckBox.setChecked(true);
		}

		// BP Check Box Prefs
		if (profileSettings.getBoolean("isBpChecked", false)) {
			bpCheckBox.setChecked(true);
		}

		// BP Meds Check Box Prefs
		if (profileSettings.getBoolean("isBpMedsChecked", false)) {
			bpMedsCheckBox.setChecked(true);
		}

		// Smoker Check Box Prefs
		if (profileSettings.getBoolean("isSmokerChecked", false)) {
			smokerCheckBox.setChecked(true);
		}

		// Risk Score Check Box Prefs
		if (profileSettings.getBoolean("isRiskScoreChecked", false)) {
			riskCheckBox.setChecked(true);
		}

	}

	private void setNameFields(Profile myProfile,
			SharedPreferences profileSettings) {
		// Set Field Values

		// Name Field
		nameText.setText(myProfile.getName());

		// Age Field
		if (myProfile.getAge() == 0) {
			ageText.setText("");
		} else {
			ageText.setText("" + myProfile.getAge());
		}

		// Gender field
		if (myProfile.getGender() == FraminghamRiskScore.MALE) {
			genderText.setText("Male");
		}

		else {
			genderText.setText("Female");
		}

		// Chol Field
		if (myProfile.getChol() == 0) {
			cholText.setText("");
		} else {
			cholText.setText("" + myProfile.getChol());
		}

		// HDL Field
		if (myProfile.getHdl() == 0) {
			hdlText.setText("");
		} else {
			hdlText.setText("" + myProfile.getHdl());
		}

		// BP Field
		if (myProfile.getBp() == 0) {
			bpText.setText("");
		} else {
			bpText.setText("" + myProfile.getBp());
		}

		// Smoker Field
		if (myProfile.isSmoker()) {
			smokerText.setText("Yes");
		}

		else {
			smokerText.setText("No");
		}

		// BP Meds Field
		if (myProfile.isOnMeds()) {
			bpMedsText.setText("Yes");
		}

		else {
			bpMedsText.setText("No");
		}

		// Risk Field
		// Get the status of isRiskSet from the EditProfile activity
		boolean isRiskSet = this.getIntent().getExtras()
				.getBoolean("riskSetStatus");

		if (isRiskSet) {
			if (myProfile.getRisk() == 0) {
				riskText.setText(" <1%");
			} else {
				riskText.setText("" + myProfile.getRisk() + "%");
			}

		}

		else {
			riskText.setText("N/A");
		}
	}

	/** Called when the Email button is pushed. */
	public void sendEmailPushed(View activity_send_to_email) {

		Intent sendEmailIntent = new Intent(android.content.Intent.ACTION_SEND);

		// Fill intent with data
		sendEmailIntent.setType("plain/text");
		// Email Address
		String emailAddress = "" + manualEmailField.getText();
		sendEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { emailAddress });
		// Subject
		sendEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"My Health Information");

		// Any selected Heath Info
		String body = getMessageBody(false);

		sendEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

		// Send it off to the Activity chooser
		startActivity(Intent.createChooser(sendEmailIntent, "Send mail..."));
	}

	public void sendTextPushed(View activity_send_to_email) {

		if (manualPhoneField.getText().toString().equals("")) {

			Toast.makeText(this, "Enter a Phone Number!", Toast.LENGTH_SHORT)
					.show();
		}

		else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// Sets up the overall message
			builder.setTitle("Quick Send");
			builder.setMessage("Do you want to send now, or edit the message first?");

			// Let me Edit Button
			builder.setNegativeButton("Let me Edit",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// This code is executed when the user presses
							// "Let me Edit"
							dialog.dismiss();

							// Create the body of the text message.
							String body = getMessageBody(true);
							// Get the phone number
							String phoneNumber = ""
									+ manualPhoneField.getText();

							// This will open a new Text Message with the body
							// and address filled in.
							Intent sendIntent = new Intent(Intent.ACTION_VIEW);
							sendIntent.setData(Uri.parse("sms:"));
							sendIntent.putExtra("sms_body", body);
							sendIntent.putExtra("address", phoneNumber);
							sendIntent.setData(Uri
									.parse("smsto:" + phoneNumber));
							startActivity(sendIntent);

						}
					});

			// Cancel Button
			builder.setNeutralButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							// Just cancel the dialog. No Action is taken.
							dialog.dismiss();
							smsCancelToast.show();

						}
					});

			// Send Now Button
			builder.setPositiveButton("Send Now",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();

							// Create the body of the text message.
							String body = getMessageBody(true);
							// Get the phone number
							String phoneNumber = ""
									+ manualPhoneField.getText();

							// This will just send the text right away.
							smsManager.sendTextMessage(phoneNumber, null, body,
									null, null);

							smsSentToast.show();

						}

					});

			textMethodSelector = builder.create();
			textMethodSelector.show();

		}

	}

	private String getMessageBody(boolean isForSMS) {
		String messageBody = "";
		boolean noChecks = true;

		if (!manualNameField.getText().toString().equals("") && !isForSMS) {
			messageBody += "TO: " + manualNameField.getText().toString() + "\n";
		}

		if (!isForSMS) {
			messageBody += ("My Health Profile\n\n");
		}

		if (nameCheckBox.isChecked()) {
			messageBody += "Name: " + nameText.getText() + "\n";
			noChecks = false;
		}

		if (ageCheckBox.isChecked()) {
			messageBody += "Age: " + ageText.getText() + "\n";
			noChecks = false;
		}

		if (genderCheckBox.isChecked()) {
			messageBody += "Gender: " + genderText.getText() + "\n";
			noChecks = false;
		}

		if (cholCheckBox.isChecked()) {
			messageBody += "Cholesterol: " + cholText.getText() + "\n";
			noChecks = false;
		}

		if (hdlCheckBox.isChecked()) {
			messageBody += "HDL Cholesterol: " + hdlText.getText() + "\n";
			noChecks = false;
		}

		if (bpCheckBox.isChecked()) {
			// Making BP shorter so it will fit in the SMS
			if (!isForSMS) {
				messageBody += "Blood Pressure: " + bpText.getText() + "\n";
				noChecks = false;
			}

			else {
				messageBody += "BP: " + bpText.getText() + "\n";
				noChecks = false;
			}

		}

		if (smokerCheckBox.isChecked()) {
			messageBody += "Smoker: " + smokerText.getText() + "\n";
			noChecks = false;
		}

		if (bpMedsCheckBox.isChecked()) {
			// Making BP shorter so it will fit in the SMS
			if (!isForSMS) {
				messageBody += "On Blood Pressure Medication: "
						+ bpMedsText.getText() + "\n";
				noChecks = false;
			}

			else {
				messageBody += "On BP Meds: " + bpMedsText.getText() + "\n";
				noChecks = false;
			}
		}

		if (riskCheckBox.isChecked()) {
			messageBody += "Framingham Risk Score: " + riskText.getText()
					+ "\n";
			noChecks = false;
		}

		if (noChecks) {
			messageBody = "No Information was selected.";
			return messageBody;
		}

		if (!isForSMS) {
			messageBody += "\n\nThis was sent using HeartCare on Android.";
		}

		return messageBody;
	}

	public void selectContact(View activity_send_to_email) {
		Intent selectContactIntent = new Intent(this, SelectContact.class);

		this.finish();
		startActivity(selectContactIntent);

	}

	private void saveCheckPrefs() {

		SharedPreferences profileSettings = getSharedPreferences(
				Profile.PREFS_NAME, 0);
		SharedPreferences.Editor editor = profileSettings.edit();

		editor.putBoolean("isNameChecked", nameCheckBox.isChecked());
		editor.putBoolean("isAgeChecked", ageCheckBox.isChecked());
		editor.putBoolean("isGenderChecked", genderCheckBox.isChecked());
		editor.putBoolean("isCholChecked", cholCheckBox.isChecked());
		editor.putBoolean("isHDLChecked", hdlCheckBox.isChecked());
		editor.putBoolean("isBpChecked", bpCheckBox.isChecked());
		editor.putBoolean("isBpMedsChecked", bpMedsCheckBox.isChecked());
		editor.putBoolean("isSmokerChecked", smokerCheckBox.isChecked());
		editor.putBoolean("isRiskScoreChecked", riskCheckBox.isChecked());

		// Commit the changes
		editor.commit();
	}

	private void clearCheckPrefs() {

		SharedPreferences profileSettings = getSharedPreferences(
				Profile.PREFS_NAME, 0);
		SharedPreferences.Editor editor = profileSettings.edit();

		editor.putBoolean("isNameChecked", false);
		editor.putBoolean("isAgeChecked", false);
		editor.putBoolean("isGenderChecked", false);
		editor.putBoolean("isCholChecked", false);
		editor.putBoolean("isHDLChecked", false);
		editor.putBoolean("isBpChecked", false);
		editor.putBoolean("isBpMedsChecked", false);
		editor.putBoolean("isSmokerChecked", false);
		editor.putBoolean("isRiskScoreChecked", false);

		// Commit the changes
		editor.commit();

	}

	@Override
	protected void onPause() {
		super.onPause();
		saveCheckPrefs();

		Log.i("termProject", "SendToEmail onPause_Here");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("termProject", "SendToEmail onStop_Here");

		saveCheckPrefs();

	}

}
