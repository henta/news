package my.hhx.com.newpager.modules.zhihu.mvp;

/**
 * Created by hhx on 2017/7/31.
 */

public interface ZhihuArticleContract {

    interface View {
        void loadFail();
        void loadSucess(ZhihuArticle zhihuArticle);
    }

    interface Presenter {
        void loadArticle(ZhihuDaily.StoriesBean storiesBean);
    }
}
