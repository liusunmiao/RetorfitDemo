package com.lsm.retorfitdemo;

import android.widget.TextView;

import com.lsm.retorfitdemo.base.BaseActivity;
import com.lsm.retorfitdemo.base.inject.InjectPresenter;
import com.lsm.retorfitdemo.net.WeatherInfo;

public class MainActivity extends BaseActivity<WeatherPresenter> implements WeatherView<WeatherInfo>{
    private TextView tvWeather;
    @InjectPresenter
    private WeatherPresenter weatherPresenter;
    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    protected void initData() {
        //请求数据
        weatherPresenter.doPresenter();
    }

    @Override
    protected void initView() {
        tvWeather = findViewById(R.id.tv_weather);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public String getParmas() {
        return "query?city=深圳&key=b70622d1ff2effbb1846e23503f79f49";
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(WeatherInfo weatherInfo) {
        WeatherInfo.CurrentWeather currentWeather=weatherInfo.realtime;
        tvWeather.setText(weatherInfo.city + "--" +
                currentWeather.aqi + "--" +
                currentWeather.direct + "--" +
                currentWeather.humidity + "--" +
                currentWeather.info + "--" +
                currentWeather.power + "--" +
                currentWeather.temperature + "--" +
                currentWeather.wid);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
