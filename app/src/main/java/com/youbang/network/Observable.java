package com.youbang.network;


/**
 * Created by yxt on 2017/5/11 0011.
 */
public class Observable<T> {
    private OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }



    public interface OnSubscribe<T> extends Action1<Observer<T>> {

    }

    public static <T>  Observable<T> create(OnSubscribe<T> subscribe) {
        return new Observable<T>(subscribe);
    }
    public void subscribe(Observer<T> observer) {
        Observable.subscribe(observer, this);
    }
    private static <T> void subscribe(Observer<T> observer, Observable<T> observable) {
        observable.onSubscribe.call(observer);
    }
}
