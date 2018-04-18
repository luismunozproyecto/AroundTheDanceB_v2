package com.example.paxi.aroundthedanceb;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;


public class MySingleton
{
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context)
    {
        this.context = context;
        requestQueue = getReferenceQueue();
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        if(mySingleton == null)
        {
            mySingleton = new MySingleton(context);
        }

        return  mySingleton;
    }

    public RequestQueue getReferenceQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T>void addTorequestque(Request<T> request)
    {
        requestQueue.add(request);
    }
}
