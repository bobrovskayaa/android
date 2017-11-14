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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener{

    FrameLayout container;
    Fragment3a mFragment1;
    Fragment3b mFragment2;
    private Button mButton1;
    private Button mButton2;
    String mPhone;
    String mMessage;
    private static final String KEY_PHONE = "PHONE";
    private static final String KEY_MESS = "MESS";
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        container = (FrameLayout) findViewById(R.id.container);
        mButton1 = (Button) findViewById(R.id.button2);
        mButton2 = (Button) findViewById(R.id.button3);
        //при создании экрана нужно сохранять ФРАГМЕНТЫ?
        // для разной ориентации создаются разные объекты и они хранят разные значения
        // нужно передать данные из активности2 в активность3 и наоборот
        //забудь пока про ключи
        mFragment1 = new Fragment3a();
        mFragment2 = new Fragment3b();
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            mPhone = savedInstanceState.getString(KEY_PHONE, "");
            mMessage = savedInstanceState.getString(KEY_MESS, "");
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Fragment2 fragment2 = (Fragment2) fragmentManager
                        .findFragmentById(R.id.fragment2);
                fragment2.editPhone(mPhone);
            }
        }
        //
        if (savedInstanceState == null &&
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment2 fragment2 = (Fragment2) fragmentManager
                    .findFragmentById(R.id.fragment2);
            mPhone = fragment2.showPhone();

            // при первом запуске программы
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();

            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.container, mFragment1);
            fragmentTransaction.commit();
            mButton1.setEnabled(false);
            mButton2.setEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_PHONE, mPhone);
        outState.putString(KEY_MESS, mMessage);
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        // подключаем FragmentManager
        fragmentManager = getSupportFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        // если фрагмента не существует или он невидим
        if (fragment2 == null || !fragment2.isVisible()) {
            // запускаем активность
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putExtra("buttonIndex", buttonIndex);
            startActivity(intent);
        } else {
            // Выводим нужную информацию
            mPhone = fragment2.showPhone();

            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            switch (buttonIndex) {
                case 2:
                    fragmentTransaction.replace(R.id.container, mFragment1);
                    fragmentTransaction.commit();
                    mButton1.setEnabled(false);
                    mButton2.setEnabled(true);
                    break;
                case 3:
                    fragmentTransaction.replace(R.id.container, mFragment2);
                    fragmentTransaction.commit();
                    mButton2.setEnabled(false);
                    mButton1.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickCall(View view) {
        fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        mPhone = fragment2.showPhone();
        if(!TextUtils.isEmpty(mPhone)) {
            String dial = "tel:" + mPhone;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        } else {
            Toast.makeText(Main2Activity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSend(View view) {
        fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);

        mPhone = fragment2.showPhone();
        mMessage = mFragment2.showMessage();
        if(!TextUtils.isEmpty(mMessage) && !TextUtils.isEmpty(mPhone)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mPhone));
            smsIntent.putExtra("sms_body", mMessage);
            startActivity(smsIntent);
        }
        else {
            Toast.makeText(Main2Activity.this, "Enter a phone number and a message", Toast.LENGTH_SHORT).show();
        }
    }
}
