package com.example.york.teamcraft.schedulefragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.viewmodel.OutputPdf;
import com.example.york.teamcraft.schedulefragment.viewmodel.SetScheduleTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {
    private static String TAG = "ScheduleFragment";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    // view model
    private SetScheduleTable setScheduleTable;

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
        inflateActionBtn(tableLayout);
        // 將Table Row放入Table Layout
        setScheduleTable = new SetScheduleTable();
        setScheduleTable.setTable(tableLayout, getContext());

        return view;
    }

    public void inflateActionBtn(final View v) {
        // add listener
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_as_pdf:
                        new OutputPdfTask().execute(v);
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
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: granted");
//            inflateActionBtn();
        }
    }

    private class OutputPdfTask extends AsyncTask {
        OutputDialogFragment dialogFragment;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogFragment = new OutputDialogFragment();
            dialogFragment.show(getFragmentManager(), "OutputDialogFragment");
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.d(TAG, "onMenuItemClick: " + isExternalStorageWritable());

            if (isExternalStorageWritable()) {
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "schedule.pdf");
                try {
                    OutputStream os = new FileOutputStream(file);
                    OutputPdf outputPdf = new OutputPdf();
                    outputPdf.outputDoc((View)params[0], os); // outputPdf.outputDoc(v, os);
//                                    OutputStreamWriter writer = new OutputStreamWriter(os);
//                                    writer.write(hello);
//                                    writer.close();

                } catch (FileNotFoundException e) {
                    Log.d(TAG, "onMenuItemClick: " + e.getMessage());
                }
                if (file.mkdirs()) {
                    Log.e(TAG, "Directory created");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            dialogFragment.dismiss();
            Toast.makeText(getContext(), "已匯出" + "schedule.pdf至Download資料夾", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

}
