package my.hhx.com.newpager.modules.ithome.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.ithome.IthomeListRecyclerAdapter;

/**
 * Created by hhx on 2017/8/23.
 */

public class IthomeListFragment extends Fragment implements IthomeListContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.it_list_recycler)
    RecyclerView itListRecycler;
    IthomeListRecyclerAdapter mAdapter;
    @BindView(R.id.it_list_refresh)
    SwipeRefreshLayout itListRefresh;
    private List<ItItem> mList = new ArrayList<>();
    Unbinder unbinder;
    private static String mTag;
    private boolean isrefresh = false;
    private IthomeListPresenter ithomeListPresenter = new IthomeListPresenter(this, mTag);

    public static IthomeListFragment newInstance(String tag) {
        mTag = tag;
        return new IthomeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_it_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        refreshData();
        return view;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        itListRecycler.setLayoutManager(linearLayoutManager);
        itListRefresh.setColorSchemeResources(R.color.md_pink_100_color_code,
                R.color.md_pink_200_color_code,
                R.color.md_pink_300_color_code,
                R.color.md_pink_400_color_code);
        itListRefresh.setOnRefreshListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void refreshFail() {

    }

    @Override
    public void refreshData() {
        ithomeListPresenter.refreshData();


    }

    @Override
    public void refreshSuccess(List<ItItem> itItems) {
        isrefresh = false;
        if (itItems == null) {
            return;
        }
        if (mList != null) {
            mList.clear();
        }
        mList.addAll(itItems);
        if (mAdapter != null && mList != null) {
            mAdapter.setNewData(mList);

        }
        //第一次加载页面才走
        if (mAdapter == null) {
            mAdapter = new IthomeListRecyclerAdapter(mList);
            itListRecycler.setAdapter(mAdapter);
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    ithomeListPresenter.loadData();

                }
            }, itListRecycler);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getContext(), ItArticleActivity.class);
                    intent.putExtra("newsId",mList.get(position).getNewsid());
                    intent.putExtra("title",mList.get(position).getTitle());
                    startActivity(intent);
                }
            });
        }


    }

    @Override
    public void loadMoreSuccess(List<ItItem> itItems) {
        if (itItems == null) {
            mAdapter.loadMoreEnd();
            return;
        }
        mAdapter.addData(itItems);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void loadMoreFail() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void onRefresh() {
        if (!isrefresh) {
            refreshData();
            itListRefresh.setRefreshing(false);
            isrefresh = true;
        }


    }
}
