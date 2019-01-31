package com.example.networkandrxjava.RxJava;

import com.example.networkandrxjava.model.datasource.retrofit.RetrofitHelper;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatasourceRepo {
    RetrofitHelper retrofitHelper = new RetrofitHelper();

    public void getUserResponse(final Callback callback) {
        retrofitHelper.getUserOb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserResponseObserver(callback));
    }
}
