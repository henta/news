package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Glooory on 2016/10/13 0013 10:11.
 */

@Root(name = "rss", strict = false)
public class IthomeContentResponse {

    @Element(name = "channel")
    private IthomeContentChannel ithomeContentChannel;
    public IthomeContentResponse(){

    }
    public IthomeContentChannel getIthomeContentChannel() {
        return ithomeContentChannel;
    }

    public void setIthomeContentChannel(IthomeContentChannel ithomeContentChannel) {
        this.ithomeContentChannel = ithomeContentChannel;
    }
}
