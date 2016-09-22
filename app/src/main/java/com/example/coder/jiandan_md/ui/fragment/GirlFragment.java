package com.example.coder.jiandan_md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class GirlFragment extends  PictureFragment{


    public GirlFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.pictureType = PictureType.PictureTypeGirl;

    }
}
