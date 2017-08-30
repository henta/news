package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hhx on 2017/8/25.
 */
@Root(name = "item", strict = false)
public class ItItem {
    @Attribute(name = "t", required = false)
    private String t;

    @Element(name = "newsid")
    private String newsid;

    @Element(name = "title")
    private String title;

    @Element(name = "c", required = false)
    private String c;

    @Element(required = false, name = "v")
    private String v;

    @Element(name = "url")
    private String url;

    @Element(name = "postdate")
    private String postdate;

    @Element(name = "image")
    private String image;

    @Element(required = false, name = "description")//处理可能为空的情况
    private String description;

    @Element(required = false, name = "hitcount")
    private int hitcount;

    @Element(required = false, name = "commentcount")
    private int commentcount;

    @Element(required = false, name = "forbidcomment")
    private String forbidcomment;

    @Element(required = false, name = "tags")
    private String tags;

    @Element(required = false, name = "z")
    private String z;

    @Element(required = false, name = "cid")
    private int cid;

    @Element(required = false, name = "live")
    private int live;

    public ItItem() {

    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHitcount() {
        return hitcount;
    }

    public void setHitcount(int hitcount) {
        this.hitcount = hitcount;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public String getForbidcomment() {
        return forbidcomment;
    }

    public void setForbidcomment(String forbidcomment) {
        this.forbidcomment = forbidcomment;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "ItItem{" +
                "t='" + t + '\'' +
                ", newsid='" + newsid + '\'' +
                ", title='" + title + '\'' +
                ", c='" + c + '\'' +
                ", v='" + v + '\'' +
                ", url='" + url + '\'' +
                ", postdate='" + postdate + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", hitcount=" + hitcount +
                ", commentcount=" + commentcount +
                ", forbidcomment='" + forbidcomment + '\'' +
                ", tags='" + tags + '\'' +
                ", z='" + z + '\'' +
                ", cid=" + cid +
                ", live=" + live +
                '}';
    }
}
