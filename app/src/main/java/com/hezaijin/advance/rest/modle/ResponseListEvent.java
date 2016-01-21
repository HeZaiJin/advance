package com.hezaijin.advance.rest.modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * ListEvent 的 response
 */
public class ResponseListEvent {

    public ResponseListEvent(Integer error_code, String reason, CustomEvent[] result) {
        this.error_code = error_code;
        this.reason = reason;
        this.result = result;
    }

    @SerializedName("error_code")
    @Expose
    private Integer error_code;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("result")
    @Expose
    private CustomEvent[] result;

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(CustomEvent[] result) {
        this.result = result;
    }

    public Integer getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public CustomEvent[] getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ResponseListEvent{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
