package com.example.york.teamcraft.schedulefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.model.RowData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {
    @BindView(R.id.tableLayout_schedule)
    TableLayout tableLayout;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_schedule, container, false);
        ButterKnife.bind(this, view);

        // 將Table Row放入Table Layout
        TableRow inflated = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.table_row, null);
        TableLayout newLayout = new ScheduleTableBuilder(tableLayout, getContext())
                .setTableRow(inflated)
                .setRowData(new RowData("器材組", "搬東西", "小球球", "11/15"))
                .setBottomBorder()
                .build();

        TableLayout new2 = new ScheduleTableBuilder(newLayout, getContext())
                .setTableRow((TableRow)LayoutInflater.from(getContext()).inflate(R.layout.table_row, null))
                .setRowData(new RowData("美工組", "買水彩", "小郁郁", "11/20"))
                .setBottomBorder()
                .build();

//        TableLayout table1 = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.table_row, );
//        builder = new ScheduleTableBuilder(table1);
//        builder.setData(new RowData("器材組", "搬東西", "小球球", "11/15"));

        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

}
