package com.benmu.wx.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.benmu.wx.R;
import com.benmu.wx.extend.RecognitionModule;

/**
 * Created by Carry on 2018/1/16.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView viewById = (ImageView) findViewById(R.id.iv);
        Bitmap bitmap11 = RecognitionModule.bitmap11;
        viewById.setImageBitmap(bitmap11);
    }
}
