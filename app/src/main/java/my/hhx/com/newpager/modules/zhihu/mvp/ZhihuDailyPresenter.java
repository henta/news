package my.hhx.com.newpager.modules.zhihu.mvp;

import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import my.hhx.com.newpager.api.ApiManager;

/**
 * Created by hhx on 2017/6/18.
 */

public class ZhihuDailyPresenter implements ZhiHuDailyContract.Presenter {
    private ZhiHuDailyContract.View mZhihuDailyView;
    private String date;

    public ZhihuDailyPresenter(ZhiHuDailyContract.View zhihuDailyView) {
        mZhihuDailyView = zhihuDailyView;
    }

    @Override
    public void refreshData() {
        ApiManager.getInstence()
                .getmZhihuDailyService()
                .getZhiHuDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ZhihuDaily zhihuDaily) {
                        mZhihuDailyView.refreshSuccess(zhihuDaily);
                        date = zhihuDaily.getDate();
                        Log.i("refresh data", date);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mZhihuDailyView.refreshFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void loadData() {
        ApiManager.getInstence()
                .getmZhihuDailyService()
                .getBeforeZhihuDaily(date)
                .map(new Function<ZhihuDaily, List<ZhihuDaily.StoriesBean>>() {

                    @Override
                    public List<ZhihuDaily.StoriesBean> apply(@NonNull ZhihuDaily zhihuDaily) throws Exception {
                        date = zhihuDaily.getDate();
                        return zhihuDaily.getStories();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ZhihuDaily.StoriesBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<ZhihuDaily.StoriesBean> storiesBeen) {
                        Log.i("loadDate", date);
                        mZhihuDailyView.loadMoreSuccess(storiesBeen);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        mZhihuDailyView.loadMoreFail();

                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }

    @Override
    public void loadCache() {

    }

}
