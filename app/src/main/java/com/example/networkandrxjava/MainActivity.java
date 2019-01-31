package com.example.networkandrxjava;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.networkandrxjava.RxJava.Callback;
import com.example.networkandrxjava.RxJava.DatasourceRepo;
import com.example.networkandrxjava.events.UserEvent;
import com.example.networkandrxjava.model.User.UserResponse;
import com.example.networkandrxjava.model.datasource.OkHttp.OkHttpHelper;
import com.example.networkandrxjava.model.datasource.httpUrlConnection.HttpUrlConnTask;
import com.example.networkandrxjava.model.datasource.retrofit.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.networkandrxjava.model.Constants.ACTUAL_BASE_URL;
import static com.example.networkandrxjava.model.Constants.FULL_EXAMPLE_URL;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "FRANK: RX: ";
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OkHttpHelper.asyncWithIntercepter(FULL_EXAMPLE_URL);
//        Call<UserResponse> responseCall = RetrofitHelper.getUsers();
//
//        responseCall.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                String urlUsed = call.request().url().toString();
//                String email = response.body().getResults().get(0).getEmail();
//                Log.d(TAG, "onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//
//            }
//        });
//
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("User");
//
//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d(TAG, "onReceive: In onReceive");
//                UserResponse userResponse = intent.getParcelableExtra("user");
//                Log.d(TAG, "onReceive: " + userResponse.getResults().get(0).getEmail());
//            }
//        };
//        registerReceiver(broadcastReceiver, filter);
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Call<UserResponse> responseCall = RetrofitHelper.getUsers();
//                Response<UserResponse> userResponseResponse = null;
//                try {
//                    userResponseResponse = responseCall.execute();
//                    String userResponceEmail = userResponseResponse.body().getResults().get(1).getEmail();
//                    Log.d(TAG, "run: " + userResponceEmail);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//    }
        DatasourceRepo datasourceRepo = new DatasourceRepo();
        datasourceRepo.getUserResponse(new Callback() {
            @Override
            public void onSuccess(UserResponse userResponse) {
                Log.d(TAG, "onSuccess: " + userResponse.getResults().get(0).getEmail());
            }
        });
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void userEvent(UserEvent event) {
//        if (event != null) {
//            UserResponse userResponse = event.getUserResponse();
//            Log.d("TAG", "userEvent: " + userResponse.getResults().get(0).getEmail());
//            //tvDisplay.setText(userResponse.getResults().get(0).getEmail());
//
//        }
//    }
//
//    public static class Receiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String passedJson = intent.getStringExtra("json");
//            Log.d(TAG, passedJson);
//        }
//    }
    }
}
