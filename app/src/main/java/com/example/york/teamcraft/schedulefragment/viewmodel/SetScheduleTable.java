package com.example.york.teamcraft.schedulefragment.viewmodel;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.ScheduleTableBuilder;
import com.example.york.teamcraft.schedulefragment.data.RowData;
import com.example.york.teamcraft.schedulefragment.model.GetTableData;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/28.
 */

public class SetScheduleTable {
    private static String TAG = "SetScheduleTable";

    // model
    private GetTableData getTableData;
    // view
    private ScheduleTableBuilder builder;

    public void setTable(final TableLayout layout, final Context context) {
        getTableData = new GetTableData();
        getTableData.getData(new CallBack<ArrayList<RowData>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void update(ArrayList<RowData> rowDataCollection) {
                Log.d(TAG, Integer.toString(rowDataCollection.size()));
                builder = new ScheduleTableBuilder(layout, context);
                for(RowData row: rowDataCollection) {
                    TableRow tableRow = (TableRow) LayoutInflater.from(context).inflate(R.layout.table_row, null);
                    builder.setTableRow(tableRow)
                            .setRowData(row)
                            .setRowColor()
                            .setBottomBorder()
                            .build();
                }

            }
        });
    }
}
