package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface TestBuilderResult {
    void add(InterleaveAction interleaveAction, Position position);

    void startThread(int index, Position temp);
}
