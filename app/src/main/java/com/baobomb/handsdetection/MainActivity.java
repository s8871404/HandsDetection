package com.baobomb.handsdetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity {
    Mat nativeMat = new Mat();
    Mat empty = new Mat();
    HandsDetecter handsDetecter = new HandsDetecter();
    ImageView imageView;
    // Used to load the 'native-lib' library on application startup.

    static {
        System.loadLibrary("opencv_java3");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bitmap nativeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hand);
        Utils.bitmapToMat(nativeBitmap, nativeMat);
        handsDetecter.detect(nativeMat, empty);
        Bitmap dstBitmap = Bitmap.createBitmap(empty.cols(), empty.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(empty,dstBitmap);
//        Bitmap dstBitmap = Bitmap.createBitmap(nativeMat.cols(),nativeMat.rows(), Bitmap.Config.ARGB_8888);
//        Utils.matToBitmap(nativeMat,dstBitmap);
        imageView.setImageBitmap(dstBitmap);

    }
}