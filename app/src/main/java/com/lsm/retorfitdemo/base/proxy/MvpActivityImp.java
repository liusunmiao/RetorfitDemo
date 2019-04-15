package com.lsm.retorfitdemo.base.proxy;

import com.lsm.retorfitdemo.base.mvp.BaseView;

public class MvpActivityImp extends MvpProxyImp implements IMvpActivityProxy{

    public MvpActivityImp(BaseView mView) {
        super(mView);
    }
}
