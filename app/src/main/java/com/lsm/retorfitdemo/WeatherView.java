package com.lsm.retorfitdemo;

import com.lsm.retorfitdemo.base.mvp.BaseView;

/**
 * 具体的view层接口，里面的方法可以根据具体的业务场景去写
 * @param <T>
 */
public interface WeatherView<T> extends BaseView {
    /**
     * 获取请求参数或者链接
     * @return
     */
    String getParmas();
    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载成功
     */
    void onSuccess(T t);

    /**
     * 加载失败
     * @param t
     */
    void onFailure(Throwable t);
}
