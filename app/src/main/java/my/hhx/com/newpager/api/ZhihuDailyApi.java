package my.hhx.com.newpager.api;

import io.reactivex.Observable;
import my.hhx.com.newpager.modules.zhihu.mvp.ZhihuArticle;
import my.hhx.com.newpager.modules.zhihu.mvp.ZhihuDaily;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hhx on 2017/5/23.
 */

public interface ZhihuDailyApi {
    //最近的日报
    @GET("news/latest")
    Observable<ZhihuDaily> getZhiHuDaily();

    //获取某一时间之前的日报（本例用于加载更多）
    @GET("news/before/{date}")
    Observable<ZhihuDaily> getBeforeZhihuDaily(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhihuArticle> getZhihuArticle(@Path("id") int id);
}
