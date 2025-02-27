package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

/**
 * All atomic events must implement this interface
 */

public interface WithInMethodIdAndPosition {

    void setInMethodIdAndPosition(int inMethodId, int position);

}
