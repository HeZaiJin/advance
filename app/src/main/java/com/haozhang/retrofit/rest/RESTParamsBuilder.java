package com.haozhang.retrofit.rest;

import com.haozhang.retrofit.config.Configs;
import com.haozhang.retrofit.rest.modle.RequestListEventParams;

/**
 * ParamsBuilder
 */
public class RESTParamsBuilder {

    private static final String TAG = "RESTParamsBuilder" ;

    public static RequestListEventParams buildRequestListEventParams(int month,int day){
        return new RequestListEventParams(Configs.API_KEY,Configs.API_VERSION,month,day);
    }
}
