package com.example.coder.jiandan_md;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_menu_fresh:

                        MainActivity.this.replaceToRefreshFragment();

                        break;

                    case R.id.navigation_menu_setting:

                        break;

                }

                item.setChecked(true);

                drawerLayout.closeDrawer(Gravity.LEFT);

                return true;
            }
        });


        navigationView.getMenu().getItem(0).setChecked(true);

        MainActivity.this.replaceToRefreshFragment();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
}
