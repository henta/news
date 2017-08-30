package my.hhx.com.newpager.modules.zhihu.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.base.Card;
import my.hhx.com.newpager.base.HxBaseRecyclerAdapter;
import my.hhx.com.newpager.base.ZhihuDailyCard;
import my.hhx.com.newpager.base.ZhihuTopCard;

/**
 * Created by hhx on 2017/5/23.
 */

public class ZhiHuDailyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ZhiHuDailyContract.View {
    @BindView(R.id.zhihu_daily_swipe)
    SwipeRefreshLayout zhihuDailySwipe;
    @BindView(R.id.no_see_tv)
    TextView noSeeTv;
    @BindView(R.id.zhihu_daily_recycler)
    RecyclerView zhihuDailyRecycler;
    private ZhihuDailyPresenter zhihuDailyPresenter = new ZhihuDailyPresenter(this);
    private ArrayList<ZhihuDaily.StoriesBean> mList;
    private ArrayList<ZhihuDaily.TopStoriesBean> mTopList;
    private HxBaseRecyclerAdapter mAdapter;
    private boolean isFresh = false;
    private boolean isLoading = false;
    Unbinder unbinder;

    public static ZhiHuDailyFragment newInstance() {
        return new ZhiHuDailyFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar) getActivity().findViewById(R.id.toolbar));
        initView();
        return view;
    }

    public void initView() {
        mList = new ArrayList<>();
        mTopList = new ArrayList<>();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        zhihuDailyRecycler.setLayoutManager(linearLayoutManager);
        zhihuDailySwipe.setColorSchemeResources(R.color.md_pink_100_color_code,
                R.color.md_pink_200_color_code,
                R.color.md_pink_300_color_code,
                R.color.md_pink_400_color_code);
        zhihuDailySwipe.setOnRefreshListener(this);
        mAdapter = new HxBaseRecyclerAdapter();
        zhihuDailyRecycler.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        refreshData();
        zhihuDailyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1 && !isLoading && dy > 0) {
                    isLoading = true;
                    Log.i("Loading", String.valueOf(isLoading));
                    zhihuDailyPresenter.loadData();

                }

            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void refreshFail() {
        //没数据时才会显示无数据，有数据不变
        if (mList.isEmpty()) {
            noSeeTv.setVisibility(View.VISIBLE);
            zhihuDailySwipe.setRefreshing(true);
            Toast.makeText(getActivity(), "无法加载数据", Toast.LENGTH_SHORT).show();
        } else {
            zhihuDailySwipe.setRefreshing(true);
            Toast.makeText(getActivity(), "无法加载数据，请检查网络后重试", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void refreshData() {
        isFresh = true;
        zhihuDailyPresenter.refreshData();
    }

    @Override
    public void refreshSuccess(final ZhihuDaily zhihuDaily) {
        if (zhihuDaily == null) {
            noSeeTv.setVisibility(View.VISIBLE);
            zhihuDailySwipe.requestFocus();
            return;
        } else {
            noSeeTv.setVisibility(View.GONE);
        }
        if (mList != null) {
            mList.clear();
        }
        if (mTopList != null) {
            mTopList.clear();
        }
        mAdapter.clear();
        mList.addAll(zhihuDaily.getStories());
        mTopList.addAll(zhihuDaily.getTop_stories());
        mAdapter.setData(getCard(zhihuDaily.getStories()));
        mAdapter.notifyDataSetChanged();
        zhihuDailyRecycler.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new HxBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ZhihuArticleActivity.class);
                intent.putExtra("article", mList.get(position - 1));
                startActivity(intent);
            }
        });
        isFresh = false;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        refreshData();
        zhihuDailySwipe.setRefreshing(false);
    }

    @Override
    public void loadMoreSuccess(List<ZhihuDaily.StoriesBean> storiesBeen) {
        mList.addAll(storiesBeen);
        mAdapter.addAll(getCard(storiesBeen));
        isLoading = false;
    }

    @Override
    public void loadMoreFail() {
        Toast.makeText(getActivity(), "无法加载数据，请检查网络设置", Toast.LENGTH_SHORT).show();
    }

    public List<Card> getCard(List<ZhihuDaily.StoriesBean> storiesBean) {
        List<Card> cards = new ArrayList<>();
        if (isFresh) {
            cards.add(new ZhihuTopCard(mTopList));
        }
        for (int i = 0; i < storiesBean.size(); i++) {
            cards.add(new ZhihuDailyCard(storiesBean.get(i)));
        }
        return cards;
    }

}
