package com.dos.church;

public class HttpErrorResponse<T> extends HttpResult<T> {
    public HttpErrorResponse(T data, String message) {
        super(data, Constante.HTTP_ERROR, message);
    }
}
