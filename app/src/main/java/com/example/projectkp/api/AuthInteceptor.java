package com.example.projectkp.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInteceptor implements Interceptor {
    private String authToken;

    public AuthInteceptor(String token){
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + authToken);

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
