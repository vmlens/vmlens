package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public interface RuntimeEventAndSetInMethodIdAndPosition {

    RuntimeEvent setInMethodIdAndPosition(int inMethodId, int position);

}
