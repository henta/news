package my.hhx.com.newpager.modules.ithome.mvp;

import java.util.List;

/**
 * Created by hhx on 2017/5/27.
 */

public interface IthomeListContract {
    interface View {
        void initView();

        void refreshFail();

        void refreshData();

        void refreshSuccess(List<ItItem> itItems);

        void loadMoreSuccess(List<ItItem> itItems);

        void loadMoreFail();


    }

    interface Presenter {
        void loadData();

        void loadCache();

        void refreshData();
    }
}
