package com.dos.church;

public class HttpSuccessResponse<T> extends HttpResult<T> {
    public HttpSuccessResponse(T data, String message) {
        super(data, Constante.HTTP_SUCCESS, message);
    }
}
