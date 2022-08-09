package com.sspl.knoxmanageopenapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sspl.knoxmanageopenapi.volley.apirequest.APIImplementation;
import com.sspl.knoxmanageopenapi.volley.apirequest.APIList;
import com.sspl.knoxmanageopenapi.volley.apirequest.VolleyCallBack;
import com.sspl.knoxmanageopenapi.volley.apirequest.models.DeviceDetails;
import com.sspl.knoxmanageopenapi.volley.apirequest.models.OAuthTokenResponse;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private APIImplementation apiImplementation;
    private String clientID, clientPassword, googleServiceFrameworkID, accessToken;

    private TextView imei_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientID = "CLIENT_ID";
        clientPassword = "CLIENT_PASSWORD";
        imei_ = findViewById(R.id.imei);

        apiImplementation = new APIImplementation();
        String url = APIList.BASEURL_AP01 + APIList.OAUTH_API + "?grant_type=client_credentials&client_id=" + clientID + "&client_secret=" + clientPassword;
        apiImplementation.getOAuthToken(getApplicationContext(), url, new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, result);
            }

            @Override
            public void onSuccess(OAuthTokenResponse result) {
                if (result.getAccess_token() != null) {
                    accessToken = result.getAccess_token();
                    Log.d(TAG, accessToken);
                    getGoogleServiceFrameworkID();
                } else
                    Log.d(TAG, result.getError_description());
                imei_.setText(result.getError_description());
            }

            @Override
            public void onSuccess(DeviceDetails result) {

            }
        });
    }

    public void getGoogleServiceFrameworkID(){
        apiImplementation.getGsfAndroidId(getApplicationContext(), new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                googleServiceFrameworkID = result;
                Log.d(TAG,googleServiceFrameworkID);
                getDeviceDetails();
            }

            @Override
            public void onSuccess(OAuthTokenResponse result) {}

            @Override
            public void onSuccess(DeviceDetails result) {}
        });
    }

    public void getDeviceDetails(){
        apiImplementation = new APIImplementation();
        String url = APIList.BASEURL_AP01 + APIList.DEVICE_DETAILS_BY_GOOGLE_ID;
        apiImplementation.getDeviceDetailsByGoogleId(getApplicationContext(), url, googleServiceFrameworkID, accessToken, new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {}

            @Override
            public void onSuccess(OAuthTokenResponse result) {}

            @Override
            public void onSuccess(DeviceDetails result) {
                if(result.getResultValue() == null){
                    Log.d(TAG, "No device found in tenant");
                    imei_.setText("No device found in tenant");
                } else {
                    String imei = result.getResultValue().getImei();
                    Log.d(TAG, imei);
                    imei_.setText(imei);
                }
            }
        });
    }
}