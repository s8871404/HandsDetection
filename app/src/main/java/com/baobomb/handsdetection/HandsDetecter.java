package com.baobomb.handsdetection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.opencv.core.Mat;

/**
 * Created by chenweilun on 2016/12/25.
 */

public class HandsDetecter {
    static HandsDetecterCallBack handsDetecterCallBack;

    public HandsDetecter(HandsDetecterCallBack handsDetecterCallBack) {
        this.handsDetecterCallBack = handsDetecterCallBack;
    }

    static {
        System.loadLibrary("handsdetecter");
    }

    public void detectFromThermal(Mat nativeMat, Mat empty) {
        detectFromThermal(nativeMat.getNativeObjAddr(), empty.getNativeObjAddr());
    }

    public void detectFromCamera(Mat nativeMat) {
        detectFromCamera(nativeMat.getNativeObjAddr());
    }

    public void setSkinColor(Mat nativeMat) {
        setSkinColor(nativeMat.getNativeObjAddr());
    }

    //相同區域檢測-設置手部檢測區域
    public void setHandsDetectRect(Mat holeMat) {
        setHandsDetectRect(holeMat.getNativeObjAddr());
    }

    public static void handsMove(String msg) {
        Log.d("Bao", msg);
        handsDetecterCallBack.onMove(msg);
    }

    public static void handsMove(String msg, int moveX, int moveY, int positionX, int positionY) {
        Log.d("Bao", msg);
        handsDetecterCallBack.onMove(msg, moveX, moveY, positionX, positionY);
    }

    public native void detectFromThermal(long nativeMat, long empty);

    public native void detectFromCamera(long nativeMat);

    public native void setSkinColor(long nativeMat);

    public native void setHandsDetectRect(long holeFrame);
}
