package com.example.york.teamcraft.teammanage.board.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.york.teamcraft.teammanage.addpost.view.AddPostActivity;
import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.post.view.PostActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/7/4.
 */

public class BoardFragment extends Fragment implements BoardView {
    private static final String TAG = "BoardFragment";
    private static final Executor NETWORK_EXECUTOR = Executors.newCachedThreadPool();

    public static BoardFragment newInstance() {
        Bundle args = new Bundle();

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Database Model
    private ReadTeam readTeam;
    // UI View
    @BindView(R.id.recycler_view_board) RecyclerView recyclerView;
    private BoardItemAdapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.progress_act) ProgressBar progressBar;
    @BindView(R.id.fab_add) FloatingActionButton fabAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_board, container, false);
        ButterKnife.bind(this, view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 使用LinearLayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // set listener
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddPostActivity.class);
                startActivity(intent);
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            showAlertDialog();
        } else {
            // init Database Model
            readTeam = new ReadTeam();

            readTeam.getTeamAct(new CallBack<ArrayList<Post>>() {
                @Override
                public void update(final ArrayList<Post> list) {
                    progressBar.setVisibility(View.GONE);
                    // 設定CalendarItemAdapter
                    calendarItemAdapter = new BoardItemAdapter(getContext(), list, new View.OnClickListener() {   // 傳入listener callback
                        @Override
                        public void onClick(View v) {
                            int pos = recyclerView.indexOfChild(v);
                            Log.d(TAG, "onClick: " + pos);
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("Post", list.get(pos));    // Post Parcelable
                            intent.putExtras(bundle);
                            intent.setClass(getActivity(), PostActivity.class);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(calendarItemAdapter);
                }
            });
        }
        return view;
    }

    public void showAlertDialog() {
        AlertDialog.Builder alertDlgBuilder = new AlertDialog.Builder(this.getActivity());
        alertDlgBuilder.setMessage("要使用團隊功能請先登入帳號喔");
        alertDlgBuilder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
        alertDlgBuilder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                getActivity().finish();
            }
        });

        alertDlgBuilder.show();
    }

}
