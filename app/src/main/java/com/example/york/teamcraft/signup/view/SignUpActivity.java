package com.example.york.teamcraft.signup.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.signup.viewmodel.CreateUser;
import com.example.york.teamcraft.signup.viewmodel.GetImage;
import com.example.york.teamcraft.teammanage.SelectTeamActivity;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView{
    private static String TAG = "SignUpActivity";
    private static int SELECT_PHOTO = 1;

    // view
    @BindView(R.id.sign_up_toolbar) Toolbar toolbar;
    @BindView(R.id.confirm_btn)  Button confirmBtn;
    @BindView(R.id.edt_user_name)  EditText edtName;
    @BindView(R.id.edt_email)  EditText edtEmail;
    @BindView(R.id.edt_pwd) EditText edtPwd;
    @BindView(R.id.img_portrait) ImageView imgPortrait;
    @BindView(R.id.btn_portrait) Button btnChoose;
    // view model
    private CreateUser createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initToolBar();  //ToolBar
        showPortrait();
        setBtnChoose();
    }

    //設置ToolBar為此activity的app bar
    private void initToolBar(){
        toolbar.setTitle("註冊帳號");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.confirm_btn)
    public void submit(View v) {
        String email = edtEmail.getText().toString();
        String password = edtPwd.getText().toString();
        String name = edtName.getText().toString();
        createUser = new CreateUser(SignUpActivity.this);
        createUser.createUser(email, password, name);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //確認使用者是否登入中
//        FirebaseUser currentUser = auth.getCurrentUser();
    }

    public void startSelectActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SelectTeamActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPortrait() {
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/teamcraft-35dd0.appspot.com/o/designer-working-on-his-computer.jpg?alt=media&token=bb68b9d5-6593-46f3-b28a-8edcada0af5d")
                .resize(50, 50)
                .into(imgPortrait);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && data != null && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();  // This is ContentProvider URI

            try{
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                imgPortrait.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void setBtnChoose() {
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetImage getImage = new GetImage();
                getImage.getPortrait(SignUpActivity.this, SELECT_PHOTO);
            }
        });
    }

}
