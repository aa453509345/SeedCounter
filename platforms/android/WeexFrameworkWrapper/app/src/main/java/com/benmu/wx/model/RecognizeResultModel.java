package com.benmu.wx.model;

import java.io.Serializable;

/**
 * Created by Carry on 2018/1/16.
 */

public class RecognizeResultModel implements Serializable {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RecognizeResultModel(int number) {
        this.number = number;
    }
}

