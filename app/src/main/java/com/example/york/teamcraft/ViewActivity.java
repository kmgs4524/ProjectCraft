package com.example.york.teamcraft;

import android.graphics.Bitmap;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

public class ViewActivity extends AppCompatActivity implements ScreenShotable, ViewAnimator.ViewAnimatorListener{
    private DrawerLayout drawerLayout;
    private ViewAnimator viewAnimator;
    private List<SlideMenuItem> list = new ArrayList<>(); // // 存放SlideMenuItem的List, 其中的SlideMenuItem只擁有name, imgRes
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private LinearLayout linearLayout;

    // Firebase
    private ListView activitiesView;
    private DatabaseReference reference;
    private FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Init DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);   // Layout of drawer

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this,
                list,
                this,
                drawerLayout,
                this);

        // Firebase
        activitiesView = (ListView) findViewById(R.id.listView);

        reference = FirebaseDatabase.getInstance().getReference("activities");  //取得Firebase Database的Reference

        adapter = new FirebaseListAdapter<Activities>(this, Activities.class, android.R.layout.two_line_list_item, reference) {
            @Override
            protected void populateView(View view, Activities activities, int position) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(activities.getTime());
                ((TextView)view.findViewById(android.R.id.text2)).setText(activities.getSchedule());

            }
        };
        activitiesView.setAdapter(adapter);

    }

    public void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(ViewActivity.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout
    }

    public void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem("CLOSE", R.mipmap.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem("BUILDING", R.mipmap.icn_1);  // first parameter is the id of menu item,the second is the icon resouce
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem("BOOK", R.mipmap.icn_2);
        list.add(menuItem2);

    }

    // Firebase method
    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }

    /* ViewAnimatorListener */
    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        return null;
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
    /* -------------------- */
}

    //ActivityViewHolder包含兩個TextView
//    private static class ActivitiesHolder extends RecyclerView.ViewHolder{
//        private final TextView mTextTime;
//        private final TextView mTextSchedule;
//
//        public ActivitiesHolder(View itemView){
//            super(itemView);
//
//            mTextTime = (TextView) itemView.findViewById(android.R.id.text1);
//            mTextSchedule = (TextView) itemView.findViewById(android.R.id.text2);
//        }
//
//        public void setTime(String time){
//            mTextTime.setText(time);
//        }
//
//        public void setSchedule(String schedule){
//            mTextSchedule.setText(schedule);
//        }
//
//    }

