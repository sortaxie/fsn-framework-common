package org.fsn.framework.common.exception.error;

public class MessageError implements IError {

    private static final String ns = "base.error";
    String errorCode;
    String errorMessage;


    public MessageError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public MessageError(String errorMessage){
        this.errorCode="0000";
        this.errorMessage = errorMessage;
    }

    public String getNamespace() {
        return ns;
    }

    public String getErrorCode() {
        return ns + "." + errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
