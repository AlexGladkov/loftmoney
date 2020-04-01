package com.loftblog.loftmoney.internet;

import io.reactivex.Completable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeleteItemService {
    @GET("./items/{id}")
    Completable request(@Path("id") Integer id);
}
