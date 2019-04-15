package com.lsm.retorfitdemo.base.mvp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V extends BaseView> {
    private V mView;
    private V mProxyView;

    /**
     * 绑定
     *
     * @param view
     */
    public void attach(V view) {
        this.mView = view;
        //使用动态代理
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (mView == null) {
                    //没有绑定直接返回
                    return null;
                }
                //绑定调用invoke去执行
                return method.invoke(mView, args);
            }
        });
    }

    /**
     * 解绑
     */
    public void detach() {
        this.mView = null;
    }
    public V getView(){
        return mProxyView;
    }


    public abstract void doPresenter();
}
