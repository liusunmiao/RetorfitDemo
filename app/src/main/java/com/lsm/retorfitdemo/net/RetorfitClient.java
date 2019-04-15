package com.lsm.retorfitdemo.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Emitter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * retorfit请求工具类
 */
public class RetorfitClient {
    private final static ServiceApi serviceApi;
    private static String TAG = "RetorfitClient";
    static {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //添加log日志拦截器
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e(TAG, message);
                    }
                })
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                //网络请求主路径
                .baseUrl("http://apis.juhe.cn/simpleWeather/")
                //添加Gson数据解析
                .addConverterFactory(GsonConverterFactory.create())
                //添加支持rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        serviceApi = retrofit.create(ServiceApi.class);
    }

    public static ServiceApi getServiceApi() {
        return serviceApi;
    }

    public static <T> Observable.Transformer<Result<T>, T> transformer() {
        return new Observable.Transformer<Result<T>, T>() {
            @Override
            public Observable<T> call(Observable<Result<T>> resultObservable) {
                return resultObservable.flatMap(new Func1<Result<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Result<T> tResult) {
                        if (tResult.isSuccess()) {
                            //成功
                            // 返回成功
                            return createObservable(tResult.result);
                        } else {
                            //失败
                            return Observable.error(new ErrorHandle.ServiceError(tResult.error_code, tResult.reason));
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    private static <T> Observable<T> createObservable(final T backData) {
        return Observable.create(new Action1<Emitter<T>>() {
            @Override
            public void call(Emitter<T> tEmitter) {
                tEmitter.onNext(backData);
                tEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.NONE);
    }
}
