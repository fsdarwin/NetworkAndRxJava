package com.example.networkandrxjava.RxJava;

import com.example.networkandrxjava.model.User.UserResponse;

public interface Callback {
    void onSuccess(UserResponse userResponse);
}
