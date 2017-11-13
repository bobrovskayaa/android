package com.example.natasha.hw2_activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class Main2Activity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener{

    FrameLayout container;
    Fragment3a mFragment1;
    Fragment3b mFragment2;
    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        container = (FrameLayout) findViewById(R.id.container);
        mButton1 = (Button) findViewById(R.id.button2);
        mButton2 = (Button) findViewById(R.id.button3);
        mFragment1 = new Fragment3a();
        mFragment2 = new Fragment3b();

        /*if (savedInstanceState == null) {
            // при первом запуске программы
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.container, mFragment1);
            fragmentTransaction.commit();
            mButton1.setEnabled(false);
            mButton2.setEnabled(true);
        }*/
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        // подключаем FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);
        Fragment3a fragment3 = (Fragment3a) fragmentManager
                .findFragmentById(R.id.fragment3);

        // если фрагмента не существует или он невидим
        if (fragment2 == null || !fragment2.isVisible()) {
            // запускаем активность
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putExtra("buttonIndex", buttonIndex);
            startActivity(intent);
        } else {
            // Выводим нужную информацию
            fragment2.setDescription(buttonIndex);

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
}
