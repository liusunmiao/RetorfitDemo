package com.lsm.retorfitdemo.net;

/**
 * 天气数据模型
 */
public class WeatherInfo {
    public String city;
    public CurrentWeather realtime;

    public class CurrentWeather {
        public String temperature;
        public String humidity;
        public String info;
        public String wid;
        public String direct;
        public String power;
        public String aqi;
    }
}
