package ru.tvs.app.net;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by vladimir on 17.01.18.
 */

public interface  RestApi {
    @GET("/stocks.json")
    void findResponseData(
            Callback<ResponseData> response);
}
