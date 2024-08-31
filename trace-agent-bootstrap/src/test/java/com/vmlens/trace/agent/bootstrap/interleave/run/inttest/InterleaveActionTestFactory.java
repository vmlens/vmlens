package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;


import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface InterleaveActionTestFactory {
    InterleaveAction create(int threadIndexd);
}
