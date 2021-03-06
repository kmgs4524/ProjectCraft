package com.example.york.teamcraft.schedulefragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.data.RowData;

/**
 * Created by York on 2017/10/28.
 */

public class ScheduleTableBuilder{
    private Context context;
    // table layout
    private TableLayout tableLayout;
    // table row
    private TableRow tableRow;
    // row data
    private RowData rowData;
    // row divider
    private View rowDivider;
    // row number
    private int rowNum = 1;


    public ScheduleTableBuilder(TableLayout layout, Context context) {
        this.tableLayout = layout;
        this.context = context;
    }

    public ScheduleTableBuilder setTableRow(TableRow row) {
        this.tableRow = row;
        return this;
    }

    public ScheduleTableBuilder setRowData(RowData data) {
        this.rowData = data;
        return this;
    }

    public ScheduleTableBuilder setBottomBorder() {
        this.rowDivider = LayoutInflater.from(context).inflate(R.layout.table_row_divider, tableLayout, false);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ScheduleTableBuilder setRowColor() {
        if(rowNum % 2 == 1) {
            tableRow.setBackgroundColor(tableRow.getResources().getColor(R.color.color_row_odd, null));
        } else {
            tableRow.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.color_row_even, null));
        }
        rowNum++;

        return this;
    }

    public TableLayout build() {
        TextView txtGroupName = (TextView) tableRow.findViewById(R.id.txt_col_group_name);
        TextView txtTask = (TextView) tableRow.findViewById(R.id.txt_col_content_task);
        TextView txtRespon = (TextView) tableRow.findViewById(R.id.txt_col_responsible);
        TextView txtDate = (TextView) tableRow.findViewById(R.id.txt_col_date);

        txtGroupName.setText(rowData.getGroupName());
        txtTask.setText(rowData.getTask());
        txtRespon.setText(rowData.getResponsible());
        txtDate.setText(rowData.getDate());

        tableLayout.addView(tableRow);
        tableLayout.addView(rowDivider);

        return this.tableLayout;
    }
}
