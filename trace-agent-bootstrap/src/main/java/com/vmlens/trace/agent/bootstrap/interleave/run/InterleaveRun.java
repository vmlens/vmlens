package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class InterleaveRun {

    private final  InterleaveLoopContext interleaveLoopContext;
    private final TLinkedList<TLinkableWrapper<InterleaveAction>> run =
            new TLinkedList<>();
    private final SingleThreadFilter singleThreadFilter = new SingleThreadFilter();
    private int positionInRun;
    private InterleaveRunState state;
    private boolean hasLoop;

    public InterleaveRun(InterleaveLoopContext interleaveLoopContext) {
        this.interleaveLoopContext = interleaveLoopContext;
        state = new InterleaveRunStateWithoutCalculated(0);
    }

    public InterleaveRun(InterleaveLoopContext interleaveLoopContext,
                         Position[] calculatedRunElementArray) {
        this.interleaveLoopContext = interleaveLoopContext;
        state = new InterleaveRunStateWithCalculated(calculatedRunElementArray);
    }

    public boolean after(EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent,
                      ThreadIndexAndThreadStateMap context,
                      ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                      SendEvent sendEvent) {
        ProcessEventContext processEventContext = new ProcessEventContext(
                context,threadLocalWhenInTestForParallelize,sendEvent);
        if(singleThreadFilter.take(runtimeEvent)) {
            AfterCallback afterCallback = new AfterCallback(this,sendEvent);
            InterleaveRunState result = state.after(processEventContext, afterCallback, runtimeEvent);
            if(result != null) {
                state = result;
                return true;
            }
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
        return false;
    }

    public boolean isActive(int threadIndex) {
        StateAndIsActive stateAndIsActive = state.isActive(threadIndex);
        state = stateAndIsActive.state();
        return stateAndIsActive.isActive();
    }

    public Integer shouldCheckActiveThreadIndex(TIntLinkedList activeThreadIndices) {
       /* if( System.currentTimeMillis() < (lastActiveThreadActivityAt  + 1000)) {
            return null;
        }
        */
        return state.activeThreadIndex(activeThreadIndices);
    }

    public void onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                     SendEvent sendEvent,
                                     int blockedThreadIndex) {
        state = state.onBlockedWithLogging(runContext,sendEvent,blockedThreadIndex);
    }


    public ActualRun run() {
        return new ActualRun(run,hasLoop);
    }

    void process(ProcessEventContext context, PluginEventOnly pluginEventOnly) {
        pluginEventOnly.setRunPosition(positionInRun);
        pluginEventOnly.setCounter(context.threadLocalWhenInTestForParallelize());
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

    public InterleaveLoopContext interleaveLoopContext() {
        return interleaveLoopContext;
    }
}
