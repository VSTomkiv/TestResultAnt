package ru.tvs.app;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import ru.tvs.app.net.RestApi;

/**
 * Created by vladimir on 17.01.18.
 */

public class Injector {

    public static final String TAG = Injector.class.getName();
    private static final String API_BASE_URL = "http://phisix-api3.appspot.com";
    private static Context appContext;
    private static RestApi restApi;

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static RestApi getRestApi() {
        if (restApi == null) {
            RequestInterceptor requestInterceptor = request -> {
                request.addHeader("Accept-Language", "ru,en;q=0.8");
                request.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.76 Safari/537.36");
                request.addHeader("Connection", "keep-alive");
            };
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(5, TimeUnit.SECONDS); // connect timeout
            client.setReadTimeout(15, TimeUnit.SECONDS);    // socket timeout

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setLog(msg -> Log.d(TAG + "::Retrofit", msg))
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(new OkClient(client))
                    .build();

           restApi = restAdapter.create(RestApi.class);

        }
        return restApi;
    }
}
