package com.example.apipeliculas.utils;

import androidx.annotation.NonNull;

public class MovieFunctions {

    @NonNull
    public static String ordenarFecha(String fecha){
        String sCadena=fecha;
        String sSubCadena = sCadena.substring(0, 4);
        String sSubCadena2 = sCadena.substring(5, 7);
        String sSubCadena3 = sCadena.substring(8);
        String sTotal = ""+sSubCadena3+"-"+sSubCadena2+"-"+sSubCadena;
        return sTotal;
    }
}
