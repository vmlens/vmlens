package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

/**
 * either thread.start, monitor exit or wait events
 *
 */

public interface ExecuteBeforeEvent extends InterleaveActionFactory {

   void addToBuilder(NextStateBuilder nextStateBuilder);

}
