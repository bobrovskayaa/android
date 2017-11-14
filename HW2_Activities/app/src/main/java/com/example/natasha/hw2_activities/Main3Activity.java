package com.example.natasha.hw2_activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    FrameLayout container;
    Fragment3a mFragment1;
    Fragment3b mFragment2;
    String mPhone2;
    String mMessage2;
    private static final String KEY_PHONE2 = "PHONE2";
    private static final String KEY_MESS2 = "MESS2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mFragment1 = new Fragment3a();
        mFragment2 = new Fragment3b();
        container = (FrameLayout) findViewById(R.id.container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        if (savedInstanceState != null) {
            mPhone2 = savedInstanceState.getString(KEY_PHONE2, "");
            mMessage2 = savedInstanceState.getString(KEY_MESS2, "");
        }

        fragment2.editPhone(mPhone2);

        //Выход из альбомной ориентации
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        // Получим индекс из намерения активности
        Intent intent = getIntent();
        int buttonIndex = intent.getIntExtra("buttonIndex", -1);
        if (buttonIndex != -1) {

            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            switch (buttonIndex) {
                case 2:
                    fragmentTransaction.replace(R.id.container, mFragment1);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    fragmentTransaction.replace(R.id.container, mFragment2);
                    fragmentTransaction.commit();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_PHONE2, mPhone2);
        outState.putString(KEY_MESS2, mMessage2);
    }

    public void onClickCall(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        String mPhone = fragment2.showPhone();
        if(!TextUtils.isEmpty(mPhone)) {
            String dial = "tel:" + mPhone;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        } else {
            Toast.makeText(Main3Activity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSend(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        String message = mFragment2.showMessage();
        String mPhone = fragment2.showPhone();

        if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(mPhone)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mPhone));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        }
        else {
            Toast.makeText(Main3Activity.this, "Enter a phone number and a message", Toast.LENGTH_SHORT).show();
        }
    }
}
