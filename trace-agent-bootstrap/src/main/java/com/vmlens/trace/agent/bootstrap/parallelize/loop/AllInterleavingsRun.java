package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;

public interface AllInterleavingsRun {
    void close();

    void after(Command command, long threadId);

    boolean advance();
}
