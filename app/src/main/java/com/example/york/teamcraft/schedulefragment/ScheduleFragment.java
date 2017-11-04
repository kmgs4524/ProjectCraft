package com.example.york.teamcraft.schedulefragment;

import android.os.Bundle;
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

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.data.RowData;
import com.example.york.teamcraft.schedulefragment.viewmodel.SetBuilderData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {
    private static String TAG = "ScheduleFragment";
    @BindView(R.id.tableLayout_schedule) TableLayout tableLayout;
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
        inflateActionBtn(); // toolbar加上action button

        // 將Table Row放入Table Layout
        setScheduleTable();

        return view;
    }

    public void inflateActionBtn() {
        toolbar.inflateMenu(R.menu.output_document_action_button);
        // add listener
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_as_pdf:
                        Log.d(TAG, "onContextItemSelected: " + "item clicked");
                        return true;
                    default:
                        return false;
                }
            }
        });
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
