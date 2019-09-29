package com.example.vollytest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleTone {

    private static SingleTone singleTone ;
    private RequestQueue requestQueue ;

    private SingleTone (Context context){

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }


    public static synchronized SingleTone getObject (Context context){
        if(singleTone == null){

            singleTone = new SingleTone(context);
        }

        return singleTone ;
    }

    public RequestQueue getQueue (){
        return requestQueue;
    }


}
