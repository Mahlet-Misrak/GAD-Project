package com.example.project.Services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
//private static Retrofit retrofit = null;
//public static Retrofit getClient(String baseURL) {
//    if (retrofit == null) {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//    return retrofit;
//}
    private static final String URL = "https://gadsapi.herokuapp.com/";
     //create logger
    public static HttpLoggingInterceptor logger =
           new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    // Create okHttp Client
    public static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .addInterceptor(logger);
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());
    private static Retrofit retrofit = builder.build();
    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
