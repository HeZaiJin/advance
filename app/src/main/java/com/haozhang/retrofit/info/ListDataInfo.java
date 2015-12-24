package com.haozhang.retrofit.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2015/12/18.
 */
public class ListDataInfo {
    @SerializedName("error_code")
    @Expose
    private Integer error_code;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("result")
    @Expose
    private DataInfo[] result;

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(DataInfo[] result) {
        this.result = result;
    }

    public Integer getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public DataInfo[] getResult() {
        return result;
    }
}
