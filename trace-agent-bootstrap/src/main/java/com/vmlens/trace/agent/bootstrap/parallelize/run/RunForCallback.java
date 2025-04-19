package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunForCallback {

    void after(AfterContext afterContext);

    void newTask(NewTaskContext newTaskContext);

    void newTestTaskStarted(RunnableOrThreadWrapper newWrapper);

}
