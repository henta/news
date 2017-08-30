package my.hhx.com.newpager.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hhx on 2017/6/23.
 */

public class HxBaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View mItemView;
    private HxBaseRecyclerAdapter.OnItemClickListener mListener;

    public HxBaseHolder(View itemView, HxBaseRecyclerAdapter.OnItemClickListener listener) {
        super(itemView);
        mItemView = itemView;
        mListener = listener;
        mItemView.setOnClickListener(this);
    }

    public View getItemView() {
        return mItemView;
    }

    public View getView(int resId) {
        View view = mItemView.findViewById(resId);
        return view;
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(mItemView,getAdapterPosition());

    }
}
