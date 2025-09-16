package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.ThreadJoinByThreadPool;

public class ThreadPoolJoinAction implements CallbackAction  {

    private final Object taskOrPool;


    public ThreadPoolJoinAction(Object taskOrPool) {
        this.taskOrPool = taskOrPool;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process(new ThreadJoinByThreadPool(taskOrPool));
    }
}
