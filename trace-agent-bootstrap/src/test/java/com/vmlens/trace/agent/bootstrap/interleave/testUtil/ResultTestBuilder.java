package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface ResultTestBuilder {
    void volatileAccess(final int fieldId, final int operation, Position position);

    void startThread(int index, Position temp);

    void joinThread(int index, Position temp);
}
