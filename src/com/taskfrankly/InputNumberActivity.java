package com.taskfrankly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InputNumberActivity extends Activity {
	private Button submitButton;
	private EditText inputEditText;
	public static final String EXTRA_INPUT_NUM="extra_input";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_input_number);

		initUiControls();
	}

	private void initUiControls() {
		submitButton=(Button) findViewById(R.id.submit);
		inputEditText=(EditText) findViewById(R.id.input_number);

		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(inputEditText.getText().toString().equals("") || inputEditText.getText().toString().equals("0")){
					inputEditText.setError("Enter valid value");
					return;
				}else{
					Intent intent=new Intent();
					intent.putExtra(EXTRA_INPUT_NUM, inputEditText.getText().toString());
					intent.setClass(InputNumberActivity.this, GridPatternActivity.class);
					startActivity(intent);
				}

			}
		});

	}
}
