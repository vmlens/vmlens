package com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public interface FilterActionsInsideMethodStrategy {

    boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
