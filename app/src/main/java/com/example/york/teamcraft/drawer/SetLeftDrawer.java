package com.example.york.teamcraft.drawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksActivity;
import com.example.york.teamcraft.teammanage.MainActivity;

/**
 * Created by York on 2017/10/13.
 */

public class SetLeftDrawer {
//    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    public void setLeftDrawer(final FragmentActivity activity, final DrawerLayout drawerLayout) {
//        navigationView = (NavigationView) activity.findViewById(R.id.navigation_view_left);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                // 先檢查點擊的item是否為checked
//                if (item.isChecked()) {
//                    item.setChecked(false);
//                } else {
//                    item.setChecked(true);
//                }
//
//                drawerLayout.closeDrawers();    // 關閉drawer
//                Intent intent = new Intent();
//
//                switch (item.getItemId()) {
//                    case R.id.drawer_personals:
//                        if (!(activity instanceof PersonalTasksActivity)) {
//                            intent.setClass(activity, PersonalTasksActivity.class);
//                            activity.startActivity(intent);
//                            return true;
//                        } else {
//                            return true;
//                        }
//
//                    case R.id.drawer_team:
//                        if (!(activity instanceof MainActivity)) {
//                            Log.d("goto", activity.toString());
//                            intent.setClass(activity, MainActivity.class);
//                            activity.startActivity(intent);
//                            return true;
//                        } else {
//                            return true;
//                        }
//                    case R.id.drawer_account:
//                        if (!(activity instanceof SignInActivity)) {
//                            intent.setClass(activity, SignInActivity.class);
//                            activity.startActivity(intent);
//                            return true;
//                        } else {
//                            return true;
//                        }
//
//                    default:
//                        return true;
//                }
//            }
//        });
    }
}
