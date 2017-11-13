package com.example.natasha.hw2_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mLogin;
    private TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = (TextView)findViewById(R.id.editText);
        mPassword = (TextView)findViewById(R.id.editText2);
    }

    public void onClickGo(View view) {
        if (mLogin.getText().toString().equals("") && (mPassword.getText().toString().equals(""))) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
    }
}
