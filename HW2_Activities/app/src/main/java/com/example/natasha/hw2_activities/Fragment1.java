package com.example.natasha.hw2_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.natasha.hw2_activities.R;

/**
 * Created by natasha on 13.11.17.
 */

public class Fragment1 extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment1, container, false);
        return rootView;
    }
}
