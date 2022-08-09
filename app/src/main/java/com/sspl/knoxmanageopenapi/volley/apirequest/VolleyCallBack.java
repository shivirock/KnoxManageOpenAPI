package com.sspl.knoxmanageopenapi.volley.apirequest;

import com.sspl.knoxmanageopenapi.volley.apirequest.models.DeviceDetails;
import com.sspl.knoxmanageopenapi.volley.apirequest.models.OAuthTokenResponse;

public interface VolleyCallBack {
    void onSuccess(String result);
    void onSuccess(OAuthTokenResponse result);
    void onSuccess(DeviceDetails result);
}
