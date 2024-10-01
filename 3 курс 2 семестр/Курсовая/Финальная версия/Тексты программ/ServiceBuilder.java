package com.example.librarystar;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceBuilder {
    private static String URL = "https://lolpigg.pythonanywhere.com/v1/";
    private static Retrofit retrofit = null;
    static Retrofit buildRequest(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(new ContentTypeInterceptor()).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    private static class ContentTypeInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request modifiedRequest = originalRequest.newBuilder()
                    .removeHeader("Content-Type")
                    .addHeader("Content-Type", "application/json")
                    .build();
            return chain.proceed(modifiedRequest);
        }
    }

}
