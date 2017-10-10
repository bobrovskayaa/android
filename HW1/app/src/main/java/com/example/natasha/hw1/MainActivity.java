package com.example.natasha.hw1;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private Button mCalculateButton;
    private TextView mResultText;
    private TextView mFirstNumber;
    private EditText mSecondNumber;
    private int mFirstCount = 0;

    private static final String KEY_FIRSTCOUNT = "FIRSTCOUNT";
    private static final String KEY_RESULT = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mCalculateButton = (Button)findViewById(R.id.button3);
        mResultText = (TextView)findViewById(R.id.textView);
        mFirstNumber = (TextView)findViewById(R.id.textView2);
        mSecondNumber = (EditText)findViewById(R.id.editText2);

        mSecondNumber.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                calculateResult();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        if (savedInstanceState != null) {
            mFirstCount = savedInstanceState.getInt(KEY_FIRSTCOUNT, 0);
            mFirstNumber.setText("" + mFirstCount);
            mResultText.setText(savedInstanceState.getString(KEY_RESULT, "Result"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_FIRSTCOUNT, mFirstCount);
        outState.putString(KEY_RESULT, mResultText.getText().toString());
    }

    public void onClickMinus(View view) {
        mFirstNumber.setText("" + --mFirstCount);
        calculateResult();
    }

    public void onClickPlus(View view) {
        mFirstNumber.setText("" + ++mFirstCount);
        calculateResult();
    }

    public void onClickCalculate(View view) {
        float sum = Float.parseFloat(mFirstNumber.getText().toString()) * Float.parseFloat(mSecondNumber.getText().toString());
        mResultText.setText("Result = " + sum);
    }

    public void calculateResult() {
        if (mSecondNumber.getText().toString().equals("") || mSecondNumber.getText().toString().equals("-") || mSecondNumber.getText().toString().equals(".")) {
            mResultText.setText("Result = 0");
        } else {
            float sum = Float.parseFloat(mFirstNumber.getText().toString()) * Float.parseFloat(mSecondNumber.getText().toString());
            mResultText.setText("Result = " + sum);
        }
    }

}
