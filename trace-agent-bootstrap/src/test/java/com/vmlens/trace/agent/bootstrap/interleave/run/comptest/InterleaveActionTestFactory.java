package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;


import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface InterleaveActionTestFactory {
    InterleaveAction create(int threadIndexd);
}
