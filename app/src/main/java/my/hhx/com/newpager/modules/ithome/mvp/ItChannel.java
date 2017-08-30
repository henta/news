package my.hhx.com.newpager.modules.ithome.mvp;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by hhx on 2017/8/23.
 */
@Root(name = "channel",strict = false)
public class ItChannel {
    @ElementList( inline = true,name = "items")
    private ArrayList<ItItem> itItems;
    public ItChannel(){

    }
    public ArrayList<ItItem> getItItems() {
        return itItems;
    }

    public void setItItems(ArrayList<ItItem> itItems) {
        this.itItems = itItems;
    }


}


