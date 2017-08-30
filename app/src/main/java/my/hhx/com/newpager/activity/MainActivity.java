package my.hhx.com.newpager.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.ithome.mvp.IthomeFragment;
import my.hhx.com.newpager.modules.news.NewsFragment;

public class MainActivity extends DrawerActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment mIthomeFragment;
    private Fragment mNewsFragment;
    private FragmentManager mFragmentManager;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        mNewsFragment = new NewsFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.content_main, mNewsFragment);
        ft.commit();
        currentFragment = mNewsFragment;


    }

    private void initView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_news);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_it_home:
                if (mIthomeFragment == null) {
                    mIthomeFragment = new IthomeFragment();
                }
                replaceFragment(mIthomeFragment);
                currentFragment=mIthomeFragment;
                break;
            case R.id.nav_news:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                }
                replaceFragment(mNewsFragment);
                currentFragment=mNewsFragment;
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换界面
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(fragment.isAdded()){
            fragmentTransaction.hide(currentFragment).show(fragment).commit();
        }else {
            fragmentTransaction.hide(currentFragment).add(R.id.content_main,fragment).commit();
        }
    }

}
