package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.afterMonitorExit;

public class AfterMonitorExitAction extends NoMethodAction {


    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        afterMonitorExit(inTestActionProcessor);
    }
}
