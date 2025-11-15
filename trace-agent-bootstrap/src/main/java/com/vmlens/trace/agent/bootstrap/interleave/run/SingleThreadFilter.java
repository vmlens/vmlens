package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ThreadCount;

public class SingleThreadFilter implements ThreadCount  {

    private int threadCount;


    /*
     * thread start increments thread count and we need to take thread count
     * thread join decrements thread count and we need to take thread join
     *
     */
    public boolean take(EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent) {
        int previousThreadCount = threadCount;
        InterleaveActionFactory factory = runtimeEvent.asInterleaveActionFactory();
        if(factory != null) {
            factory.update(this);
        }

        // This will take thread join
        if(previousThreadCount > 0) {
            return true;
        }
        // this will take thread start
        if(threadCount > 0) {
            return true;
        }

        return false;
    }

    @Override
    public void decrement(int delta) {
        threadCount -= delta;
    }

    @Override
    public void increment() {
        threadCount++;
    }
}
