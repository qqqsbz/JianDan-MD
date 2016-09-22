package com.example.coder.jiandan_md.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.coder.jiandan_md.R;


public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
