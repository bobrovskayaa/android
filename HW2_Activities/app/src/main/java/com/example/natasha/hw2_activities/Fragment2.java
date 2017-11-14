package com.example.natasha.hw2_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
        mPhone = rootView.findViewById(R.id.editText3);
        return rootView;
    }

    public String showPhone() {
        return mPhone.getText().toString();
    }

    public void editPhone(String number) {
        mPhone.setText(number);
    }

}
