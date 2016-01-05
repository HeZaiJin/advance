package com.haozhang.retrofit.reset;

import com.haozhang.retrofit.config.Configs;
import com.haozhang.retrofit.reset.modle.RequestListEventParams;
import com.haozhang.retrofit.reset.modle.ResponseListEvent;
import com.haozhang.retrofit.reset.service.ApiServices;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * 网络请求
 */
public class RESETClient {

    private static final String TAG = "RESETClient";

    private static final String DEFAULT_URL= Configs.API;

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DEFAULT_URL)
            .build();

    private static final ApiServices sHttpService = sRetrofit.create(ApiServices.class);

    /**
     * 获取listevent
     * * @param params
     * @param callback
     */
    public static void qureyListEvent(RequestListEventParams params,Callback<ResponseListEvent> callback){
        Call<ResponseListEvent> listEventCall = sHttpService.loadListInofs(params.v,params.key,params.month,params.day);
        listEventCall.enqueue(callback);
    }

}
