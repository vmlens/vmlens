package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.*;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class InterleaveRun {

    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run =
            new TLinkedList<>();
    private final SingleThreadFilter singleThreadFilter = new SingleThreadFilter();
    private int positionInRun;
    private InterleaveRunState state;
    private boolean hasLoop;

    public InterleaveRun() {
        state = new InterleaveRunStateWithoutCalculated(0);
    }

    public InterleaveRun(Position[] calculatedRunElementArray) {
        state = new InterleaveRunStateWithCalculated(calculatedRunElementArray);
    }

    public void after(EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent,
                      ThreadIndexAndThreadStateMap context,
                      ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                      SendEvent sendEvent) {
        ProcessEventContext processEventContext = new ProcessEventContext(
                context,threadLocalWhenInTestForParallelize,sendEvent);
        if(singleThreadFilter.take(runtimeEvent)) {
            AfterCallback afterCallback = new AfterCallback(this,sendEvent);
            state = state.after(processEventContext,afterCallback,runtimeEvent);
       /*  this actually not really useful
           since typically the start is in the main method anyway
       } else {
            PluginEventOnly pluginEvent = runtimeEvent.asPluginEventOnly();
            if( pluginEvent != null) {
                if(pluginEvent.isMethodEnterOrExit()) {
                    process(processEventContext,pluginEvent);
                }
            }*/
        }
    }

    public boolean isActive(int threadIndex, TIntLinkedList activeThreadIndices) {
        StateAndIsActive stateAndIsActive = state.isActive(threadIndex,activeThreadIndices);
        state = stateAndIsActive.state();
        return stateAndIsActive.isActive();
    }

    public int activeThreadIndex(TIntLinkedList activeThreadIndices) {
        StateAndThreadIndex stateAndThreadIndex =  state.activeThreadIndex(activeThreadIndices);
        state = stateAndThreadIndex.state();
        return stateAndThreadIndex.threadIndex();
    }

    public void onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext, SendEvent sendEvent, int activeThreadIndex) {
        state = state.onBlockedWithLogging(runContext,sendEvent,activeThreadIndex);
    }

    public void onBlockedWithoutLogging(int activeThreadIndex) {
        state = state.onBlockedWithoutLogging(activeThreadIndex);
    }

    public ActualRun run() {
        return new ActualRun(run,hasLoop);
    }

    void process(ProcessEventContext context, PluginEventOnly pluginEventOnly) {
        pluginEventOnly.setRunPosition(positionInRun);
        pluginEventOnly.setMethodCounter(context.threadLocalWhenInTestForParallelize());
        context.sendEvent().sendSerializable(pluginEventOnly);
    }

    int process(ProcessEventContext context, InterleaveActionFactory interleaveActionFactory) {
        InterleaveAction action =  interleaveActionFactory.createAndSend(context.context(),
                context.threadLocalWhenInTestForParallelize(),
                context.sendEvent(),
                positionInRun);
        positionInRun++;
        run.add(wrap(action));
        return action.threadIndex();
    }

    public void setHasLoop() {
        this.hasLoop = true;
    }
}
