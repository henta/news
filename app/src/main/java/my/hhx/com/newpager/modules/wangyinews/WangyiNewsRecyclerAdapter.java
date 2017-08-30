package my.hhx.com.newpager.modules.wangyinews;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.wangyinews.mvp.WangyiNews;

/**
 * Created by hhx on 2017/8/23.
 */

public class WangyiNewsRecyclerAdapter extends BaseQuickAdapter<WangyiNews,BaseViewHolder> {
    public WangyiNewsRecyclerAdapter(@Nullable List<WangyiNews> data) {
        super(R.layout.zhihu_item,data);
//        super(null);
//        //Step.1
//        setMultiTypeDelegate(new MultiTypeDelegate<WangyiNews>() {
//            @Override
//            protected int getItemType(WangyiNews entity) {
//                //infer the type by entity
//                return entity.type;
//            }
//        });
//        //Step.2
//        getMultiTypeDelegate()
//                .registerItemType(Entity.TEXT, R.layout.item_text_view)
//                .registerItemType(Entity.IMG, R.layout.item_image_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, WangyiNews item) {

    }
}
