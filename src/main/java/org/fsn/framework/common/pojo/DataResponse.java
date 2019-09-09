package org.fsn.framework.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends BaseResponse {
    private T data ;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
