package com.loftblog.loftmoney.internet;

import com.loftblog.loftmoney.internet.models.AuthResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthService {

    @GET("./auth")
    Single<AuthResponse> request(@Query("social_user_id") String userId);
}
