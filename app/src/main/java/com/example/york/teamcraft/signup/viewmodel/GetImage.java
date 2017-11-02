package com.example.york.teamcraft.signup.viewmodel;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by York on 2017/11/2.
 */

public class GetImage {

    public void getPortrait(FragmentActivity act, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        act.startActivityForResult(intent, requestCode);
    }
}
