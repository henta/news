package my.hhx.com.newpager.modules.ithome.mvp;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import my.hhx.com.newpager.api.ApiManager;

/**
 * Created by hhx on 2017/8/27.
 */

public class ItArticlePresenter implements ItArticleContract.Presenter {
    private ItArticleContract.View mView;
    public ItArticlePresenter(ItArticleContract.View view){
        mView=view;
    }

    @Override
    public void loadArticle(String newsId) {
        Log.e("itcontentpresenter",newsId);
        ApiManager.getInstence()
                .getmItHomeService()
                .getNewsContent(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IthomeContentResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IthomeContentResponse ithomeContentResponse) {
                       // Log.e("itcontentpresenter", ithomeContentResponse.toString());
                        mView.loadSucess(ithomeContentResponse.getIthomeContentChannel().getIthomeContentItem());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("itcontentpresenter","error");
                        mView.loadFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
