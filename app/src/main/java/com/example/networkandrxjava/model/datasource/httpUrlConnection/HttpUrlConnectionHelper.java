package com.example.networkandrxjava.model.datasource.httpUrlConnection;

import android.util.Log;

import com.example.networkandrxjava.events.UserEvent;
import com.example.networkandrxjava.model.User.UserResponse;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.networkandrxjava.model.Constants.ACTUAL_BASE_URL;

public class HttpUrlConnectionHelper {

    public static void makeAPICallWithHttpConn() {

        HttpURLConnection httpURLConnection = null;
        URL apiURL;
        String jsonResponse = "";
        InputStream inputStream = null;

        try {
            apiURL = new URL(ACTUAL_BASE_URL);
            httpURLConnection = (HttpURLConnection) apiURL.openConnection();
            if (httpURLConnection != null) {
                inputStream = httpURLConnection.getInputStream();
            }else{
                System.out.println("httpURLConnection is null");
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int currentReadCharAsciiValue = inputStream.read();
            while (currentReadCharAsciiValue != -1) {
                char currentChar = (char) currentReadCharAsciiValue;
                currentReadCharAsciiValue = inputStreamReader.read();
                jsonResponse = jsonResponse + currentChar;
            }
            System.out.println(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                Gson gson = new Gson();
                UserResponse userResponse = gson.fromJson(jsonResponse, UserResponse.class);
                EventBus.getDefault().post(new UserEvent(userResponse));
                //Log.d("TAG", "makeAPICallWithHttpConn: " + userResponse.getResults().get(0).getEmail());
            }
        }
    }
}
