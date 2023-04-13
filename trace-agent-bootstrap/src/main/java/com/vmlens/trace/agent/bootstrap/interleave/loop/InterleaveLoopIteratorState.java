package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

import java.util.Iterator;

public interface InterleaveLoopIteratorState extends Iterator<CalculatedRun>  {
    InterleaveLoopIteratorState nextState();

}
