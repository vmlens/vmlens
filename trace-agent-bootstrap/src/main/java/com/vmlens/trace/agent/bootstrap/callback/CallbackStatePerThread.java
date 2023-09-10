package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.AtomicCounter;
import com.vmlens.trace.agent.bootstrap.AtomicCounterShort;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;


// Fixme aufräumen
public class CallbackStatePerThread implements ThreadLocalWrapper {


    private static final AtomicCounter nextMappedId = new AtomicCounter();
    private static final AtomicCounterShort nextShortId = new AtomicCounterShort();

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    public final AnarsoftWeakHashMap<Object> arraysInThisThread = new AnarsoftWeakHashMap<Object>();

    private final boolean syncActionSameAsField4TraceCheck;
    public final QueueCollectionWrapper queueCollection;
    public final long threadId;


    public int tempDoNotInterleave;

    public int doNotInterleave;
    public int doNotInterleaveFromLock;
    public boolean methodTracingStarted = false;
//	public int inThreadBegin;
//	
    public int inThreadStart;
    //public int stackTraceBasedDoNotTraceField;

    /*
     *
     * wenn innerhal anarsoft soll nichts getracet werden
     *
     */
    // public int inAnarsoftStack;

    int stackTraceBasedDoTrace;
    /**
     * Das ist classloader, und aktuell file
     * Hier sollen nur keine Field Access getracet werden
     */
    public int stackTraceBasedDoNotTrace;
    //	 MethodIdAndFieldIds methodIdAndFieldIds = new MethodIdAndFieldIds();
    public int methodCount;
    public int programCount = 1;
    public int notStartedCount;
    public int stackTraceDepth;
    // private int STATIC_MAXIMUM_STACK_TRACE_DEPTH = 10;
    boolean queueIsFull;
    String threadName;
    private ParallelizedThreadLocal parallelizedThreadLocal;
    private boolean threadIsOk;
    private boolean doNotcheckStackTraceBasedDoTrace;
    private int maxStackTraceDepth = CallbackState.maxStackTraceDepth;

    public CallbackStatePerThread(boolean doNotcheckStackTraceBasedDoTrace, int maxStackTraceDepth,
                                  long inThreadId, QueueCollection inQueueCollection, boolean syncActionSameAsField4TraceCheck) {
        this(doNotcheckStackTraceBasedDoTrace, maxStackTraceDepth, inThreadId, inQueueCollection, syncActionSameAsField4TraceCheck, true);
    }

    public CallbackStatePerThread(boolean doNotcheckStackTraceBasedDoTrace, int maxStackTraceDepth,
                                  long inThreadId, QueueCollection inQueueCollection, boolean syncActionSameAsField4TraceCheck, boolean doSend) {
        this.syncActionSameAsField4TraceCheck = syncActionSameAsField4TraceCheck;
        String name = Thread.currentThread().getName();
        threadId = inThreadId;
        threadName = name;
        threadIsOk = !name.equals(ANARSOFT_THREAD_NAME) && !name.equals("Finalizer");
        this.doNotcheckStackTraceBasedDoTrace = doNotcheckStackTraceBasedDoTrace;
        //	 this.maxStackTraceDepth = maxStackTraceDepth;
        this.queueCollection = new QueueCollectionWrapper(inQueueCollection);

    }

    public boolean isStackTraceIncomplete() {
        return !traceMethodCall() && !isInInterleaveLoop();
    }

    public boolean isInInterleaveLoop() {
        return parallelizedThreadLocal != null;
    }

    public boolean traceMethodCall() {
        if (isInInterleaveLoop()) {
            return true;
        }
        if (stackTraceDepth < 2) {
            return true;
        }
        if (queueIsFull) {
            if (stackTraceDepth < maxStackTraceDepth) {
                maxStackTraceDepth = stackTraceDepth;
                // CallbackState.setMaxStackTraceDepth(maxStackTraceDepth);
                queueIsFull = false;
                return true;
            }
        }
        return stackTraceDepth < maxStackTraceDepth;
    }

    /*
     *
     * Note: stackTraceBasedDoNotTrace is used for filtering class loading.  Nicht entfernen ansonsten laeuft vmlens zu lange.
     *
     * @return
     */

    public boolean waitWhenTraceSyncStatements() {


        return (doNotcheckStackTraceBasedDoTrace || stackTraceBasedDoTrace > 0) && stackTraceBasedDoNotTrace < 1;
    }


    private boolean trace() {
        return threadIsOk && stackTraceBasedDoNotTrace < 1;
    }


    public boolean traceSyncStatements() {
        if (syncActionSameAsField4TraceCheck) {
            return traceMethods();
        } else {
            return trace();
        }


        //  && stackTraceBasedDoNotTrace < 1;
    }

    public boolean traceMethods() {
        return trace() && (doNotcheckStackTraceBasedDoTrace || stackTraceBasedDoTrace > 0); //   && stackTraceBasedDoNotTrace < 1;
    }

    public boolean traceFields() {
        return traceMethods();
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
}
