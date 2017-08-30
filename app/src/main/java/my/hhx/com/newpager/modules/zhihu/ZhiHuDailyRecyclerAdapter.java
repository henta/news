package my.hhx.com.newpager.modules.zhihu;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.zhihu.mvp.ZhihuDaily;

/**
 * Created by hhx on 2017/6/18.
 */

public class ZhiHuDailyRecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ZhihuDaily> mList;

    public ZhiHuDailyRecyclerAdapter(Context context, ArrayList<ZhihuDaily> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext)
                        .inflate(R.layout.zhihu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder myHolder= (ViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.zhihu_daily_item_iv)
        ImageView zhihuDailyItemIv;
        @BindView(R.id.zhihu_daily_item_title_tv)
        TextView zhihuDailyItemTitleTv;
        @BindView(R.id.zhihu_daily_item_time_tv)
        TextView zhihuDailyItemTimeTv;
        @BindView(R.id.zhihu_daily_item)
        CardView zhihuDailyItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
