package com.haozhang.retrofit.api;

import com.haozhang.retrofit.info.GithubInfo;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Administrator on 2015/12/18.
 */
public interface ApiServices {

    @GET("https://api.github.com")
    Call<GithubInfo> loadGithubInfos();

    
}
