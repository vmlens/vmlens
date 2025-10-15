package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.ThreadJoinByThreadPool;

public class ThreadPoolJoinAction  extends NoMethodAction {

    private final Object taskOrPool;


    public ThreadPoolJoinAction(Object taskOrPool) {
        this.taskOrPool = taskOrPool;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process(new ThreadJoinByThreadPool(taskOrPool));
    }
}
