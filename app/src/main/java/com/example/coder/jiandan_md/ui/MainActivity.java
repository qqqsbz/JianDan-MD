package com.example.coder.jiandan_md.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.ui.fragment.BoringFragment;
import com.example.coder.jiandan_md.ui.fragment.GirlFragment;
import com.example.coder.jiandan_md.ui.fragment.RefreshFragment;
import com.example.coder.jiandan_md.util.ImageLoadProxy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;

    @BindView(R.id.navigation_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.navigation_view)
    public NavigationView navigationView;

    private long time = 0;

    private ActionBarDrawerToggle drawerToggle;

    private int lastSelectedMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ImageLoadProxy.initImageLoader(this);

        initView();

    }

    private void initView() {

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() != lastSelectedMenu) {
                    switch (item.getItemId()) {

                        case R.id.navigation_menu_fresh:

                            .this.replaceToRefreshFragment();

                            break;

                        case R.id.navigation_menu_boring:

                            .this.replaceToBoringFragment();

                            break;

                        case R.id.navigation_menu_girl:

                            .this.replaceToGirlFragment();

                            break;

                        case R.id.navigation_menu_setting:

                            break;

                    }
                }

                item.setChecked(true);

                drawerLayout.closeDrawer(Gravity.LEFT);

                lastSelectedMenu = item.getItemId();

                return true;
            }
        });


        navigationView.getMenu().getItem(0).setChecked(true);

        .this.replaceToRefreshFragment();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.refresh_menu,menu);
//        return true;
//    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {

            drawerLayout.closeDrawer(Gravity.LEFT);

        } else {

            if (System.currentTimeMillis() - time > 2000) {

                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();

                time = System.currentTimeMillis();

            } else {

                finish();
            }
        }
    }



    private void replaceToRefreshFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frameLayout, new RefreshFragment())
                .commit();
    }

    private void replaceToBoringFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frameLayout,new BoringFragment())
                .commit();

    }

    private void replaceToGirlFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frameLayout,new GirlFragment())
                .commit();

    }

}
