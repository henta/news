package my.hhx.com.newpager.modules.channel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.hhx.com.newpager.MessageEvent;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.util.CacheUtil;


public class ChannelActivity extends AppCompatActivity {

    @BindView(R.id.channel_recycler)
    RecyclerView channelRecycler;
    private String myTitle[] = new String[]{"最新", "评测室", "安卓", "手机", "数码"};
    private String otherTitle[] = new String[]{"电脑", "极客学院", "vr", "智能汽车", "游戏电竞", "windows", "发布会", "阳台", "苹果", "科普", "网络焦点", "行业前沿"};
    private String myTag[] = new String[]{"news", "labs", "android", "phone", "digi"};
    private String otherTag[] = new String[]{"pc", "geek", "vr", "auto", "game", "windows", "live", "balcony", "ios", "discovery", "internet", "it"};
    private ArrayList<ChannelEntity> myList = new ArrayList<>();
    private ArrayList<ChannelEntity> otherList = new ArrayList<>();
    private CacheUtil cacheUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);
        initData();
        init();
    }

    private void init() {

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        channelRecycler.setLayoutManager(manager);

        final ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(channelRecycler);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, myList, otherList);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        channelRecycler.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, myList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnCompleteListener(new ChannelAdapter.OnCompleteListener() {
            @Override
            public void onComplete(ArrayList<ChannelEntity> myChannelItems, ArrayList<ChannelEntity> otherChannelItems) {
                Log.i("channelmy", myChannelItems.toString());
                Log.i("channelother", otherChannelItems.toString());
                cacheUtil = CacheUtil.get(ChannelActivity.this);
                cacheUtil.put("myChannel", myChannelItems);
                cacheUtil.put("otherChannel", otherChannelItems);
                EventBus.getDefault().post(new MessageEvent("refreshFragment"));
            }
        });

    }


    private void initData() {
        cacheUtil = CacheUtil.get(ChannelActivity.this);
        if (cacheUtil.getAsObject("myChannel") == null) {

            for (int i = 0; i < myTitle.length; i++) {
                ChannelEntity channelEntity = new ChannelEntity();
                channelEntity.setName(myTitle[i]);
                channelEntity.setTag(myTag[i]);
                myList.add(channelEntity);
            }
            for (int i = 0; i < otherTitle.length; i++) {
                ChannelEntity channelEntity = new ChannelEntity();
                channelEntity.setName(otherTitle[i]);
                channelEntity.setTag(otherTag[i]);
                otherList.add(channelEntity);
            }
        } else {
            if (myList != null && otherList != null) {
                myList.clear();
                otherList.clear();
                myList.addAll((ArrayList<ChannelEntity>) cacheUtil.getAsObject("myChannel"));
                otherList.addAll((ArrayList<ChannelEntity>) cacheUtil.getAsObject("otherChannel"));

            }
        }


    }
}
