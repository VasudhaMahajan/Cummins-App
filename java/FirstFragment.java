package com.s1.cumminsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 11/10/2017.
 */

public class FirstFragment extends Fragment
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.first_layout,container,false);
        return myView;
    }
}
