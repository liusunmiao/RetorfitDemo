package com.lsm.retorfitdemo.net;

public class ErrorHandle {
    public static class ServiceError extends Throwable{
        int errorCode;
        public ServiceError(int errorCode, String errorMsg) {
            super(errorMsg);
            this.errorCode = errorCode;
        }
    }
}
