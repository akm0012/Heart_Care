package edu.auburn.eng.csse.comp3710.group3.project;

import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AbcsAspirinActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	public void launchWebPage1(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://www.mayoclinic.com/health-information/"));
		startActivity(i);
	}

	public void launchWebPage2(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://health.nih.gov"));
		startActivity(i);
	}

	public void launchWebPage3(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri
				.parse("https://www.vanderbilthealth.com/main/healthtopics"));
		startActivity(i);
	}

	public void launchWebPage4(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri
				.parse("http://www.uabmedicine.org/conditions-and-services"));
		startActivity(i);
	}



	public void launchWebPageAspirin(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://www.webmd.com/drugs/mono-3003-ASPIRIN+CHEWABLE+-+ORAL.aspx?drugid=1082&drugname=aspirin+Oral&source=1"));
		startActivity(i);
	}

	public void launchWebPageBloodPressure(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://www.webmd.com/hypertension-high-blood-pressure/guide/blood-pressure-causes"));
		startActivity(i);
	}

	public void launchWebPageCholesterol(View view) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://www.webmd.com/cholesterol-management/default.htm"));
		startActivity(i);
	}

	public void helpStopButtonClicked(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent i = new Intent(Intent.ACTION_VIEW);
						i.setData(Uri.parse("http://www.webmd.com/smoking-cessation/default.htm"));
						startActivity(i);
					}
				})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						}).setMessage(R.string.alert_stopsmoking_message);
		// Set other dialog properties

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abcs_view);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();

			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = null;
			switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
			case 0:
				rootView = inflater.inflate(
						R.layout.fragment_abcs_aspirin_dummy, container, false);
				// TextView t2 = (TextView) rootView.findViewById(R.id.weblink);
				// t2.setMovementMethod(LinkMovementMethod.getInstance());
				break;
			case 1:
				rootView = inflater
						.inflate(R.layout.fragment_abcs_aspirin_dummy2,
								container, false);

				break;
			case 2:
				rootView = inflater
						.inflate(R.layout.fragment_abcs_aspirin_dummy3,
								container, false);
				break;
			case 3:
				rootView = inflater
						.inflate(R.layout.fragment_abcs_aspirin_dummy4,
								container, false);
				break;
			case 4:
				rootView = inflater
						.inflate(R.layout.fragment_abcs_aspirin_dummy5,
								container, false);
				break;
			}
			/*
			 * TextView dummyTextView = (TextView) rootView
			 * .findViewById(R.id.section_label);
			 * dummyTextView.setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER)));
			 */
			return rootView;
		}
	}

}
