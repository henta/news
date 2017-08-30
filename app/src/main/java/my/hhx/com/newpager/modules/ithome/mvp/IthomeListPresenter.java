package my.hhx.com.newpager.modules.ithome.mvp;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import my.hhx.com.newpager.api.ApiManager;
import my.hhx.com.newpager.util.ITHomeUtils;

/**
 * Created by hhx on 2017/8/24.
 */

public class IthomeListPresenter implements IthomeListContract.Presenter {
    private String mTag;
    private IthomeListContract.View mView;
    private String mLastId;
    private ArrayList<ItItem> mList = new ArrayList<>();

    public IthomeListPresenter(IthomeListContract.View view, String tag) {
        mTag = tag;
        mView = view;
    }

    @Override
    public void loadData() {
        ApiManager.getInstence()
                .getmItHomeService()
                .getITHomeMore(mTag, ITHomeUtils.getMinNewsId(mLastId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ITResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ITResponse itResponse) {
                        if (mList != null) {
                            mList.clear();
                        }
                        mList = itResponse.getChannel().getItItems();
                        mLastId = mList.get(mList.size() - 1).getNewsid();
                        mView.loadMoreSuccess(mList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.loadMoreFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void loadCache() {

    }

    @Override
    public void refreshData() {
        ApiManager.getInstence()
                .getmItHomeService()
                .getITHomeNewest(mTag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ITResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ITResponse itResponse) {
                        mList = itResponse.getChannel().getItItems();
                        mLastId = mList.get(mList.size() - 1).getNewsid();
                        Log.e("itPressent", mLastId);
                        Log.e("itPressent", mList.get(0).toString());
                        mView.refreshSuccess(mList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("itPressent", "error");
                        mView.refreshFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
