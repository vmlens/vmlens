package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

/**
 * either thread.start, monitor exit or wait events
 *
 */

public interface ExecuteBeforeEvent extends InterleaveActionFactoryAndRuntimeEvent {

   void addToBuilder(NextStateBuilder nextStateBuilder);

}
