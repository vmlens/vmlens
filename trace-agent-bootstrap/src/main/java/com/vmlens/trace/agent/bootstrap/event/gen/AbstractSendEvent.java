package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.*;
import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.callback.QueueCollectionWrapper;
import com.vmlens.trace.agent.bootstrap.parallize.*;

public abstract class AbstractSendEvent implements SendEvent {
	
	
	public static final int ID_Field		= 0;
	public static final int ID_SyncActions 	= 1;
	public static final int ID_Monitor 		= 2;
	public static final int ID_Method 		= 3;
	public static final int ID_DirectMemory = 4;
	public static final int ID_Scheduler 	= 5;
	public static final int ID_Transfer 	= 6;
	public static final int ID_State 	    = 7;
	public static final int ID_StateInitial = 8;
	
	protected abstract long threadId();
	protected abstract QueueCollectionWrapper  getQueueCollection();
	protected abstract ParallizedThreadFacade getParallizedThreadFacade();
	
	
public void writeFieldAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
, final  long     objectHashCode
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(FieldAccessEventGen.class) )
{

 getQueueCollection().put( ID_Field , 
EventFactory.createFieldAccessEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
, parallizedThreadFacade.showNonVolatileMemoryAccess()
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createFieldAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeFieldAccessEventStaticGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(FieldAccessEventStaticGen.class) )
{

 getQueueCollection().put( ID_Field , 
EventFactory.createFieldAccessEventStaticGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
, parallizedThreadFacade.showNonVolatileMemoryAccess()
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createFieldAccessEventStaticGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
) , slidingWindowId
);

}


}

public void writeArrayAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
, final  long     objectHashCode
, final  int     position
, final  int     classId
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(ArrayAccessEventGen.class) )
{

 getQueueCollection().put( ID_Field , 
EventFactory.createArrayAccessEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
,  
position
,  
classId
, parallizedThreadFacade.showNonVolatileMemoryAccess()
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createArrayAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
,  
position
,  
classId
) , slidingWindowId
);

}


}

public void writeVolatileAccessEventStaticGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     fieldId
, final  int     methodCounter
, final  int     methodId
, final  boolean     isWrite
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(VolatileAccessEventStaticGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createVolatileAccessEventStaticGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
isWrite
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileAccessEventStaticGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
isWrite
) , slidingWindowId
);

}


}

public void writeVolatileAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     fieldId
, final  int     methodCounter
, final  int     methodId
, final  int     operation
, final  long     objectHashCode
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(VolatileAccessEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createVolatileAccessEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeVolatileArrayAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  long     index
, final  int     methodCounter
, final  int     methodId
, final  int     operation
, final  long     objectHashCode
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(VolatileArrayAccessEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createVolatileArrayAccessEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
index
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileArrayAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
index
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeVolatileDirectMemoryEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     methodCounter
, final  long     objectHashCode
, final  int     operation
, final  int     order
)
{

{
 getQueueCollection().put( ID_DirectMemory , 
					
							
							EventFactory.createVolatileDirectMemoryEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
methodCounter
,  
objectHashCode
,  
operation
,  
order
) , slidingWindowId
);

}


}

public void writeLockEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(LockEnterEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createLockEnterEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createLockEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
) , slidingWindowId
);

}


}

public void writeLockExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(LockExitEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createLockExitEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createLockExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
) , slidingWindowId
);

}


}

public void writeStampedLockEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
, final  int     stampedLockMethodId
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(StampedLockEnterEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createStampedLockEnterEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createStampedLockEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
) , slidingWindowId
);

}


}

public void writeStampedLockExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
, final  int     stampedLockMethodId
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(StampedLockExitEventGen.class) )
{

 getQueueCollection().put( ID_SyncActions , 
EventFactory.createStampedLockExitEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createStampedLockExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
) , slidingWindowId
);

}


}

public void writeMonitorEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  int     methodId
, final  int     position
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(MonitorEnterEventGen.class) )
{

 getQueueCollection().put( ID_Monitor , 
EventFactory.createMonitorEnterEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_Monitor , 
					
							
							EventFactory.createMonitorEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
) , slidingWindowId
);

}


}

