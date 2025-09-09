package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;

public interface CallbackAction {

    void execute(InTestActionProcessor inTestActionProcessor);

}
