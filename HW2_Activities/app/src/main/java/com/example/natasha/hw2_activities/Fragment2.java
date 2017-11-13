package com.example.natasha.hw2_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.natasha.hw2_activities.R;

/**
 * Created by natasha on 13.11.17.
 */

public class Fragment2 extends android.support.v4.app.Fragment {
    private EditText mPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment2, container, false);
        mPhone = (EditText) rootView.findViewById(R.id.editText3);

        return rootView;
    }

    public void setDescription(int buttonIndex) {

        switch (buttonIndex) {
            case 2:
                mPhone.setText("button1");
                break;
            case 3:
                mPhone.setText("button2");
                break;

            default:
                break;
        }
    }
}