public void writeMonitorExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  int     methodId
, final  int     position
)
{

ParallizedThreadFacade parallizedThreadFacade = getParallizedThreadFacade();

if(parallizedThreadFacade != null && parallizedThreadFacade.sendAsInterleaveEvent(MonitorExitEventGen.class) )
{

 getQueueCollection().put( ID_Monitor , 
EventFactory.createMonitorExitEventGenInterleave ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
, parallizedThreadFacade.loopId()
,  parallizedThreadFacade.runId()
,  parallizedThreadFacade.runPosition()

) , slidingWindowId
);
}
else

{
 getQueueCollection().put( ID_Monitor , 
					
							
							EventFactory.createMonitorExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
) , slidingWindowId
);

}


}

public void writeMethodEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeParallizedMethodEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  int     parallizeId
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createParallizedMethodEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
parallizeId
) , slidingWindowId
);

}


}

public void writeParallizedMethodExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createParallizedMethodExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodEnterSmallThreadIdEventGen (final int slidingWindowId
, final  byte     smallThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterSmallThreadIdEventGen ( slidingWindowId
 ,  
smallThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodExitSmallThreadIdEventGen (final int slidingWindowId
, final  byte     smallThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitSmallThreadIdEventGen ( slidingWindowId
 ,  
smallThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodEnterShortThreadIdEventGen (final int slidingWindowId
, final  short     shortThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterShortThreadIdEventGen ( slidingWindowId
 ,  
shortThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodExitShortThreadIdEventGen (final int slidingWindowId
, final  short     shortThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitShortThreadIdEventGen ( slidingWindowId
 ,  
shortThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeThreadBeginEventGen (final int slidingWindowId
, final  long     startedThreadId
, final  int     programCounter
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createThreadBeginEventGen ( slidingWindowId
 ,  
threadId()
,  
startedThreadId
,  
programCounter
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeThreadStoppedEventGen (final int slidingWindowId
, final  long     stoppedThreadId
, final  int     programCounter
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createThreadStoppedEventGen ( slidingWindowId
 ,  
threadId()
,  
stoppedThreadId
,  
programCounter
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeMethodAtomicEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  byte     hasCallback
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodAtomicEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
hasCallback
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeMethodAtomicExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  byte     hasCallback
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodAtomicExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
hasCallback
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeMethodCallbackEnterEventGen (final int slidingWindowId
, final  int     methodCounter
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodCallbackEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodCounter
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeMethodCallbackExitEventGen (final int slidingWindowId
, final  int     methodCounter
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodCallbackExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodCounter
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeLoopStartEventGen (final int slidingWindowId
, final  int     loopId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopStartEventGen ( slidingWindowId
 ,  
loopId
) , slidingWindowId
);

}


}

public void writeLoopEndEventGen (final int slidingWindowId
, final  int     loopId
, final  int     status
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopEndEventGen ( slidingWindowId
 ,  
loopId
,  
status
) , slidingWindowId
);

}


}

public void writeRunStartEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createRunStartEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeRunEndEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createRunEndEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeLoopWarningEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopWarningEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeStateEventFieldGen (final int slidingWindowId
, final  int     classId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventFieldGen ( slidingWindowId
 ,  
threadId()
,  
classId
,  
methodId
,  
methodCounter
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeStateEventFieldInitialGen (final int slidingWindowId
, final  long     threadIdAtEvent
, final  int     classId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
, final  int     slidingWindowIdAtEvent
)
{

{
 getQueueCollection().put( ID_StateInitial , 
					
							
							EventFactory.createStateEventFieldInitialGen ( slidingWindowId
 ,  
threadIdAtEvent
,  
classId
,  
methodId
,  
methodCounter
,  
operation
,  
objectHashCode
,  
slidingWindowIdAtEvent
) , slidingWindowId
);

}


}

public void writeStateEventStaticFieldGen (final int slidingWindowId
, final  int     fieldId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventStaticFieldGen ( slidingWindowId
 ,  
threadId()
,  
fieldId
,  
methodId
,  
methodCounter
,  
operation
) , slidingWindowId
);

}


}

public void writeStateEventStaticFieldInitialGen (final int slidingWindowId
, final  long     threadIdAtEvent
, final  int     fieldId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  int     slidingWindowIdAtEvent
)
{

{
 getQueueCollection().put( ID_StateInitial , 
					
							
							EventFactory.createStateEventStaticFieldInitialGen ( slidingWindowId
 ,  
threadIdAtEvent
,  
fieldId
,  
methodId
,  
methodCounter
,  
operation
,  
slidingWindowIdAtEvent
) , slidingWindowId
);

}


}

public void writeStateEventArrayGen (final int slidingWindowId
, final  int     methodId
, final  int     position
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
, final  int     classId
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventArrayGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
position
,  
methodCounter
,  
operation
,  
objectHashCode
,  
classId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveFieldAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
, final  long     objectHashCode
)
{

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createFieldAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveFieldAccessEventStaticGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
)
{

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createFieldAccessEventStaticGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveArrayAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     fieldId
, final  int     methodCounter
, final  int     operation
, final  int     methodId
, final  boolean     stackTraceIncomplete
, final  long     objectHashCode
, final  int     position
, final  int     classId
)
{

{
 getQueueCollection().put( ID_Field , 
					
							
							EventFactory.createArrayAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
fieldId
,  
methodCounter
,  
operation
,  
methodId
,  
stackTraceIncomplete
,  
objectHashCode
,  
position
,  
classId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveVolatileAccessEventStaticGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     fieldId
, final  int     methodCounter
, final  int     methodId
, final  boolean     isWrite
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileAccessEventStaticGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
isWrite
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveVolatileAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     fieldId
, final  int     methodCounter
, final  int     methodId
, final  int     operation
, final  long     objectHashCode
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
fieldId
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveVolatileArrayAccessEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  long     index
, final  int     methodCounter
, final  int     methodId
, final  int     operation
, final  long     objectHashCode
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createVolatileArrayAccessEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
index
,  
methodCounter
,  
methodId
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveVolatileDirectMemoryEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     methodCounter
, final  long     objectHashCode
, final  int     operation
, final  int     order
)
{

{
 getQueueCollection().put( ID_DirectMemory , 
					
							
							EventFactory.createVolatileDirectMemoryEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
methodCounter
,  
objectHashCode
,  
operation
,  
order
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveLockEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createLockEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveLockExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createLockExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStampedLockEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
, final  int     stampedLockMethodId
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createStampedLockEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStampedLockExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  boolean     isShared
, final  int     lockTyp
, final  int     stampedLockMethodId
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createStampedLockExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
isShared
,  
lockTyp
,  
stampedLockMethodId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMonitorEnterEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  int     methodId
, final  int     position
)
{

{
 getQueueCollection().put( ID_Monitor , 
					
							
							EventFactory.createMonitorEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMonitorExitEventGen (final int slidingWindowId
, final  int     programCounter
, final  int     order
, final  int     monitorId
, final  int     methodCounter
, final  int     methodId
, final  int     position
)
{

{
 getQueueCollection().put( ID_Monitor , 
					
							
							EventFactory.createMonitorExitEventGen ( slidingWindowId
 ,  
threadId()
,  
programCounter
,  
order
,  
monitorId
,  
methodCounter
,  
methodId
,  
position
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveParallizedMethodEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  int     parallizeId
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createParallizedMethodEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
parallizeId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveParallizedMethodExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createParallizedMethodExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodEnterSmallThreadIdEventGen (final int slidingWindowId
, final  byte     smallThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterSmallThreadIdEventGen ( slidingWindowId
 ,  
smallThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodExitSmallThreadIdEventGen (final int slidingWindowId
, final  byte     smallThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitSmallThreadIdEventGen ( slidingWindowId
 ,  
smallThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodEnterShortThreadIdEventGen (final int slidingWindowId
, final  short     shortThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodEnterShortThreadIdEventGen ( slidingWindowId
 ,  
shortThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodExitShortThreadIdEventGen (final int slidingWindowId
, final  short     shortThreadId
, final  int     methodId
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_Method , 
					
							
							EventFactory.createMethodExitShortThreadIdEventGen ( slidingWindowId
 ,  
shortThreadId
,  
methodId
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveThreadBeginEventGen (final int slidingWindowId
, final  long     startedThreadId
, final  int     programCounter
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createThreadBeginEventGen ( slidingWindowId
 ,  
threadId()
,  
startedThreadId
,  
programCounter
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveThreadStoppedEventGen (final int slidingWindowId
, final  long     stoppedThreadId
, final  int     programCounter
, final  int     methodCounter
)
{

{
 getQueueCollection().put( ID_SyncActions , 
					
							
							EventFactory.createThreadStoppedEventGen ( slidingWindowId
 ,  
threadId()
,  
stoppedThreadId
,  
programCounter
,  
methodCounter
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodAtomicEnterEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  byte     hasCallback
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodAtomicEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
hasCallback
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodAtomicExitEventGen (final int slidingWindowId
, final  int     methodId
, final  int     methodCounter
, final  byte     hasCallback
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodAtomicExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
methodCounter
,  
hasCallback
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodCallbackEnterEventGen (final int slidingWindowId
, final  int     methodCounter
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodCallbackEnterEventGen ( slidingWindowId
 ,  
threadId()
,  
methodCounter
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveMethodCallbackExitEventGen (final int slidingWindowId
, final  int     methodCounter
, final  int     loopId
, final  int     runId
, final  int     runPosition
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createMethodCallbackExitEventGen ( slidingWindowId
 ,  
threadId()
,  
methodCounter
,  
loopId
,  
runId
,  
runPosition
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveLoopStartEventGen (final int slidingWindowId
, final  int     loopId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopStartEventGen ( slidingWindowId
 ,  
loopId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveLoopEndEventGen (final int slidingWindowId
, final  int     loopId
, final  int     status
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopEndEventGen ( slidingWindowId
 ,  
loopId
,  
status
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveRunStartEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createRunStartEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveRunEndEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createRunEndEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveLoopWarningEventGen (final int slidingWindowId
, final  int     loopId
, final  int     runId
)
{

{
 getQueueCollection().put( ID_Scheduler , 
					
							
							EventFactory.createLoopWarningEventGen ( slidingWindowId
 ,  
loopId
,  
runId
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStateEventFieldGen (final int slidingWindowId
, final  int     classId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventFieldGen ( slidingWindowId
 ,  
threadId()
,  
classId
,  
methodId
,  
methodCounter
,  
operation
,  
objectHashCode
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStateEventFieldInitialGen (final int slidingWindowId
, final  long     threadIdAtEvent
, final  int     classId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
, final  int     slidingWindowIdAtEvent
)
{

{
 getQueueCollection().put( ID_StateInitial , 
					
							
							EventFactory.createStateEventFieldInitialGen ( slidingWindowId
 ,  
threadIdAtEvent
,  
classId
,  
methodId
,  
methodCounter
,  
operation
,  
objectHashCode
,  
slidingWindowIdAtEvent
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStateEventStaticFieldGen (final int slidingWindowId
, final  int     fieldId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventStaticFieldGen ( slidingWindowId
 ,  
threadId()
,  
fieldId
,  
methodId
,  
methodCounter
,  
operation
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStateEventStaticFieldInitialGen (final int slidingWindowId
, final  long     threadIdAtEvent
, final  int     fieldId
, final  int     methodId
, final  int     methodCounter
, final  int     operation
, final  int     slidingWindowIdAtEvent
)
{

{
 getQueueCollection().put( ID_StateInitial , 
					
							
							EventFactory.createStateEventStaticFieldInitialGen ( slidingWindowId
 ,  
threadIdAtEvent
,  
fieldId
,  
methodId
,  
methodCounter
,  
operation
,  
slidingWindowIdAtEvent
) , slidingWindowId
);

}


}

public void writeWithoutInterleaveStateEventArrayGen (final int slidingWindowId
, final  int     methodId
, final  int     position
, final  int     methodCounter
, final  int     operation
, final  long     objectHashCode
, final  int     classId
)
{

{
 getQueueCollection().put( ID_State , 
					
							
							EventFactory.createStateEventArrayGen ( slidingWindowId
 ,  
threadId()
,  
methodId
,  
position
,  
methodCounter
,  
operation
,  
objectHashCode
,  
classId
) , slidingWindowId
);

}


}

}