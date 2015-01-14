package edu.auburn.eng.csse.comp3710.group3.project;

import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectContact extends Activity {

	private ListView contactListView;
	private String displayName = "", emailAddress = "", phoneNumber = "";
	ArrayList<String> contactList = new ArrayList<String>();
	ArrayAdapter<String> itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_contact);

		contactListView = (ListView) this.findViewById(R.id.listContacts_view);

		itemAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, contactList);

		contactListView.setAdapter(itemAdapter);

		contactListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView,
					View clickedView, int position, long rowID) {

				String contactCard = ((TextView) clickedView).getText()
						.toString();

				Scanner scanner = new Scanner(contactCard)
						.useDelimiter("Name:\\s*|\nPhone:\\s*|\nEmail:\\s*");

				String name = scanner.next();

				// Check to make sure the contact list is not empty.
				if (name.equals("No Contacts Found.")) {

					// To avoid crashing we jump out here.
					return;
				}

				String phone = scanner.next();
				String email = scanner.next();

				Log.i("termProject", "Name: " + name + " Phone: " + phone
						+ " Email: " + email);
				Log.i("termProject", "Position: " + position + "\nRow ID: "
						+ rowID);

				// We now have the name,email, and phone of the name selected.
				// Now, we need to pass that info back to the previous activity
				// via an intent.
				// Create an Intent to return to SendToEmail.class
				sendInfo(name, email, phone);

			}
		});
	}

	public void sendInfo(String nameIn, String emailIn, String phoneIn) {
		// Create an Intent to start the SendToEmail.class

		Intent shareIntent = new Intent(this, SendToEmail.class);

		// Pass the values of name, email, and phone.
		shareIntent.putExtra("contactName", nameIn);
		shareIntent.putExtra("contactEmail", emailIn);
		shareIntent.putExtra("contactPhone", phoneIn);

		startActivity(shareIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// List all the contacts in the view on startup
		readContacts();
	}

	private void readContacts() {

		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		boolean hasContacts = false;

		while (cursor.moveToNext()) {
			boolean hasPhoneNumber = false;
			boolean hasEmail = false;

			hasContacts = true;
			displayName = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			Log.i("termProject", "DisplayName: " + displayName);

			String id = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			Cursor emails = cr.query(Email.CONTENT_URI, null, Email.CONTACT_ID
					+ " = " + id, null, null);

			// Walks the cursor through each row
			while (emails.moveToNext()) {
				emailAddress = emails.getString(emails
						.getColumnIndex(Email.DATA));
				Log.i("termProject", "Email Address: " + emailAddress);
				hasEmail = true;
				break;
			}
			emails.close();

			if (Integer
					.parseInt(cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
				Cursor phoneCursor = cr.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ " = ?", new String[] { id }, null);

				while (phoneCursor.moveToNext()) {
					phoneNumber = phoneCursor
							.getString(phoneCursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					Log.i("termProject", "Phone Number: " + phoneNumber);
					hasPhoneNumber = true;
					break;
				}
				phoneCursor.close();
			}
			// At this point we should have all three Strings filled!
			// Need to only add the values if has* = true

			// Has all three values
			if (hasContacts && hasEmail && hasPhoneNumber) {
				contactList.add("Name: " + displayName + "\nPhone: "
						+ phoneNumber + "\nEmail: " + emailAddress);
			}

			// Has Email but NO phone number.
			else if (hasContacts && hasEmail && !hasPhoneNumber) {
				contactList.add("Name: " + displayName + "\nPhone: "
						+ "None listed." + "\nEmail: " + emailAddress);
			}

			// Has phone number but NO email.
			else if (hasContacts && !hasEmail && hasPhoneNumber) {
				contactList.add("Name: " + displayName + "\nPhone: "
						+ phoneNumber + "\nEmail: " + "None listed.");
			}

			else {
				Log.e("termProject", "Contact List Error.");
				// Should never happen.
			}

			// Notifies the View to refresh itself.
			itemAdapter.notifyDataSetChanged();

		}
		cursor.close();

		if (!hasContacts) {
			// Only happen if there are no contacts at all.
			contactList.add("No Contacts Found.");
			itemAdapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		// This makes sure the contact list does not display twice in certain
		// circumstances.
		contactList.clear();
		itemAdapter.notifyDataSetChanged();
		Log.i("termProject", "SelectContact - onPause_Here");
	}

}
