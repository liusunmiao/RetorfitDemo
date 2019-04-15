package com.lsm.retorfitdemo.base.proxy;

import com.lsm.retorfitdemo.base.inject.InjectPresenter;
import com.lsm.retorfitdemo.base.mvp.BasePresenter;
import com.lsm.retorfitdemo.base.mvp.BaseView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MvpProxyImp<V extends BaseView> implements IMvpProxy {
    private V mView;
    private List<BasePresenter> presenterList;

    public MvpProxyImp(V mView) {
        this.mView = mView;
        presenterList = new ArrayList<>();
        bindAndCreatePresenter();
    }

    @Override
    public void bindAndCreatePresenter() {
        //动态创建Presenter
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取对应的注解
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                Class<? extends BasePresenter> presenterClazz = null;
                try {
                    //泛型擦除
                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
                    if(!BasePresenter.class.isAssignableFrom(presenterClazz)){
                        //这个class是不是继承自BasePresenter 如果不是就抛异常
                        throw new RuntimeException("No support inject persenter type"+presenterClazz.getSimpleName());
                    }
                    //通过反射实例化对象
                    BasePresenter presenter = presenterClazz.newInstance();
                    //进行绑定
                    presenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView, presenter);
                    presenterList.add(presenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        //进行解绑
        for (BasePresenter presenter : presenterList) {
            presenter.detach();
        }
    }
}
