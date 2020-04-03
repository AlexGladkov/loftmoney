package com.loftblog.loftmoney.internet.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    public static String AUTH_TOKEN_KEY = "Access-Token";

    private String status;
    private String id;
    private @SerializedName("auth_token") String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
