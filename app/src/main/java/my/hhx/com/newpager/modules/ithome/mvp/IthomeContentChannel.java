package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hhx on 2017/8/27.
 */
@Root(name = "channel",strict = false)
public class IthomeContentChannel {
    @Element(name ="item" )
    private IthomeContentItem ithomeContentItem;

    public IthomeContentChannel(){

    }
    public IthomeContentItem getIthomeContentItem() {
        return ithomeContentItem;
    }

    public void setIthomeContentItem(IthomeContentItem ithomeContentItem) {
        this.ithomeContentItem = ithomeContentItem;
    }
}
