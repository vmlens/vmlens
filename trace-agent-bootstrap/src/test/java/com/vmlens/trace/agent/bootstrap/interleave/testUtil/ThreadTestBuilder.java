package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.testfixture.ResultTestBuilder;

public class ThreadTestBuilder {
    private final ResultTestBuilder resultTestBuilder;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ResultTestBuilder resultTestBuilder, int threadIndex) {
        this.resultTestBuilder = resultTestBuilder;
        this.threadIndex = threadIndex;
    }

    public Position volatileAccess(int fieldId, int operation) {
        Position temp =  new Position(threadIndex,position);
        resultTestBuilder.volatileAccess(fieldId, operation, temp);
        position++;
        return temp;
    }
    public Position startThread(int index) {
        Position temp =  new Position(threadIndex,position);
        resultTestBuilder.startThread(index,temp);
        position++;
        return temp;
    }

    public Position joinThread(int index) {
        Position temp = new Position(threadIndex, position);
        resultTestBuilder.joinThread(index, temp);
        position++;
        return temp;
    }

    public Position monitorEnter(int id) {
        Position temp = new Position(threadIndex, position);
        resultTestBuilder.monitorEnter(id, temp);
        position++;
        return temp;
    }

    public Position monitorExit(int id) {
        Position temp = new Position(threadIndex, position);
        resultTestBuilder.monitorExit(id, temp);
        position++;
        return temp;
    }
}
