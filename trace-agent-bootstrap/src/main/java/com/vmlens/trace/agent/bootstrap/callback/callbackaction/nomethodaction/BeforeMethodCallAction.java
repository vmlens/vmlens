package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetInMethodIdAndPositionAtThreadLocal;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;

public class BeforeMethodCallAction extends NoMethodAction {

    private final int inMethodId;
    private final int position;

    public BeforeMethodCallAction(int inMethodId, int position) {
        this.inMethodId = inMethodId;
        this.position = position;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process(new SetInMethodIdAndPositionAtThreadLocal(
                new InMethodIdAndPosition(inMethodId, position)));
    }
}
