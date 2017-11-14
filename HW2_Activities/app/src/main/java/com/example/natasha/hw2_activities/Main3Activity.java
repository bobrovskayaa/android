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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mFragment1 = new Fragment3a();
        mFragment2 = new Fragment3b();
        container = (FrameLayout) findViewById(R.id.container);

        //Выход из альбомной ориентации
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        // Получим индекс из намерения активности
        Intent intent = getIntent();
        int buttonIndex = intent.getIntExtra("buttonIndex", -1);
        if (buttonIndex != -1) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment2 fragment2 = (Fragment2) fragmentManager
                    .findFragmentById(R.id.fragment2);
            //fragment2.setDescription(buttonIndex);

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
