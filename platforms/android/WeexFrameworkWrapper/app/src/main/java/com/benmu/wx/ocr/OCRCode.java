package com.benmu.wx.ocr;

/**
 * Created by Carry on 2018/1/16.
 * 状态码
 */

public enum OCRCode {
    SUCCESS("识别成功", 0), INVALIDATE_PATH("无效的路径", 1000), NATIVE_ERROR("识别库错误", 1001), UNKOWN_ERROR
            ("未知错误", 1002);

    private String mDes;
    private int mCode;

    OCRCode(String des, int code) {
        this.mDes = des;
        this.mCode = code;
    }


    public String getmDes() {
        return mDes;
    }

    public void setmDes(String mDes) {
        this.mDes = mDes;
    }

    public int getmCode() {
        return mCode;
    }

    public void setmCode(int mCode) {
        this.mCode = mCode;
    }
}
