package my.hhx.com.newpager.modules.zhihu.mvp;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import my.hhx.com.newpager.api.ApiManager;

/**
 * Created by hhx on 2017/7/31.
 */

public class ZhihuArticlePresenter implements ZhihuArticleContract.Presenter {
    private ZhihuArticleActivity mView;

    public ZhihuArticlePresenter(ZhihuArticleActivity zhihuArticleActivity) {
        mView = zhihuArticleActivity;
    }


    @Override
    public void loadArticle(ZhihuDaily.StoriesBean storiesBean) {
        int id = storiesBean.getId();
        ApiManager.getInstence()
                .getmZhihuDailyService()
                .getZhihuArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuArticle>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ZhihuArticle zhihuArticle) {
                        mView.loadSucess(zhihuArticle);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("LoadZhihuArticle", "error");
                        mView.loadFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
