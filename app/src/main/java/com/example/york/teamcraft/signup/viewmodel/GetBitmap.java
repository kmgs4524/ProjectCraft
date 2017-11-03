package com.example.york.teamcraft.signup.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.InputStream;

/**
 * Created by York on 2017/11/2.
 */

public class GetBitmap {
    public Bitmap getCompressBitmap(InputStream is) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);

        return bitmap;
    }
}
