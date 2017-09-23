package com.testmeuapt.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.util.SharedPreferenceUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jsantini on 22/09/17.
 */

public class DribbbleApi {

    private static DribbbleApi.DribbbleApiContract dribbbleApi;
    private static String URL_BASE = "https://api.dribbble.com/v1/";

    public static DribbbleApi.DribbbleApiContract getApi(final Context context) {
        final String accessToken = SharedPreferenceUtil.getAccessToken(context);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.header("Accept", "application/json");
                requestBuilder.header("Authorization", "Bearer " + accessToken);
                requestBuilder.method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

        OkHttpClient okClient = builder.build();
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm:ss").create();
        Retrofit client = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        dribbbleApi = client.create(DribbbleApiContract.class);
        return dribbbleApi;
    }

    public interface DribbbleApiContract {

        @GET("shots")
        Call<List<Shot>> getShots(@Query("per_page") int limit);

        @GET("shots/{id}")
        Call<Shot> getShotData(@Path("id") long id);
    }
}
