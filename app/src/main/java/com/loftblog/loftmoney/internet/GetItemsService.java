package com.loftblog.loftmoney.internet;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemsService {
    @GET("./items")
    Single<GetItemsResponse> request(@Query("type") String type);
}
