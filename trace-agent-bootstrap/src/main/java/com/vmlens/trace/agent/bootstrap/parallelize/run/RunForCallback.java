package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public interface RunForCallback {

    void after(AfterContext afterContext);

    void newTask(NewTaskContext newTaskContext);

    void threadStarted(ThreadWrapper newWrapper);

    void threadStartedByPool(ThreadStartedByPoolContext context);

    void threadJoinedByPool(ThreadJoinedAction threadJoinedAction);

}
