package com.loftblog.loftmoney.internet;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.internal.operators.completable.CompletableAmb;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class WebFactory {

    private Retrofit retrofit;

    private WebFactory() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://loftschool.com/android-api/basic/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static WebFactory instance = null;

    public static WebFactory getInstance() {
        if (instance == null) {
            instance = new WebFactory();
        }

        return instance;
    }

    public GetItemsService getGetItemsService() { return retrofit.create(GetItemsService.class); }
    public AuthService getAuthService() { return retrofit.create(AuthService.class); }
    public AddItemService addItemService() {
        return retrofit.create(AddItemService.class);
    }

    public DeleteItemService deleteItemService() {
        return retrofit.create(DeleteItemService.class);
    }
}

