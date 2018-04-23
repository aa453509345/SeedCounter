package com.benmu.wx.extend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.benmu.framework.utils.JsPoster;
import com.benmu.wx.activity.TestActivity;
import com.benmu.wx.model.RecognizeResultModel;
import com.benmu.wx.ocr.OCRCode;
import com.benmu.wx.ocr.OCRWorker;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.File;

/**
 * Created by Carry on 2018/1/16.
 */

public class RecognitionModule extends WXModule {
    private JSCallback mCallback;
    public static Bitmap bitmap11;

    /**
     * @param imagePath 待识别图片本地路径
     * @param callback  callback
     */
    @JSMethod
    public void recognize(final String imagePath, JSCallback callback) {
        Log.e("recognize", "path>>>>>>>>" + imagePath);
        this.mCallback = callback;
        safeDecode(imagePath, 1000, 1000);
    }

    private void safeDecode(String path, int width, int height) {
        File file = new File(path);
        if (!file.exists()) {
            //路径有误
            JsPoster.postFailed(OCRCode.INVALIDATE_PATH.getmDes(), mCallback);
            return;
        }

        double scale = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        try {
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        while (true) {
            if ((width > 0 && outWidth < width)
                    || (height > 0 && outHeight < height)) {
                break;
            }
            outWidth /= 2;
            outWidth /= 2;
            scale *= 2;
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = (int) scale;

        Bitmap safeBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        bitmap11 = safeBitmap;
        Log.e("recognize", "处理完成");
//        Intent intent = new Intent(mWXSDKInstance.getContext(), TestActivity.class);
//        mWXSDKInstance.getContext().startActivity(intent);
        OCRWorker worker = new OCRWorker();
        worker.work(safeBitmap, new OCRWorker.OCRListener() {
            @Override
            public void onSuccess(int number, OCRCode code) {
                 Log.e("recognize","success>>>>"+number);
                JsPoster.postSuccess(new RecognizeResultModel(number), code.getmDes(), mCallback);
            }

            @Override
            public void onFailed(OCRCode code) {
                Log.e("recognize","onFailed>>>>");
                JsPoster.postFailed(code.getmDes(), mCallback);
            }
        });

    }
}
