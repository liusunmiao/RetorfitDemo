package com.lsm.retorfitdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lsm.retorfitdemo.base.mvp.BasePresenter;
import com.lsm.retorfitdemo.base.mvp.BaseView;
import com.lsm.retorfitdemo.base.proxy.IMvpActivityProxy;
import com.lsm.retorfitdemo.base.proxy.MvpActivityImp;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    private P mPresenter;
    //使用了静态代理的方式
    private IMvpActivityProxy proxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        mPresenter = createPresenter();
        //绑定请求
        mPresenter.attach(this);
        proxy = new MvpActivityImp(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //进行解绑
        proxy.unbindPresenter();
        //进行view和model的解绑
        mPresenter.detach();
    }

    /**
     * 通过直接new的方式创建presenter对象
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置布局
     */
    protected abstract void setContentView();

    /**
     * 如果不使用注解和反射的方式实例化presenter的话，可以通过getPresenter()获取创建好的
     * prsenter对象进行数据请求
     *
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }
}
