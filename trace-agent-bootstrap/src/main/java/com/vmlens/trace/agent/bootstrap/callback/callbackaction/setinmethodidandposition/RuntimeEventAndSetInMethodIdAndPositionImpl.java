package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdAndPosition;

public class RuntimeEventAndSetInMethodIdAndPositionImpl<EVENT extends RuntimeEvent & WithInMethodIdAndPosition>
        implements RuntimeEventAndSetInMethodIdAndPosition {

    private final EVENT event;

    public RuntimeEventAndSetInMethodIdAndPositionImpl(EVENT event) {
        this.event = event;
    }

    @Override
    public RuntimeEvent setInMethodIdAndPosition(int inMethodId, int position) {
        event.setInMethodIdAndPosition(inMethodId, position);
        return event;
    }
}
