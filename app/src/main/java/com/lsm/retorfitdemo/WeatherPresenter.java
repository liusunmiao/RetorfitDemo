package com.lsm.retorfitdemo;

import com.lsm.retorfitdemo.base.mvp.BasePresenter;
import com.lsm.retorfitdemo.net.BaseSubscriber;
import com.lsm.retorfitdemo.net.WeatherInfo;

/**
 * 具体的presenter 将view和model进行绑定
 */
public class WeatherPresenter extends BasePresenter<WeatherView<WeatherInfo>> {
    private WeatherModel weatherModel;
    public WeatherPresenter(){
        weatherModel= new WeatherModel() {
            @Override
            protected String getParmas() {
                return getView().getParmas();
            }
        };
    }
    @Override
    public void doPresenter() {
        getView().onLoading();
        weatherModel.getBackResult().subscribe(new BaseSubscriber<WeatherInfo>() {

            @Override
            public void onError(Throwable e) {
                getView().onFailure(e);
            }

            @Override
            public void onNext(WeatherInfo weatherInfo) {
                getView().onSuccess(weatherInfo);
            }
        });
    }
}
