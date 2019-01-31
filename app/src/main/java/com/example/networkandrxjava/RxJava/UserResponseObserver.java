package com.example.networkandrxjava.RxJava;

import android.util.Log;

import com.example.networkandrxjava.model.User.UserResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserResponseObserver implements Observer<UserResponse> {

    Callback callback;
    public static final String TAG = "FRANK: ";
    UserResponse userResponse = new UserResponse();

    public UserResponseObserver(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: SUBSCRIBING");
    }

    @Override
    public void onNext(UserResponse userResponse) {
        this.userResponse = userResponse;
        Log.d(TAG, "onNext: "
                + userResponse.getResults().get(0).getEmail());
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        callback.onSuccess(userResponse);
    }
}
