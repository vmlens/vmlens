package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

import java.util.Iterator;

public interface InterleaveLoopIteratorState extends Iterator<ActualRun>  {
    InterleaveLoopIteratorState nextState();

}
