package my.hhx.com.newpager.modules.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.wangyinews.mvp.WangyiNewsFragment;
import my.hhx.com.newpager.modules.zhihu.mvp.ZhiHuDailyFragment;

/**
 * Created by hhx on 2017/8/16.
 */

public class NewsFragment extends Fragment {
    @BindView(R.id.materialViewPager)
    MaterialViewPager materialViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        //setHasOptionsMenu(true);
        Toolbar toolbar = materialViewPager.getToolbar();
        toolbar.setTitle("新闻");
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_color_code));
        materialViewPager.getPagerTitleStrip().setIndicatorColorResource(R.color.md_white_color_code);
        materialViewPager.getPagerTitleStrip().setTextColorResource(R.color.md_white_color_code);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mTitle = new ArrayList<>();
        mFragment = new ArrayList<>();
        mTitle.add("知乎日报");
        mTitle.add("热点");
        mTitle.add("新闻");
        mTitle.add("科技");
        mFragment.add(ZhiHuDailyFragment.newInstance());
        mFragment.add(WangyiNewsFragment.newInstance());
        mFragment.add(ZhiHuDailyFragment.newInstance());
        mFragment.add(ZhiHuDailyFragment.newInstance());
        materialViewPager.getViewPager().setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment == null ? 0 : mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        materialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        materialViewPager.getViewPager().setOffscreenPageLimit(materialViewPager.getViewPager().getAdapter().getCount());
        materialViewPager.getPagerTitleStrip().setViewPager(materialViewPager.getViewPager());


    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main, menu);
//        menu.findItem(R.id.action_settings).setVisible(false);
        // super.onCreateOptionsMenu(menu, inflater);
    }
}
