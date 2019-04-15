package com.lsm.retorfitdemo;

import com.lsm.retorfitdemo.base.mvp.BaseModel;
import com.lsm.retorfitdemo.net.RetorfitClient;
import com.lsm.retorfitdemo.net.WeatherInfo;

import rx.Observable;


/**
 * 具体的Model类 做一些网络请求逻辑处理
 */
public abstract class WeatherModel implements BaseModel<WeatherInfo> {
    @Override
    public Observable<WeatherInfo> getBackResult() {
//        query?city=深圳&key=b70622d1ff2effbb1846e23503f79f49
        return RetorfitClient.getServiceApi()
                .getWeather(getParmas())
                .compose(RetorfitClient.<WeatherInfo>transformer());
    }

    /**
     * 获取请求参数或或者链接
     * @return
     */
    protected abstract String getParmas();

}
