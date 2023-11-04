package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapperForParallelize;


// Fixme aufräumen
public class CallbackStatePerThreadForParallelize extends PerThreadCounter implements ThreadLocalWrapperForParallelize {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    public final AnarsoftWeakHashMap<Object> arraysInThisThread = new AnarsoftWeakHashMap<Object>();

    private final QueueIn queueIn;
    private final long threadId;

    public int tempDoNotInterleave;
    public int doNotInterleave;
    public int doNotInterleaveFromLock;
    public int inThreadStart;
    int stackTraceBasedDoTrace;
    /**
     * Das ist classloader, und aktuell file
     * Hier sollen nur keine Field Access getracet werden
     */
    public int stackTraceBasedDoNotTrace;
    //	 MethodIdAndFieldIds methodIdAndFieldIds = new MethodIdAndFieldIds();
    public int notStartedCount;


    private ParallelizedThreadLocal parallelizedThreadLocal;


    private int programCount = 1;


    public CallbackStatePerThreadForParallelize(long threadId, QueueIn queueIn) {
        this.queueIn = queueIn;
        this.threadId = threadId;
    }

    @Override
    public long threadId() {
        return threadId;
    }

    @Override
    public ParallelizedThreadLocal getParallelizedThreadLocal() {
        return parallelizedThreadLocal;
    }

    @Override
    public void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }


    public void put(int id, StaticEvent element, int slidingWindowId) {
        queueIn.put(id, element, slidingWindowId);
    }

    public void putDirect(StaticEvent in) {
        queueIn.putDirect(in);
    }

}
