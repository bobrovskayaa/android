package com.example.natasha.hw2_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by natasha on 13.11.17.
 */

public class Fragment3b extends Fragment {
    private EditText mMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3b, null);
        mMessage = view.findViewById(R.id.editText4);

        return view;
    }

    public String showMessage() {
        return mMessage.getText().toString();
    }
}
