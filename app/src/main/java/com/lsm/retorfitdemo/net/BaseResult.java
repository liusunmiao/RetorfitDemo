package com.lsm.retorfitdemo.net;

/**
 * 返回数据模型基类 主要定义返回code msg
 * 后面的数据模型类继承自该类
 */
public class BaseResult {
    public String reason;
    public int error_code = -1;

    public boolean isSuccess() {
        return 0 == error_code;
    }
}
