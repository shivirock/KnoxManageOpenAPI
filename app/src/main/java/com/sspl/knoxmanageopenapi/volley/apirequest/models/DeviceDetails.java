package com.sspl.knoxmanageopenapi.volley.apirequest.models;

public class DeviceDetails {
    public ResultValue resultValue;
    public String resultCode;
    public String resultMessage;

    public ResultValue getResultValue() {
        return resultValue;
    }

    public void setResultValue(ResultValue resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return "DeviceDetails{" +
                "resultValue=" + resultValue +
                ", resultCode='" + resultCode + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                '}';
    }
}