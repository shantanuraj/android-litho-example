package io.podcst.android.data;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by eve on 19/04/17.
 */

public class Api {

    public static ApiService get() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(PodcstsResponse.class, new PodcstsDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiService.class);
    }

    private static class PodcstsDeserializer implements JsonDeserializer<PodcstsResponse> {
        @Override
        public PodcstsResponse deserialize(JsonElement json,
                                           Type typeOfT,
                                           JsonDeserializationContext context) {
            return new Gson().fromJson(json, PodcstsResponse.class);
        }
    }

    public interface ApiService {

        @GET(Constants.FEATURED)
        Observable<PodcstsResponse> getFeaturedPodcasts();

        @GET(Constants.POPULAR)
        Observable<PodcstsResponse> getPopularPodcasts();

        @GET(Constants.TRENDING)
        Observable<PodcstsResponse> getTrendingPodcasts();
    }
}