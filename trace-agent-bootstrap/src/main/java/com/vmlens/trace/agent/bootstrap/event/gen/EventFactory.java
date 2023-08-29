package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.nio.ByteBuffer;

public class EventFactory {


	public static int MAX_ARRAY_SIZE = 59;
	
public static RuntimeEvent createFieldAccessEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
,  long     objectHashCode
) 
{

return new FieldAccessEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
  ,  objectHashCode
);
  
 

 


}
public static RuntimeEvent createFieldAccessEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
,  long     objectHashCode
,  boolean     showSharedMemory
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new FieldAccessEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
  ,  objectHashCode
  ,  showSharedMemory
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createFieldAccessEventStaticGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
) 
{

return new FieldAccessEventStaticGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
);
  
 

 


}
public static RuntimeEvent createFieldAccessEventStaticGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
,  boolean     showSharedMemory
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new FieldAccessEventStaticGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
  ,  showSharedMemory
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createArrayAccessEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
,  long     objectHashCode
,  int     position
,  int     classId
) 
{

return new ArrayAccessEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
  ,  objectHashCode
  ,  position
  ,  classId
);
  
 

 


}
public static RuntimeEvent createArrayAccessEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  int     operation
,  int     methodId
,  boolean     stackTraceIncomplete
,  long     objectHashCode
,  int     position
,  int     classId
,  boolean     showSharedMemory
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new ArrayAccessEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  fieldId
  ,  methodCounter
  ,  operation
  ,  methodId
  ,  stackTraceIncomplete
  ,  objectHashCode
  ,  position
  ,  classId
  ,  showSharedMemory
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createVolatileAccessEventStaticGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     fieldId
,  int     methodCounter
,  int     methodId
,  boolean     isWrite
) 
{

return new VolatileAccessEventStaticGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  fieldId
  ,  methodCounter
  ,  methodId
  ,  isWrite
);
  
 

 


}
public static RuntimeEvent createVolatileAccessEventStaticGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     fieldId
,  int     methodCounter
,  int     methodId
,  boolean     isWrite
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new VolatileAccessEventStaticGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  fieldId
  ,  methodCounter
  ,  methodId
  ,  isWrite
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createVolatileAccessEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     fieldId
,  int     methodCounter
,  int     methodId
,  int     operation
,  long     objectHashCode
) 
{

return new VolatileAccessEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  fieldId
  ,  methodCounter
  ,  methodId
  ,  operation
  ,  objectHashCode
);
  
 

 


}
public static RuntimeEvent createVolatileAccessEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     fieldId
,  int     methodCounter
,  int     methodId
,  int     operation
,  long     objectHashCode
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new VolatileAccessEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  fieldId
  ,  methodCounter
  ,  methodId
  ,  operation
  ,  objectHashCode
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createVolatileArrayAccessEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  long     index
,  int     methodCounter
,  int     methodId
,  int     operation
,  long     objectHashCode
) 
{

return new VolatileArrayAccessEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  index
  ,  methodCounter
  ,  methodId
  ,  operation
  ,  objectHashCode
);
  
 

 


}
public static RuntimeEvent createVolatileArrayAccessEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  long     index
,  int     methodCounter
,  int     methodId
,  int     operation
,  long     objectHashCode
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new VolatileArrayAccessEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  index
  ,  methodCounter
  ,  methodId
  ,  operation
  ,  objectHashCode
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createVolatileDirectMemoryEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     methodCounter
,  long     objectHashCode
,  int     operation
,  int     order
) 
{

return new VolatileDirectMemoryEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  methodCounter
  ,  objectHashCode
  ,  operation
  ,  order
);
  
 

 


}
public static RuntimeEvent createLockEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
) 
{

return new LockEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
);
  
 

 


}
public static RuntimeEvent createLockEnterEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new LockEnterEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createLockExitEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
) 
{

return new LockExitEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
);
  
 

 


}
public static RuntimeEvent createLockExitEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new LockExitEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createStampedLockEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     stampedLockMethodId
) 
{

return new StampedLockEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  stampedLockMethodId
);
  
 

 


}
public static RuntimeEvent createStampedLockEnterEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     stampedLockMethodId
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new StampedLockEnterEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  stampedLockMethodId
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createStampedLockExitEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     stampedLockMethodId
) 
{

return new StampedLockExitEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  stampedLockMethodId
);
  
 

 


}
public static RuntimeEvent createStampedLockExitEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  boolean     isShared
,  int     lockTyp
,  int     stampedLockMethodId
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new StampedLockExitEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  isShared
  ,  lockTyp
  ,  stampedLockMethodId
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMonitorEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  int     methodId
,  int     position
) 
{

return new MonitorEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  methodId
  ,  position
);
  
 

 


}
public static RuntimeEvent createMonitorExitEventGen ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  int     methodId
,  int     position
) 
{

return new MonitorExitEventGen(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  methodId
  ,  position
);
  
 

 


}
public static RuntimeEvent createMonitorEnterEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  int     methodId
,  int     position
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MonitorEnterEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  methodId
  ,  position
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMonitorExitEventGenInterleave ( int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     order
,  int     monitorId
,  int     methodCounter
,  int     methodId
,  int     position
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MonitorExitEventGenInterleave(
   slidingWindowId
  ,  threadId
  ,  programCounter
  ,  order
  ,  monitorId
  ,  methodCounter
  ,  methodId
  ,  position
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMethodEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodExitEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodExitEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createParallizedMethodEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
,  int     parallizeId
) 
{

return new ParallizedMethodEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
  ,  parallizeId
);
  
 

 


}
public static RuntimeEvent createParallizedMethodExitEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
) 
{

return new ParallizedMethodExitEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodEnterSmallThreadIdEventGen ( int slidingWindowId
,  byte     smallThreadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodEnterSmallThreadIdEventGen(
   slidingWindowId
  ,  smallThreadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodExitSmallThreadIdEventGen ( int slidingWindowId
,  byte     smallThreadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodExitSmallThreadIdEventGen(
   slidingWindowId
  ,  smallThreadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodEnterShortThreadIdEventGen ( int slidingWindowId
,  short     shortThreadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodEnterShortThreadIdEventGen(
   slidingWindowId
  ,  shortThreadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodExitShortThreadIdEventGen ( int slidingWindowId
,  short     shortThreadId
,  int     methodId
,  int     methodCounter
) 
{

return new MethodExitShortThreadIdEventGen(
   slidingWindowId
  ,  shortThreadId
  ,  methodId
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createThreadBeginEventGen ( int slidingWindowId
,  long     threadId
,  long     startedThreadId
,  int     programCounter
,  int     methodCounter
) 
{

return new ThreadBeginEventGen(
   slidingWindowId
  ,  threadId
  ,  startedThreadId
  ,  programCounter
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createThreadStoppedEventGen ( int slidingWindowId
,  long     threadId
,  long     stoppedThreadId
,  int     programCounter
,  int     methodCounter
) 
{

return new ThreadStoppedEventGen(
   slidingWindowId
  ,  threadId
  ,  stoppedThreadId
  ,  programCounter
  ,  methodCounter
);
  
 

 


}
public static RuntimeEvent createMethodAtomicEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
,  byte     hasCallback
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MethodAtomicEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
  ,  hasCallback
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMethodAtomicExitEventGen ( int slidingWindowId
,  long     threadId
,  int     methodId
,  int     methodCounter
,  byte     hasCallback
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MethodAtomicExitEventGen(
   slidingWindowId
  ,  threadId
  ,  methodId
  ,  methodCounter
  ,  hasCallback
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMethodCallbackEnterEventGen ( int slidingWindowId
,  long     threadId
,  int     methodCounter
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MethodCallbackEnterEventGen(
   slidingWindowId
  ,  threadId
  ,  methodCounter
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createMethodCallbackExitEventGen ( int slidingWindowId
,  long     threadId
,  int     methodCounter
,  int     loopId
,  int     runId
,  int     runPosition
) 
{

return new MethodCallbackExitEventGen(
   slidingWindowId
  ,  threadId
  ,  methodCounter
  ,  loopId
  ,  runId
  ,  runPosition
);
  
 

 


}
public static RuntimeEvent createLoopStartEventGen ( int slidingWindowId
,  int     loopId
) 
{

return new LoopStartEventGen(
   slidingWindowId
  ,  loopId
);
  
 

 


}
public static RuntimeEvent createLoopEndEventGen ( int slidingWindowId
,  int     loopId
,  int     status
) 
{

return new LoopEndEventGen(
   slidingWindowId
  ,  loopId
  ,  status
);
  
 

 


}
public static RuntimeEvent createRunStartEventGen ( int slidingWindowId
,  int     loopId
,  int     runId
) 
{

return new RunStartEventGen(
   slidingWindowId
  ,  loopId
  ,  runId
);
  
 

 


}
public static RuntimeEvent createRunEndEventGen ( int slidingWindowId
,  int     loopId
,  int     runId
) 
{

return new RunEndEventGen(
   slidingWindowId
  ,  loopId
  ,  runId
);
  
 

 


}
public static RuntimeEvent createLoopWarningEventGen ( int slidingWindowId
,  int     loopId
,  int     runId
) 
{

return new LoopWarningEventGen(
   slidingWindowId
  ,  loopId
  ,  runId
);
  
 

 


}
	
	
}