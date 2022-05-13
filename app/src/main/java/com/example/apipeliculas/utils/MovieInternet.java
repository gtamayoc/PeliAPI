package com.example.apipeliculas.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import java.io.IOException;

public class MovieInternet {


    public static boolean OnLine(@NonNull Context context) {
        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;

        } else {
            return false;
        }
//
    }


}
