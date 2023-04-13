package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.AtomicCounter;
import com.vmlens.trace.agent.bootstrap.AtomicCounterShort;
import com.vmlens.trace.agent.bootstrap.OptionalByte;
import com.vmlens.trace.agent.bootstrap.OptionalShort;
import com.vmlens.trace.agent.bootstrap.event.ThreadNameEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.SendEvent;
import com.vmlens.trace.agent.bootstrap.mode.AgentMode;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizedThreadFacade;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;
import gnu.trove.map.hash.TIntIntHashMap;

// Fixme aufräumen
public class CallbackStatePerThread {


    private static final AtomicCounter nextMappedId = new AtomicCounter();
    private static final AtomicCounterShort nextShortId = new AtomicCounterShort();

    public final TIntIntHashMap waitPointId2ActivatingCount = new TIntIntHashMap();
    public final TIntIntHashMap waitPointId2DeActivatiedCount = new TIntIntHashMap();

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

	public boolean methodTracingStarted = false;
	public final AnarsoftWeakHashMap<Object>  arraysInThisThread = new  AnarsoftWeakHashMap<Object> ();

    public final SendEvent sendEvent;
    private final boolean syncActionSameAsField4TraceCheck;
    public final AgentMode mode;

    public ParallizedThreadFacade parallizedThread;
    // Fixme erzeugt zirkulare referenz
    public ParallelizedThreadLocal parallelizedThreadLocal;
    //public int monitorCount;


//	public int monitorCount;


    public int tempDoNotInterleave;

    public int doNotInterleave;
    public int doNotInterleaveFromLock;
	
	
  public int inThreadStart;
//	public int inThreadBegin;
//	
	
	/**
	 * Das ist classloader, und aktuell file
	 * Hier sollen nur keine Field Access getracet werden
	 * 
	 * 
	 */
    public int stackTraceBasedDoNotTrace;
    //public int stackTraceBasedDoNotTraceField;

    /*
     * 
     * wenn innerhal anarsoft soll nichts getracet werden
     * 
     */
   // public int inAnarsoftStack;
  
    int stackTraceBasedDoTrace;
	 
//	 MethodIdAndFieldIds methodIdAndFieldIds = new MethodIdAndFieldIds();
	public  int methodCount;
	public  int programCount = 1;
	  
	 private  boolean threadIsOk;
	 private  boolean doNotcheckStackTraceBasedDoTrace;
	 
	 int stackTraceDepth;

	// final boolean delaySynchronization;
	 
	// public int needsToWaitAfterMethod=0;
	 
	 
	 public final  QueueCollectionWrapper  queueCollection;
	 private int maxStackTraceDepth = CallbackState.maxStackTraceDepth;
	// private int STATIC_MAXIMUM_STACK_TRACE_DEPTH = 10;
	 boolean queueIsFull;
	 String threadName;
	 public final long threadId;
	public int notStartedCount;
	 
	 public boolean isStackTraceIncomplete()
	 {
		 return ! traceMethodCall() && ! isInInterleaveLoop();
	 }
	 public boolean isInInterleaveLoop()
	 {
		 if(parallizedThread == null)
		 {
			 return false;
		 }
		 return parallizedThread.isInInterleaveLoop();
	 }
	 
