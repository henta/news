package my.hhx.com.newpager.modules.zhihu.mvp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hhx on 2017/5/23.
 */

public class ZhihuDaily implements Serializable {


    /**
     * date : 20170523
     * stories : [{"images":["https://pic2.zhimg.com/v2-050f2af92fffb5e33215f5bba7765ed5.jpg"],"type":0,"id":9436399,"ga_prefix":"052317","title":"什么电影卖座就赶紧攒一个卖给观众，这算不上好电影"},{"images":["https://pic2.zhimg.com/v2-164c76ca9e067db2c35af4a742e825c1.jpg"],"type":0,"id":9436185,"ga_prefix":"052316","title":"刑法角度来看《白夜行》，也是一声叹息"},{"images":["https://pic3.zhimg.com/v2-913d3b9ab60dcf91e24d87f65d0cd6da.jpg"],"type":0,"id":9434316,"ga_prefix":"052315","title":"「它好小好可爱啊，可为什么我却想一脚踢飞？」"},{"images":["https://pic3.zhimg.com/v2-8ba2b05c3ecf24e666359c286596ae46.jpg"],"type":0,"id":9436410,"ga_prefix":"052314","title":"柯洁仍是人类最强，但 AlphaGo 重演了一部进化史"},{"images":["https://pic1.zhimg.com/v2-fc71a7f1153abe7e16cd56163e92b2a8.jpg"],"type":0,"id":9436320,"ga_prefix":"052313","title":"「适当的献血有利于身体健康」是宣传还是科学事实？"},{"images":["https://pic2.zhimg.com/v2-55cf813537e3e76db3b0858be3fcd511.jpg"],"type":0,"id":9434926,"ga_prefix":"052312","title":"大误 · 我判自己死刑"},{"title":"机场是怎么设计出来的？","ga_prefix":"052310","images":["https://pic1.zhimg.com/v2-d8d29950ffca1658639ecc65bf202da4.jpg"],"multipic":true,"type":0,"id":9436006},{"images":["https://pic1.zhimg.com/v2-653de91043c7328bf24ed896d184bd20.jpg"],"type":0,"id":9434678,"ga_prefix":"052309","title":"你为什么单身？这也是一场博弈啊"},{"images":["https://pic4.zhimg.com/v2-feebe62acc3bd2cc69efd5cfc14cb17b.jpg"],"type":0,"id":9435580,"ga_prefix":"052308","title":"买二手房的 11 个常见经验和教训"},{"images":["https://pic3.zhimg.com/v2-641ac882f3cf680b2e437c45274f47ee.jpg"],"type":0,"id":9435572,"ga_prefix":"052307","title":"「伟大」二字，在他们身上当之无愧"},{"images":["https://pic3.zhimg.com/v2-d2bc3803bdee2450e6292a97e2929e4a.jpg"],"type":0,"id":9435554,"ga_prefix":"052307","title":"AlphaGo 对围棋的理解比人类高一个境界，不禁为柯洁捏了把汗"},{"images":["https://pic3.zhimg.com/v2-9bdcb162bca9d6e959fe1283ee8871c2.jpg"],"type":0,"id":9434894,"ga_prefix":"052307","title":"央视说「100 升可燃冰能让汽车跑 5 万公里」为什么错了？"},{"images":["https://pic3.zhimg.com/v2-a7ff280188970f0f604711e3ed681b96.jpg"],"type":0,"id":9432173,"ga_prefix":"052306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-96db4cdeae99d481395ea6d5656415c8.jpg","type":0,"id":9436410,"ga_prefix":"052314","title":"柯洁仍是人类最强，但 AlphaGo 重演了一部进化史"},{"image":"https://pic4.zhimg.com/v2-3824d1389aecc7ab6b7dbe98fff6778b.jpg","type":0,"id":9435554,"ga_prefix":"052307","title":"AlphaGo 对围棋的理解比人类高一个境界，不禁为柯洁捏了把汗"},{"image":"https://pic3.zhimg.com/v2-9766535a937abd80721f6b828549e452.jpg","type":0,"id":9434894,"ga_prefix":"052307","title":"央视说「100 升可燃冰能让汽车跑 5 万公里」为什么错了？"},{"image":"https://pic2.zhimg.com/v2-5ec11e9f2672d93b69b5fbf15910df6d.jpg","type":0,"id":9434773,"ga_prefix":"052220","title":"面对更强的 AlphaGo，柯洁能带来奇迹吗？"},{"image":"https://pic3.zhimg.com/v2-4ac454e049dc68499bf36cc049326e46.jpg","type":0,"id":9434326,"ga_prefix":"052216","title":"贾跃亭辞任总经理：闹了大半年，乐视这次能否度过危机？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean implements Serializable {
        /**
         * images : ["https://pic2.zhimg.com/v2-050f2af92fffb5e33215f5bba7765ed5.jpg"]
         * type : 0
         * id : 9436399
         * ga_prefix : 052317
         * title : 什么电影卖座就赶紧攒一个卖给观众，这算不上好电影
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean implements Serializable{
        /**
         * image : https://pic1.zhimg.com/v2-96db4cdeae99d481395ea6d5656415c8.jpg
         * type : 0
         * id : 9436410
         * ga_prefix : 052314
         * title : 柯洁仍是人类最强，但 AlphaGo 重演了一部进化史
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
