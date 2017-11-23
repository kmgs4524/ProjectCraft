package com.example.york.teamcraft.financefragment.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.accountingitemdialogfragment.AccountingItemFragment;
import com.example.york.teamcraft.financefragment.ProgressWheel;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.financefragment.viewmodel.SetBudget;
import com.example.york.teamcraft.financefragment.viewmodel.SetFloatingButton;
import com.example.york.teamcraft.financefragment.viewmodel.SetAccountingItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinanceFragment extends Fragment implements FinanceView {
    private static String TAG = "FinanceFragment";
    // view
    @BindView(R.id.progress_bar_remaining_amount)
    ProgressWheel progressWheel;
    @BindView(R.id.txt_budget)
    TextView txtBudget;
    @BindView(R.id.txt_total_cost)
    TextView txtTotalCost;
    @BindView(R.id.fab_add_accounting_item)
    FloatingActionButton fabAddItem;
    @BindView(R.id.recycler_view_accounting)
    RecyclerView recyclerAcct;
    private RecyclerView.Adapter<AccountingItemViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;
    // view model
    private SetBudget setBudget;
    private SetAccountingItem setAccountingItem;
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

        setBudget = new SetBudget(this);
        setBudget.setData();
        setAccountingItem = new SetAccountingItem(this);
        setAccountingItem.setRecyclerViewData();
        setFloatingButton = new SetFloatingButton(this);
        setFloatingButton.setFinanceView();

        return view;
    }

    @Override
    public void initProgressBar(int budget, int totalCost) {
        progressWheel.setMax(budget);
        progressWheel.setProgress(-totalCost);
    }

    @Override
    public void initTxtBuget(int budget) {
        txtBudget.setText(Integer.toString(budget));
    }

    @Override
    public void initRecyclerView(final ArrayList<AccountingItem> list) {
        adapter = new AccountingItemAdapter(list, this);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerAcct.setAdapter(adapter);
        recyclerAcct.setLayoutManager(layoutManager);
    }

    @Override
    public void initTxtTotalCost(int cost) {
        txtTotalCost.setText(Integer.toString(cost));
    }

    @Override
    public void initFab() {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountingItemFragment dialog = new AccountingItemFragment();
                dialog.show(getFragmentManager(), "AccountingItemFragment");
            }
        });
    }

}
