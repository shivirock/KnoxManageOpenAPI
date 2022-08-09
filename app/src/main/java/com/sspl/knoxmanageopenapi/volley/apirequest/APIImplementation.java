package com.sspl.knoxmanageopenapi.volley.apirequest;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sspl.knoxmanageopenapi.volley.apirequest.models.DeviceDetails;
import com.sspl.knoxmanageopenapi.volley.apirequest.models.OAuthTokenResponse;

import java.util.HashMap;
import java.util.Map;

public class APIImplementation {
    public static final String TAG = "APIImplementation";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private Gson mGSON;

    public void getOAuthToken(Context mContext, String url, final VolleyCallBack callBack) {
        mRequestQueue = Volley.newRequestQueue(mContext);
        mGSON = new Gson();

        //Request a string response from the provided URL.
        mStringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG + " GetOAuthToken Function" + 1, response);
                        callBack.onSuccess(mGSON.fromJson(response, OAuthTokenResponse.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String json;
                OAuthTokenResponse mOAuthTokenResponse = new OAuthTokenResponse();
                if (error.networkResponse != null && error.networkResponse.data != null) {

                mOAuthTokenResponse.setError("invalid_client");
                mOAuthTokenResponse.setError_description("Bad client credentials");

                Log.d(TAG + " GetOAuthToken Function" + 2, mOAuthTokenResponse.toString());
                callBack.onSuccess(mOAuthTokenResponse);
            }
        }});
        mRequestQueue.add(mStringRequest);
    }

    public void getGsfAndroidId(Context context, final VolleyCallBack callBack)
    {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String params[] = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (c != null && (!c.moveToFirst() || c.getColumnCount() < 2)){
            if(!c.isClosed())
                c.close();
            callBack.onSuccess("");
        }

        try {
            if (c != null) {
                String result = Long.toHexString(Long.parseLong(c.getString(1)));
                if(!c.isClosed())
                    c.close();
                callBack.onSuccess(result);
            }else {
                callBack.onSuccess("");
            }
        } catch (NumberFormatException e) {
            if(!c.isClosed())
                c.close();
            callBack.onSuccess("");
        }
    }

    public void getDeviceDetailsByGoogleId(Context mContext, final String url, String googleDeviceID, String token, final VolleyCallBack callBack){
        mRequestQueue = Volley.newRequestQueue(mContext);
        mGSON = new Gson();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        DeviceDetails mDeviceDetails = mGSON.fromJson(response, DeviceDetails.class);
                        callBack.onSuccess(mDeviceDetails);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("googleDeviceId",googleDeviceID);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String>  header = new HashMap<String, String>();
                header.put("Authorization", "Bearer"+token);
                header.put("Content-Type", "application/x-www-form-urlencoded");
                return header;
            }
        };
        mRequestQueue.add(postRequest);
    }
}