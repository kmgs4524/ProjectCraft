package com.example.york.teamcraft.signup.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.signup.viewmodel.GetBitmap;
import com.example.york.teamcraft.signup.viewmodel.CreateUser;
import com.example.york.teamcraft.signup.viewmodel.RetrieveImage;
import com.example.york.teamcraft.teammanage.SelectTeamActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private static String TAG = "SignUpActivity";
    private static int SELECT_PHOTO = 1;
    // view
    @BindView(R.id.sign_up_toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar_sign_up)
    ProgressBar progBarSignUp;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;
    @BindView(R.id.edt_user_name)
    EditText edtName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_pwd)
    EditText edtPwd;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    // view model
    private CreateUser createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initToolBar();  //ToolBar
    }

    //設置ToolBar為此activity的app bar
    private void initToolBar() {
        toolbar.setTitle("註冊帳號");
        setSupportActionBar(toolbar);
    }

    // 點選CircleImageView，從手機畫廊中選擇圖片
    @OnClick(R.id.img_portrait)
    public void choosePortrait() {
        RetrieveImage retrieveImage = new RetrieveImage();
        retrieveImage.startPickImage(SignUpActivity.this, SELECT_PHOTO);
    }

    // 點選註冊Button進行註冊
    @OnClick(R.id.confirm_btn)
    public void submit(View v) {
        String email = edtEmail.getText().toString();
        String password = edtPwd.getText().toString();
        String name = edtName.getText().toString();
        if (email.equals("") || password.equals("") || name.equals("")) {
            Toast.makeText(this, "以上任一欄位不得空白", Toast.LENGTH_SHORT).show();
        } else {
            progBarSignUp.setVisibility(View.VISIBLE);
            createUser = new CreateUser(SignUpActivity.this);
            createUser.createUser(email, password, name, ((BitmapDrawable) imgPortrait.getDrawable()).getBitmap());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //確認使用者是否登入中
    }

    public void startSelectActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SelectTeamActivity.class);
        startActivity(intent);
    }

    // 被RetrieveImage.startPickImage()中的startActivityForResult()所呼叫並傳入參數
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && data != null && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();  // This is ContentProvider URI
            try {
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                GetBitmap getBitMap = new GetBitmap();
                Bitmap bitmap = getBitMap.getCompressBitmap(imageStream);   // 取得壓縮過的bitmap
                imgPortrait.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
