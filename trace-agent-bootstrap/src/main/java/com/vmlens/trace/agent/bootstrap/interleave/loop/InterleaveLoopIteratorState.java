package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;

import java.util.Iterator;

public interface InterleaveLoopIteratorState extends Iterator<InterleaveRun>  {
    InterleaveLoopIteratorState nextState();

}
