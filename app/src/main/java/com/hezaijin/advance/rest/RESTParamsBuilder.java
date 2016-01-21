package com.hezaijin.advance.rest;

import com.hezaijin.advance.config.Configs;
import com.hezaijin.advance.rest.modle.RequestListEventParams;

/**
 * ParamsBuilder
 */
public class RESTParamsBuilder {

    private static final String TAG = "RESTParamsBuilder" ;

    public static RequestListEventParams buildRequestListEventParams(int month,int day){
        return new RequestListEventParams(Configs.API_KEY,Configs.API_VERSION,month,day);
    }
}
