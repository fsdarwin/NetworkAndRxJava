package com.example.networkandrxjava.model.datasource.retrofit;

import android.util.Log;

import com.example.networkandrxjava.model.User.UserResponse;
import com.example.networkandrxjava.model.datasource.OkHttp.OkHttpHelper;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.networkandrxjava.model.Constants.ACTUAL_BASE_URL;
import static com.example.networkandrxjava.model.Constants.PATH;
import static com.example.networkandrxjava.model.Constants.QUERY_RESULTS;

public class RetrofitHelper {
    public static final String TAG = "FRANK: ";

    public static Retrofit createRetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ACTUAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpHelper.okHttpWithIntercepterClient())
                .build();
        return retrofit;
    }

    public static Retrofit createRetrofitForRx (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ACTUAL_BASE_URL)
                .client(OkHttpHelper.okHttpWithIntercepterClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<UserResponse> getUsers(){
        Retrofit retrofit = createRetrofitInstance();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getUserList("10");
    }

    public static Observable<UserResponse> getUserOb(){
        Retrofit retrofit = createRetrofitForRx();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getUserObservable("10");
    }

    public interface RemoteService{
        @GET("api/")
        Call<UserResponse> getUserList(@Query(QUERY_RESULTS) String resultCount);

        @GET(PATH)
        Observable<UserResponse> getUserObservable(@Query(QUERY_RESULTS) String resultCount);
    }
}
