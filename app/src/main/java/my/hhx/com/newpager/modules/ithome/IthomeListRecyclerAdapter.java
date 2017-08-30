package my.hhx.com.newpager.modules.ithome;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.ithome.mvp.ItItem;

/**
 * Created by hhx on 2017/8/23.
 */

public class IthomeListRecyclerAdapter extends BaseQuickAdapter<ItItem, BaseViewHolder> {

    public IthomeListRecyclerAdapter(List<ItItem> data) {
        super(R.layout.zhihu_item, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, ItItem item) {
        helper.setText(R.id.zhihu_daily_item_title_tv, item.getTitle());
        Glide.with(mContext).load(item.getImage()).crossFade().into((ImageView) helper.getView(R.id.zhihu_daily_item_iv));
        helper.setText(R.id.zhihu_daily_item_time_tv,item.getPostdate());
    }
}
