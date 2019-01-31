package com.example.networkandrxjava.model.datasource.OkHttp;

import android.content.Context;
import android.util.Log;

import com.example.networkandrxjava.model.User.UserResponse;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.http.Url;

public class OkHttpHelper {
    public static final String TAG = "FRANK: ";
//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    };

    public static void asyncOkHttpApiCall(String url, Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponce;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResponce = response.body().string();
                Log.d(TAG, "onResponse: " + jsonResponce);
            }
        });
    }

    public static void syncOkHttpApiCall(String url, final Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d(TAG, "syncOkHttpApiCall: In Sync API");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: In run of API");
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String jsonResponse = response.body().string();
                    Log.d(TAG, "run: " + jsonResponse);
//                    intentFilter.addAction("pass_info");
//                    context.registerReceiver(new MainActivity.Receiver(), intentFilter);
//                    Intent intent = new Intent();
//                    intent.putExtra("json", jsonResponse);
//                    context.sendBroadcast(intent);
                    Gson gson = new Gson();
                    UserResponse userResponse = gson.fromJson(jsonResponse, UserResponse.class);
                    //Intent intent = new Intent();
                    //intent.setAction("User");
                    //intent.putExtra("user", userResponse);
                    Log.d(TAG, "run: Before broadcast");
                    // LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Log.d(TAG, "run: After broadcast");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    //BUILD okhttp client with interceptor
    public static OkHttpClient okHttpWithIntercepterClient() {
        HttpLoggingInterceptor httpLoggingInterceptor
                = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    public static void asyncWithIntercepter(String url){
        OkHttpClient okHttpClient = okHttpWithIntercepterClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}
