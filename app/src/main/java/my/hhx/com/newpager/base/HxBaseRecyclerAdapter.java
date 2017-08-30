package my.hhx.com.newpager.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhx on 2017/6/23.
 */

public class HxBaseRecyclerAdapter<C extends Card> extends RecyclerView.Adapter<HxBaseHolder> {
    protected List<C> mData;
    public static int ZHIHU_DAILY_CARD = 0;
    public static int ZHIHU_TOP_CARD = 1;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onClick(View view,int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public HxBaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public HxBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            if (viewType == mData.get(i).getItemType()) {
                return mData.get(i).onCreateViewHolder(parent, viewType,mListener);
            }
        }
        throw new RuntimeException("wrong viewType");
    }

    @Override
    public void onBindViewHolder(HxBaseHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<C> data) {
        addAll(data);
    }

    public void add(C card) {
        mData.add(card);
        int index = mData.indexOf(card);
        notifyItemChanged(index);
    }

    public void addAll(List<C> card) {
        if (card == null || card.size() == 0) {
            return;
        }
        mData.addAll(card);
        notifyItemRangeChanged(mData.size() - card.size(), mData.size());
    }

    public void remove(C card) {
        int indexOfCell = mData.indexOf(card);
        remove(indexOfCell);
    }

    public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * @param start
     * @param count
     */
    public void remove(int start, int count) {
        if ((start + count) > mData.size()) {
            return;
        }

        mData.subList(start, start + count).clear();

        notifyItemRangeRemoved(start, count);
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }
}
