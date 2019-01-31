package com.example.networkandrxjava.model.datasource.httpUrlConnection;

import android.os.AsyncTask;

public class HttpUrlConnTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        HttpUrlConnectionHelper.makeAPICallWithHttpConn();
        return null;
    }
}
