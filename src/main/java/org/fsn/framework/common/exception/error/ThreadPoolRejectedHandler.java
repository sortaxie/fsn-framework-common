package org.fsn.framework.common.exception.error;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolRejectedHandler implements RejectedExecutionHandler {
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new  BaseBusinessModuleException(DefaultError.TOO_MANY_THREAD_ERROR);
    }
}
