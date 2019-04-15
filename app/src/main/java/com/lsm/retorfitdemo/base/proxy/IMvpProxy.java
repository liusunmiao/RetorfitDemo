package com.lsm.retorfitdemo.base.proxy;

public interface IMvpProxy {
    /**
     * 创建并绑定Presenter
     */
    void bindAndCreatePresenter();

    /**
     * 解绑presenter
     */
    void unbindPresenter();
}
