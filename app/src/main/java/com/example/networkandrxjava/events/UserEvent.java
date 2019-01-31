package com.example.networkandrxjava.events;

import com.example.networkandrxjava.model.User.UserResponse;

public class UserEvent {
    private UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public UserEvent(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}

