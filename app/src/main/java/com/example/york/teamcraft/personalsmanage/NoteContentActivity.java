package com.example.york.teamcraft.personalsmanage;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.york.teamcraft.R;

public class NoteContentActivity extends AppCompatActivity {
    private static String TAG = "NoteContentActivity";
    private int itemId;
    private String noteTitle;
    private String noteContent;

    private NotesDbHelper helper;
    private Cursor cursor;

    private TextView txtTitle;
    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personals_activity_note_content);
        initToolBar();

        Bundle bundle = getIntent().getExtras();
        itemId = bundle.getInt("ItemId");
        Log.d(TAG, Integer.toString(itemId));

        queryDB();
        setTextView();
    }

    // 設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("事項");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊

        Log.d(TAG, "init ToolBar");
    }

    // 建立Action Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_content_actionbutton, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // 點選右上方的Action button，觸發刪除筆記等事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String whereClause = "_id=" + itemId;

        switch (item.getItemId()) {
            case R.id.action_delete:
                AlertDialog.Builder alertDlgBuilder = new AlertDialog.Builder(NoteContentActivity.this);
                alertDlgBuilder.setMessage("是否刪除該記事?");
//                alertDlgBuilder.setCancelable()
                alertDlgBuilder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int numRows = helper.getWritableDatabase().delete(NotesContract.Notes.TABLE_NAME, whereClause, null);
                        Log.d("numRows", Integer.toString(numRows));
                        Intent intent = new Intent();
                        intent.setClass(NoteContentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDlgBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDlgBuilder.show();

        }

        return super.onOptionsItemSelected(item);
    }

    //按左上角的返回，將回到上頁
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // 從資料庫取得資料
    public void queryDB() {
        helper = NotesDbHelper.getInstance(this); // 取得掌控資料庫的DB Helper
        String[] columns = { "title", "content" }; // 要查詢的欄位
        String selection = "_id=" + Integer.toString(itemId);   // 查詢的WHERE參數
        Log.d(TAG, columns[0]);
        Log.d(TAG, columns[1]);
        Log.d(TAG, selection);
        cursor = helper.getReadableDatabase().query(
                NotesContract.Notes.TABLE_NAME,
                columns,
                selection,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        noteTitle = cursor.getString(
                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_TITLE)
        );

        noteContent = cursor.getString(
                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_CONTENT)
        );
    }

    // 設置TextView顯示Note資料
    public void setTextView() {
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtContent  = (TextView) findViewById(R.id.txt_content);
        txtTitle.setText(noteTitle);
        txtContent.setText(noteContent);
    }


}
