package com.stefanaleksic.MolarMassCalculator;

import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
	private AdView adView;
	private static final String MY_AD_UNIT_ID = "ca-app-pub-2977954306342400/4126550315";
	
	
	
	@Override
	  public void onPause() {
	    adView.pause();
	    super.onPause();
	  }

	  @Override
	  public void onResume() {
	    super.onResume();
	    adView.resume();
	  }

	  @Override
	  public void onDestroy() {
	    adView.destroy();
	    super.onDestroy();
	  }
	
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		
		
		
		adView = new AdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		adView.setAdSize(AdSize.BANNER);
		
		LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
		
		layout.addView(adView);
		
		AdRequest adRequest = new AdRequest.Builder().build();
		
		adView.loadAd(adRequest);
		
	}

	private void openDevs() {
		new AlertDialog.Builder(this)
				.setTitle("Devs")
				.setMessage(
						"Programmed by: \n Stefan Aleksic\n   ::stefan@stefanaleksic.com \n Shil Mehta\n   ::shil.school@gmail.com \n\nDesigned by: \n Shubhit Dharnidharka \n Sunny Patel")
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
							}
						})

				.show();
	}

	private void openSettings() {
		new AlertDialog.Builder(this)
				.setTitle("Help")
				.setMessage(
						"Make sure to capitalize the first letter of the Element. \n\nExample: \"He\" for Helium not \"he\". \n\nWhen opening parenthesis, make sure to always close them.")
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
							}
						})

				.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_devs:
			openDevs();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void lolumadeerror() {

	}
	
	
	
	static boolean isOn = false;
	public void sendMessage(View view) {

		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();

		//
		//
		//

		int count = 0;
		ArrayList<Element> elementsList = new ArrayList<Element>();
		ArrayList<String> elementName = new ArrayList<String>();
		ArrayList<Double> atomicMass = new ArrayList<Double>();
		String lineContent = "";

		Scanner in = new Scanner(getResources().openRawResource(R.raw.ele));

		while (in.hasNextLine()) {
			count++;
			lineContent = in.nextLine();

			switch (count) {
			case 3:
				elementName.add(lineContent);
				break;
			case 4:
				atomicMass.add(Double.parseDouble(lineContent));
				break;
			}
			if (count == 4)
				count = 0;

		}

		int amountOfElements = 118;

		for (int i = 0; i < atomicMass.size(); i++) {
			elementsList
					.add(new Element(elementName.get(i), atomicMass.get(i)));
		}

		TT text = new TT();
		if (message.equalsIgnoreCase("Nicolas Cage")) {
			int resId = getResources().getIdentifier("nick", "raw",
					getPackageName());
			new AlertDialog.Builder(this)
					.setTitle("NICOLAS CAGE")
					.setMessage("")
					.setIcon(getResources().getDrawable(resId))
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})

					.show();
		} else {
			try {
				double total = text.calculateMolarMass(elementsList, message);

				if (total == 0.0) {
					throw new Exception();
				}
				new AlertDialog.Builder(this)
						.setTitle(message)

						.setMessage(total + " g/mol")
						.setPositiveButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								})

						.show();
			} catch (Exception e) {
				Context context = getApplicationContext();
				CharSequence toastText = "";
				if (Build.VERSION.SDK_INT < 11) {
					toastText = "You entered something incorrectly. Check out \"Help\" by clicking the menu button to see what you did wrong.";
				} else {
					toastText = "You entered something incorrectly. Check out \"Help\" to see what you did wrong.";
				}
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, toastText, duration);
				toast.show();
				

			}
		}
	}
}
