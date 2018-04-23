package com.benmu.wx.utils;

import android.util.Log;

/**
 * Created by Carry on 2018/1/16.
 */

public class NativeLoader {
    private static final String TAG="NativeLoader";

    public static void load(String libName){
        try {
            System.loadLibrary(libName);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"加载SO库"+libName+"失败");
        }
    }
}
