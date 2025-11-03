package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;


public class RunAfter<EVENT extends RuntimeEvent> extends AbstractInTestAction  {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;
    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();

    public RunAfter(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public void  execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                         QueueIn queueIn) {
        setFieldsStrategy.setFields(runtimeEvent,threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.runAdapter().after(
                new AfterContext(threadLocalDataWhenInTest,runtimeEvent,queueIn));
    }


    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
