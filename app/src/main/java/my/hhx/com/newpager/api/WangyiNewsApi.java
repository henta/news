package my.hhx.com.newpager.api;

import io.reactivex.Observable;
import my.hhx.com.newpager.modules.wangyinews.mvp.WangyiNews;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hhx on 2017/8/23.
 */

public interface WangyiNewsApi {
    @GET("headline/T1348647909107/{num}")
    Observable<WangyiNews> getWangyiFire(@Path("num") String num);

    @GET("list/{type}/{num}")
    Observable<WangyiNews> getWangyiList(@Path("type") String type, @Path("num") String num);



}
