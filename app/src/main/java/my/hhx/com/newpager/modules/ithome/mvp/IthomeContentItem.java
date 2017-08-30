package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hhx on 2017/8/27.
 */
@Root(name = "item",strict = false)
public class IthomeContentItem {

    @Element(name = "newssource")
    private String newssource;
    @Element(name = "newsauthor")
    private String newsauthor;
    @Element(name = "detail")
    private String detail;
    public IthomeContentItem(){

    }

    public String getNewssource() {
        return newssource;
    }

    public void setNewssource(String newssource) {
        this.newssource = newssource;
    }

    public String getAuthor() {
        return newsauthor;
    }

    public void setAuthor(String author) {
        this.newsauthor = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
