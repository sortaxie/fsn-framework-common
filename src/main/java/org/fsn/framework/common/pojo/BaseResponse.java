package org.fsn.framework.common.pojo;

import org.fsn.framework.common.exception.error.IError;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

/**
 * @author benju.xie
 * @version 0.0.1
 * @datetime 2017/5/10 17:44
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private String errorCode;
    private String errorMessage;
    private String extMessage;
    private BaseResponse.Status status;
    private Map<String,Object> result;

    public BaseResponse.Status getStatus() {
        return this.status;
    }

    public void setStatus(BaseResponse.Status status) {
        this.status = status;
    }

    public BaseResponse() {
        this.status = BaseResponse.Status.SUCCEED;
    }

    public BaseResponse(IError error) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
        this.status = BaseResponse.Status.FAILED;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }


    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this.errorCode != null) {
            sb.append("ErrorCode : ").append(this.errorCode).append("ErrorMessage : ").append(this.errorMessage).append("ExtMessage : " + this.extMessage);
        } else {
            sb.append("SUCCEED");
        }

        return sb.toString();
    }


    public static enum Status {
        SUCCEED,
        FAILED;

        private Status() {
        }
    }
}
