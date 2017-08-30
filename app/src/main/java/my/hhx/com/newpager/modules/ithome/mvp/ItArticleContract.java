package my.hhx.com.newpager.modules.ithome.mvp;

/**
 * Created by hhx on 2017/5/27.
 */

public interface ItArticleContract {
    interface View {
        void loadFail();
        void loadSucess(IthomeContentItem ithomeContentItem);
    }

    interface Presenter {
        void loadArticle(String newsId);
    }
}
