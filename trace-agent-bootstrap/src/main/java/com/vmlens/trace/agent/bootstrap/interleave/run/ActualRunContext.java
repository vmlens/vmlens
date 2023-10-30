package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;

public interface ActualRunContext {

    SendEventContext sendEventContext();

    void setRunIdsInRuntimeEvent(RuntimeEvent event);


}
