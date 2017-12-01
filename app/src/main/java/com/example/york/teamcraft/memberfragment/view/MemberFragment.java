package com.example.york.teamcraft.memberfragment.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberFragment extends Fragment {
    @BindView(R.id.recycler_view_member_list)
    RecyclerView recyclerViewMemberList;

    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        ButterKnife.bind(this, view);

        ArrayList<SectionOrItem> items = new ArrayList<>();
        SectionOrItem item1 = new SectionOrItem();
        SectionOrItem item2 = new SectionOrItem();
        SectionOrItem item3 = new SectionOrItem();
        item1.setIsItem(false);
        item1.setGroupName("活動組");
        item2.setIsItem(true);
        item2.setImageUrl("https://firebasestorage.googleapis.com/v0/b/teamcraft-35dd0.appspot.com/o/portraits%2Fc0b6fe18-e855-4e44-86e4-4b38214d08f5.png?alt=media&token=d1303dc4-f8fb-4dbb-ac9d-d246f708f18f");
        item2.setMemberName("李侑乘");
        item2.setEmail("kmgs4524@gmail.com");
        item3.setIsItem(true);
        item3.setImageUrl("https://firebasestorage.googleapis.com/v0/b/teamcraft-35dd0.appspot.com/o/portraits%2Fc0b6fe18-e855-4e44-86e4-4b38214d08f5.png?alt=media&token=d1303dc4-f8fb-4dbb-ac9d-d246f708f18f");
        item3.setMemberName("李侑乘");
        item3.setEmail("kmgs4524@gmail.com");
        items.add(item1);
        items.add(item2);


        recyclerViewMemberList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMemberList.setAdapter(new MemberListAdapter(getContext(), items));
        return view;
    }

}