	// private final int absoluteMaxStackTrace = 10000000;
	 public boolean traceMethodCall()
	 {
		 if( isInInterleaveLoop() )
		 {
			 return true;
		 }
		 if(stackTraceDepth < 2)
		 {
			 return true;
		 }
		 if( queueIsFull )
		 {
			 if(  stackTraceDepth <  maxStackTraceDepth)
			 {
				 maxStackTraceDepth = stackTraceDepth;
				// CallbackState.setMaxStackTraceDepth(maxStackTraceDepth);
				 queueIsFull = false;
				 return true;
			 }
		 }
		 return stackTraceDepth <  maxStackTraceDepth;
	 }
	 
	 
	 public CallbackStatePerThread( boolean doNotcheckStackTraceBasedDoTrace,int maxStackTraceDepth,
			 long inThreadId,QueueCollection  inQueueCollection,boolean syncActionSameAsField4TraceCheck,AgentMode mode)  {
		 this(  doNotcheckStackTraceBasedDoTrace , maxStackTraceDepth , inThreadId , inQueueCollection , syncActionSameAsField4TraceCheck ,    mode, true);
	 }
	 
	 
	 public CallbackStatePerThread( boolean doNotcheckStackTraceBasedDoTrace,int maxStackTraceDepth,
			 long inThreadId,QueueCollection  inQueueCollection,boolean syncActionSameAsField4TraceCheck,AgentMode mode, boolean doSend)  {
		 this.mode = mode;
		 this.syncActionSameAsField4TraceCheck = syncActionSameAsField4TraceCheck;
		 String name = Thread.currentThread().getName();
		 threadId = inThreadId;
		 threadName = name;
		 threadIsOk = ! name.equals(ANARSOFT_THREAD_NAME) && ! name.equals("Finalizer");
		 this.doNotcheckStackTraceBasedDoTrace = doNotcheckStackTraceBasedDoTrace;
	//	 this.maxStackTraceDepth = maxStackTraceDepth;
		 this.queueCollection = new QueueCollectionWrapper(inQueueCollection);

		 if( threadIsOk )
		 {
			 
//			 synchronized(tracedThreadCount_lock)
//			 {
//				
//				 if( tracedThreadCount > 50)
//				 {
//					 doSend = false;
//				 }
//				 else
//				 {
//					 tracedThreadCount++;
//				 }
//				 
//				 
//			 }

			 if(doSend)
			 {
				 final OptionalByte mappedId = nextMappedId.nextValue();
				 if(mappedId.isHasByte())
				 {
					 sendEvent  = new SendEventForSmallThreadId(inThreadId,queueCollection,mappedId.getTheValue(), this);

					 queueCollection.putDirect(  new ThreadNameEvent(threadId ,threadName , mappedId , new OptionalShort(false,(short)0) ) );
					 
					 
				 }
				 else
				 {
					 OptionalShort shortThreadId = nextShortId.nextValue();
					 queueCollection.putDirect(  new ThreadNameEvent(threadId ,threadName , mappedId , shortThreadId ) );
					 if(shortThreadId.isHasShort())
					 {
						 sendEvent  = new   SendEventForShortThreadId(inThreadId,queueCollection,shortThreadId.getTheValue(),this);
					 }
					 else
					 {
						 sendEvent  = new SendEventImpl(inThreadId,queueCollection,this); 
					 }
				 }
			 }
			 else
			 {
				 sendEvent  = new SendEventDoNotSend(); 
			 }
		 }
		 else
		 {
			 sendEvent  = new SendEventDoNotSend(); 
		 }
	 }

	 /*
	  * 
	  * Note: stackTraceBasedDoNotTrace is used for filtering class loading.  Nicht entfernen ansonsten laeuft vmlens zu lange.
	  * 
	  * @return
	  */
	 
	 
	 public boolean waitWhenTraceSyncStatements()
	 {
		 
		 
		 return  (doNotcheckStackTraceBasedDoTrace ||  stackTraceBasedDoTrace > 0) &&  stackTraceBasedDoNotTrace < 1 ;
	 }
	 
	 
	 private boolean trace()
	 {
		 return threadIsOk &&  stackTraceBasedDoNotTrace < 1; 
	 }
	 
	 
	 public boolean traceSyncStatements()
	 {
		 if(syncActionSameAsField4TraceCheck)
		 {
			 return traceMethods();
		 }
		 else
		 {
			 return  trace();
		 }
		 
		
		//  && stackTraceBasedDoNotTrace < 1;
	 }
	 
	 public boolean traceMethods()
	 {
		 return  trace() && ( doNotcheckStackTraceBasedDoTrace ||  stackTraceBasedDoTrace > 0); //   && stackTraceBasedDoNotTrace < 1;
		 
		 
		
		 //return traceSyncStatements(); //     && stackTraceBasedDoNotTrace < 1;
		 
//				 && stackTraceBasedDoNotTrace < 1;
//				 && ( doNotcheckStackTraceBasedDoTrace ||  stackTraceBasedDoTrace > 0);
	 }
	 
	 public boolean traceFields()
	 {
		 return  traceMethods()   ;
//				
//				 && ( doNotcheckStackTraceBasedDoTrace ||  stackTraceBasedDoTrace > 0);
		 // && stackTraceBasedDoNotTrace < 1 && stackTraceBasedDoNotTraceField < 1;
	 }
	  
	 

	
	 
}
