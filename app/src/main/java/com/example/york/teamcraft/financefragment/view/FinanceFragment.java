package com.example.york.teamcraft.financefragment.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.accountingitemdialogfragment.AddAccountingItemFragment;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.financefragment.viewmodel.SetFloatingButton;
import com.example.york.teamcraft.financefragment.viewmodel.SetRecyclerViewData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanceFragment extends Fragment implements FinanceView{
    // view
    @BindView(R.id.fab_add_accounting_item)
    FloatingActionButton fabAddItem;
    @BindView(R.id.recycler_view_accounting)
    RecyclerView recyclerAcct;
    private RecyclerView.Adapter<AccountingItemViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;
    // view model
    private SetRecyclerViewData setRecyclerViewData;
    private SetFloatingButton setFloatingButton;

    public static FinanceFragment newInstance() {
        Bundle args = new Bundle();

        FinanceFragment fragment = new FinanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FinanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_finance, container, false);
        ButterKnife.bind(this, view);

        setRecyclerViewData = new SetRecyclerViewData(this);
        setRecyclerViewData.setRecyclerViewData();
        setFloatingButton = new SetFloatingButton(this);
        setFloatingButton.setFinanceView();

        return view;
    }

    @Override
    public void initRecyclerView(ArrayList<AccountingItem> list) {
        adapter = new AccountingItemAdapter(list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerAcct.setAdapter(adapter);
        recyclerAcct.setLayoutManager(layoutManager);
    }

    @Override
    public void initFab() {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAccountingItemFragment dialog = new AddAccountingItemFragment();
                dialog.show(getFragmentManager(), "AddAccountingItemFragment");
            }
        });
    }

}
