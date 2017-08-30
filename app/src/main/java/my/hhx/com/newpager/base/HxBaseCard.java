package my.hhx.com.newpager.base;

/**
 * Created by hhx on 2017/6/23.
 */

public abstract class HxBaseCard<T> implements Card {
    public T mData;

    public HxBaseCard(T t) {
        mData = t;
    }
}
