package com.example.apipeliculas.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MovieInternet {


    public static boolean OnLine(Context context) {
        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobile = connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mWifi.isConnected()) {
                tipoConexion1 = true;
            }
            if (mMobile.isConnected()) {
                tipoConexion2 = true;
            }

            if (tipoConexion1 || tipoConexion2) {
                if (tipoConexion1 == false) {
                    return false;
                } else {
                    return true;
                }
            }

        } else {
            return false;
        }
        return false;
    }


}
