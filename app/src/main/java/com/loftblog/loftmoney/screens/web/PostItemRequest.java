package com.loftblog.loftmoney.screens.web;

import com.loftblog.loftmoney.screens.web.models.ItemRemote;

import io.reactivex.Completable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface PostItemRequest {

    @POST("./items/add")
    @FormUrlEncoded
    Completable request(@Field("price") Integer price, @Field("name") String name,
                        @Field("type") String type, @Field("auth-token") String authToken);
}
