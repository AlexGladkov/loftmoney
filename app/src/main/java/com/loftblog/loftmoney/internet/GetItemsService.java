package com.loftblog.loftmoney.internet;

import com.loftblog.loftmoney.internet.models.GetItemsResponse;
import com.loftblog.loftmoney.internet.models.ItemRemote;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemsService {
    @GET("./items")
    Single<List<ItemRemote>> request(@Query("type") String type, @Query("auth-token") String authToken);
}
