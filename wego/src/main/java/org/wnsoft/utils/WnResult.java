/**
 * Created by wunan on 16-9-26.
 */
package org.wnsoft.utils;

import java.io.Serializable;

public class WnResult implements Serializable {
    public static final WnResult SUCCESS = new WnResult(0);
    private int errorCode = 0;
    private String errorMsg;
    private Object object;

    public WnResult(Object object) {
        this.object = object;
    }

    public WnResult(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WnResult(int errorCode, String errorMsg, Object object) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.object = object;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
