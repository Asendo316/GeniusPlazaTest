package com.cordiscorp.geniustest.connectvity;


import com.cordiscorp.geniustest.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ibkunle Adeoluwa on 8/19/2019.
 */
public class GPTestClient {
    public static final String BASE_URL = BuildConfig.API_ENDPOINT;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
