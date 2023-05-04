package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface ResultTestBuilder {
    void add(InterleaveAction interleaveAction, Position position);

    void startThread(int index, Position temp);

    void joinThread(int index, Position temp);
}
