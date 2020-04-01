package com.loftblog.loftmoney.internet;

import io.reactivex.Completable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AddItemService {
    @POST("./items/add")
    @FormUrlEncoded
    public Completable request(@Field("type") String type, @Field("name") String name, @Field("price") Integer price);
}
