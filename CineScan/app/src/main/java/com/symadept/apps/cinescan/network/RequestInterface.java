package com.symadept.apps.cinescan.network;

import com.symadept.apps.cinescan.models.CatalogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shaimu8 on 5/20/18.
 */

public interface RequestInterface {
//    @GET("3/discover/movie?api_key=API_KEY&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false&page=1")
    @GET("/3/movie/popular?api_key=API_KEY&language=en-US&page=1")
    Call<CatalogResponse> getMostPopularMovies();

    @GET("/3/movie/top_rated?api_key=API_KEY&language=en-US&page=1")
    Call<CatalogResponse> getTopRatedMovies();
}
