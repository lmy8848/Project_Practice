package com.example.usertest.entity;

/**
 * @author NJQ-PC
 */
public class Response {
    private Integer code;
    private String message;
    private String token;

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Response(Integer code, String message, String token) {
        this.code = code;
        this.message = message;
        this.token = token;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
