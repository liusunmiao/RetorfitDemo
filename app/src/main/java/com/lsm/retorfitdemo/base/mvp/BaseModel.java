package com.lsm.retorfitdemo.base.mvp;

import rx.Observable;

/**
 * Model接口 后面的具体model实现该接口 并实现Observable方法
 * @param <T>
 */
public interface BaseModel<T> {
    Observable<T> getBackResult();
}
