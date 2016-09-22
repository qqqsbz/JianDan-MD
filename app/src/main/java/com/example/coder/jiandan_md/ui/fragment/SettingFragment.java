package com.example.coder.jiandan_md.ui.fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.util.FileUtil;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.example.coder.jiandan_md.util.PackageUtil;

import java.io.File;
import java.text.DecimalFormat;


public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private final static String SettingClearCache = "setting_cache";

    public final static String SettingRefreshLager = "setting_refresh";

    private final static String SettingAbout = "setting_about";

    private final static String SettingVersion = "setting_version";

    CheckBoxPreference refreshLagerPreference;

    Preference clearCachePreference;

    Preference aboutPreference;

    Preference versionCachePreference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        refreshLagerPreference = (CheckBoxPreference) findPreference(SettingRefreshLager);

        clearCachePreference = findPreference(SettingClearCache);

        aboutPreference = findPreference(SettingAbout);

        versionCachePreference = findPreference(SettingVersion);

        File cacheDir = ImageLoadProxy.getImageLoader().getDiskCache().getDirectory();

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        clearCachePreference.setSummary("缓存大小:" + decimalFormat.format(FileUtil.getDirSize(cacheDir)) + "M");

        versionCachePreference.setTitle("版本号:" + PackageUtil.getVersionName(getActivity()));

        refreshLagerPreference.setOnPreferenceClickListener(this);

        clearCachePreference.setOnPreferenceClickListener(this);

        aboutPreference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if (preference.getKey().equals(SettingRefreshLager)) {

            String msg = refreshLagerPreference.isChecked() ? "已开启大图模式" : "已关闭大图模式";

            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();

        } else if (preference.getKey().equals(SettingClearCache)) {

            ImageLoadProxy.getImageLoader().getDiskCache().clear();

            clearCachePreference.setSummary("缓存大小:0.00M");

            Toast.makeText(getActivity(),"清除缓存成功",Toast.LENGTH_SHORT).show();

        } else if (preference.getKey().equals(SettingAbout)) {

            Toast.makeText(getActivity(),"该Demo只是入门练习而已哦 代码写得不好 ^_^",Toast.LENGTH_LONG).show();

        }

        return false;
    }

}
