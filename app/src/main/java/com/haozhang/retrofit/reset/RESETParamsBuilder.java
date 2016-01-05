package com.haozhang.retrofit.reset;

import com.haozhang.retrofit.config.Configs;
import com.haozhang.retrofit.reset.modle.RequestListEventParams;

/**
 * ParamsBuilder
 */
public class RESETParamsBuilder {

    private static final String TAG = "RESETParamsBuilder" ;

    public static RequestListEventParams buildRequestListEventParams(int month,int day){
        return new RequestListEventParams(Configs.API_KEY,Configs.API_VERSION,month,day);
    }
}
