package com.example.york.teamcraft.signup.viewmodel;

import android.graphics.Bitmap;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by York on 2017/11/1.
 */

public class UploadImage {
    private FirebaseStorage storage = FirebaseStorage.getInstance("gs://teamcraft-35dd0.appspot.com");

    public UploadTask uploadBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        String path = "portraits/" + UUID.randomUUID() + ".png";
        StorageReference storageRef =  storage.getReference(path);

        return storageRef.putBytes(data);
    }

}
