package com.example.york.teamcraft.schedulefragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.data.RowData;
import com.example.york.teamcraft.schedulefragment.viewmodel.OutputPdf;
import com.example.york.teamcraft.schedulefragment.viewmodel.SetBuilderData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {
    private static String TAG = "ScheduleFragment";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @BindView(R.id.tableLayout_schedule)
    TableLayout tableLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static ScheduleFragment newInstance() {
        Bundle args = new Bundle();

        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_schedule, container, false);
        ButterKnife.bind(this, view);
        toolbar.inflateMenu(R.menu.output_document_action_button);
        verifyStoragePermissions();
        inflateActionBtn();
        // 將Table Row放入Table Layout
        setScheduleTable();

        return view;
    }

    public void inflateActionBtn() {
        // add listener
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_as_pdf:
                        Log.d(TAG, "onMenuItemClick: " + isExternalStorageWritable());
                        if (isExternalStorageWritable()) {
                            Toast.makeText(getContext(), "writable", Toast.LENGTH_SHORT).show();
                            File file = new File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_MUSIC), "sheet.pdf");
                            try {
                                OutputStream os = new FileOutputStream(file);
                                try {
                                    String hello = "hello world";
                                    OutputStreamWriter writer = new OutputStreamWriter(os);
                                    writer.write(hello);
                                    writer.close();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } catch (FileNotFoundException e) {
                                Log.d(TAG, "onMenuItemClick: " + e.getMessage());
                            }
//                            Log.d(TAG, "onMenuItemClick: " + file.getAbsolutePath());
                            if (file.mkdirs()) {
                                Log.e(TAG, "Directory created");
                            }
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void verifyStoragePermissions() {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 無權限時需向使用者請求
            ActivityCompat.requestPermissions(
                    this.getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: granted");
            inflateActionBtn();
        }
    }

    public void setScheduleTable() {
        SetBuilderData setBuilderData = new SetBuilderData();
        setBuilderData.setTable(tableLayout, getContext());
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

}
