package com.lsm.retorfitdemo.net;

import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }
}
