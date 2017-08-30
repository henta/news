package my.hhx.com.newpager.modules.wangyinews.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.wangyinews.WangyiNewsRecyclerAdapter;

/**
 * Created by hhx on 2017/5/23.
 */

public class WangyiNewsFragment extends Fragment {
    @BindView(R.id.wangyi_recycler)
    RecyclerView wangyiRecycler;
    private WangyiNewsRecyclerAdapter mAdapter;
    private List<WangyiNews> mList;
    private WangyiNews wangyiNews;
    Unbinder unbinder;

    public static WangyiNewsFragment newInstance() {
        return new WangyiNewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wangyi_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(wangyiNews);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        wangyiRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new WangyiNewsRecyclerAdapter(mList);
        wangyiRecycler.setAdapter(mAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
