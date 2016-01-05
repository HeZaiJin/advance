package com.haozhang.retrofit.reset;

import com.haozhang.retrofit.config.Configs;
import com.haozhang.retrofit.reset.modle.RequestListEventParams;

/**
 * Created by Administrator on 2016/1/5.
 */
public class RESETParamsBuilder {

    private static final String TAG = "RESETParamsBuilder" ;

    public static RequestListEventParams buildRequestListEventParams(int month,int day){
        return new RequestListEventParams(Configs.API_KEY,Configs.API_VERSION,month,day);
    }
}
