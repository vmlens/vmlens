package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.BlockBlockTuple;
import gnu.trove.map.hash.THashMap;

public interface DeadlockOperation  {

    void addToMap(THashMap<Position,DeadlockOperation> map);
    boolean isInDeadlock(BlockBlockTuple potential);
    TreeBuilderNode addToAlternatingOrder(TreeBuilderNode node);

}
