package com.codepath.upkar.tipcalculator;

import java.math.BigDecimal;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

	private static final String DOLLAR_STRING = "$$";
	private static final String tag = "com.codepath.upkar.tipcalculator.TipCalculatorActivity";
	private TextView tvTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		tvTip = (TextView) findViewById(R.id.tvTip);
		tvTip.setText(DOLLAR_STRING);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}

	public void calculateTip(View v) {
		/*
		 * can any of these ids be null? can v be null?
		 */
		int btn10Id = findViewById(R.id.btn10).getId();
		int btn15Id = findViewById(R.id.btn15).getId();
		int btn20Id = findViewById(R.id.btn20).getId();

		Integer tipPercentage = null;

		if (v.getId() == btn10Id)
			tipPercentage = 10;
		else if (v.getId() == btn15Id)
			tipPercentage = 15;
		else if (v.getId() == btn20Id)
			tipPercentage = 20;

		/*
		 * none of the buttons were clicked. Something else brought the code
		 * here. Is this possible?
		 */
		if (tipPercentage == null)
			return;

		/*
		 * if no amount is present and the buttons are clicked, just show $$
		 */
		tvTip = (TextView) findViewById(R.id.tvTip);
		EditText etAmount = (EditText) findViewById(R.id.etAmount);
		String amountTxt = etAmount.getText().toString();
		if (amountTxt == null || amountTxt.length() < 1) {
			tvTip.setText(DOLLAR_STRING);
			return;
		}

		try {
			
			float amountFloat = Float.parseFloat(amountTxt);
			BigDecimal amount = new BigDecimal(amountFloat).setScale(2, BigDecimal.ROUND_UP);
			BigDecimal tip = new BigDecimal((tipPercentage * amount.floatValue()) / 100).setScale(2, BigDecimal.ROUND_UP);
			tvTip.setText("$  " + String.valueOf(tip));
		} catch (NumberFormatException e) {
			Log.d(tag, e.getMessage());
		}
	}
}
