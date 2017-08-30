package my.hhx.com.newpager.base;

import android.view.ViewGroup;

/**
 * Created by hhx on 2017/6/23.
 */

public interface Card {
    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */

    HxBaseHolder onCreateViewHolder(ViewGroup parent, int viewType, HxBaseRecyclerAdapter.OnItemClickListener listener);

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    void onBindViewHolder(HxBaseHolder holder, int position);

    /**
     * 获取viewType
     *
     * @return
     */
    int getItemType();

    /**
     * 回收资源
     */
    void releaseResource();
}
