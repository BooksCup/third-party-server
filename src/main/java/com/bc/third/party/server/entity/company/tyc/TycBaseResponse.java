package com.bc.third.party.server.entity.company.tyc;


/**
 * 返回值(天眼查)
 *
 * @param <T> 泛型
 * @author zhou
 */
public class TycBaseResponse<T> {

    private String error_code;
    private String reason;
    private T result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}


