package my.hhx.com.newpager.api;


import io.reactivex.Observable;
import my.hhx.com.newpager.modules.ithome.mvp.IthomeContentResponse;
import my.hhx.com.newpager.modules.ithome.mvp.ITResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Glooory on 2016/10/12 0012 12:42.
 */

public interface ITHomeApi {

    //请求IT之家最新的数据
    //http://api.ithome.com/xml/newslist/news.xml
    @GET("xml/newslist/{tag}.xml")
    Observable<ITResponse> getITHomeNewest(@Path("tag") String tag);

    //请求更多IT之家的新闻数据
    //http://api.ithome.com/xml/newslist/news_05bffc036ce4305d.xml
    @GET("xml/newslist/{tag}_{lastItemId}.xml")
    Observable<ITResponse> getITHomeMore(@Path("tag") String tag, @Path("lastItemId") String lastItemId);

    //根据 newsId 请求具体的新闻内容
    //http://api.ithome.com/xml/newscontent/264/048.xml
    @GET("xml/newscontent/{newsId}.xml")
    Observable<IthomeContentResponse> getNewsContent(@Path("newsId") String newsId);


}
