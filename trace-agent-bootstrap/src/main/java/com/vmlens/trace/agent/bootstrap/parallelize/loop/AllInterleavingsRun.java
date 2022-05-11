package com.vmlens.trace.agent.bootstrap.parallelize.loop;

/*
  end
  afterOperation (auch in handler)

 */

import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;

public class AllInterleavingsRun {

    private final Object MONITOR = new Object();
    private final int runId;
    private final RunStateMachine runState = new RunStateMachine();

    public AllInterleavingsRun(int runId) {
        this.runId = runId;
    }

    AllInterleavingsRun advance() {}

    void close() {}

    void after(Command command) { }

}
