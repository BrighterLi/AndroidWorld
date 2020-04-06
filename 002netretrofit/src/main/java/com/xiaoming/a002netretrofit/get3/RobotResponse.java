package com.xiaoming.a002netretrofit.get3;

public class RobotResponse {
    private String reason;
    private String error_code;
    private ResultResponse result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public ResultResponse getResult() {
        return result;
    }

    public void setResult(ResultResponse result) {
        this.result = result;
    }
}
