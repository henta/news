package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Glooory on 2016/10/12 0012 12:23.
 */

@Root(name = "rss", strict = false)
public class ITResponse {
    public ITResponse() {

    }

    @Element(name = "channel")
    ItChannel channel;
    @Attribute(name = "version")
    String version;

    public ItChannel getChannel() {
        return channel;
    }

    public void setChannel(ItChannel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
