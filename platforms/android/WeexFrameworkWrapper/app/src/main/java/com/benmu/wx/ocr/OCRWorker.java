package com.benmu.wx.ocr;

import android.graphics.Bitmap;
import android.util.Log;

import com.ryantang.picture.OpencvJni;

import java.sql.Date;

/**
 * Created by Carry on 2018/1/16.
 */

public class OCRWorker {
    /**
     * 识别
     */
    public void work(Bitmap bitmap, OCRListener listener) {
        if (bitmap == null) {
            listener.onFailed(OCRCode.INVALIDATE_PATH);
            return;
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.e("OCRWorker","width>>>>>>>>>"+bitmap.getWidth()+"height>>>>>>"+bitmap.getHeight());
        int[] pixels = new int[w * h];
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        int num = 0;

        try {
            result.setPixels(OpencvJni.tiqu(pixels, w, h), 0, w, 0, 0, w, h);
            num = OpencvJni.num(pixels, w, h);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OCRWorker", "识别库异常");
            listener.onFailed(OCRCode.NATIVE_ERROR);
            return;
        }

        if (listener != null) {
            listener.onSuccess(num, OCRCode.SUCCESS);
        }
        try {
            pixels=null;
            bitmap.recycle();
            result.recycle();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public interface OCRListener {
        void onSuccess(int number, OCRCode code);

        void onFailed(OCRCode code);
    }
}
