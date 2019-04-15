package com.lsm.retorfitdemo.net;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网络请求接口
 */
public interface ServiceApi {
    /**
     * 天气请求
     * @return
     */
    @GET
    Observable<Result<WeatherInfo>> getWeather(@Url String url);
}
