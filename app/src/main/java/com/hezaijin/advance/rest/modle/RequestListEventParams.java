package com.hezaijin.advance.rest.modle;

/**
 * listEvent 请求params
 */
public class RequestListEventParams {
    private static final String TAG = "RequestListEventParams";

    public String key;
    public String v;
    public int month;
    public int day;

    public RequestListEventParams(String key, String v, int month, int day) {
        this.key = key;
        this.v = v;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "RequestListEventParams{" +
                "key='" + key + '\'' +
                ", v='" + v + '\'' +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

