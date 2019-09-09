package org.fsn.framework.common.exception.error;

/**
 * @author benju.xie
 * @version 0.0.1
 * @datetime 2017/5/10 17:53
 */
public interface IError  {
    String getNamespace();

    String getErrorCode();

    String getErrorMessage();
}
