package com.haozhang.advance.rest;

import com.haozhang.advance.config.Configs;
import com.haozhang.advance.rest.modle.RequestListEventParams;

/**
 * ParamsBuilder
 */
public class RESTParamsBuilder {

    private static final String TAG = "RESTParamsBuilder" ;

    public static RequestListEventParams buildRequestListEventParams(int month,int day){
        return new RequestListEventParams(Configs.API_KEY,Configs.API_VERSION,month,day);
    }
}
