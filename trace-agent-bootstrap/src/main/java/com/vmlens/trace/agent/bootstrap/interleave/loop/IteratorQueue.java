package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;

import java.util.Iterator;

public interface IteratorQueue {
    Iterator<CalculatedRun> poll();
}
