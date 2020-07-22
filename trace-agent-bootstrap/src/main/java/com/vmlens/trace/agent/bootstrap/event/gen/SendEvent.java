package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.*;
import java.nio.ByteBuffer;

public interface SendEvent {
	
void writeFieldAccessEventGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
, long     objectHashCode
);

void writeWithoutInterleaveFieldAccessEventGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
, long     objectHashCode
);

void writeFieldAccessEventStaticGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
);

void writeWithoutInterleaveFieldAccessEventStaticGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
);

void writeArrayAccessEventGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
, long     objectHashCode
, int     position
, int     classId
);

void writeWithoutInterleaveArrayAccessEventGen (int slidingWindowId
, int     programCounter
, int     fieldId
, int     methodCounter
, int     operation
, int     methodId
, boolean     stackTraceIncomplete
, long     objectHashCode
, int     position
, int     classId
);

void writeVolatileAccessEventStaticGen (int slidingWindowId
, int     programCounter
, int     order
, int     fieldId
, int     methodCounter
, int     methodId
, boolean     isWrite
);

void writeWithoutInterleaveVolatileAccessEventStaticGen (int slidingWindowId
, int     programCounter
, int     order
, int     fieldId
, int     methodCounter
, int     methodId
, boolean     isWrite
);

void writeVolatileAccessEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     fieldId
, int     methodCounter
, int     methodId
, int     operation
, long     objectHashCode
);

void writeWithoutInterleaveVolatileAccessEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     fieldId
, int     methodCounter
, int     methodId
, int     operation
, long     objectHashCode
);

void writeVolatileArrayAccessEventGen (int slidingWindowId
, int     programCounter
, int     order
, long     index
, int     methodCounter
, int     methodId
, int     operation
, long     objectHashCode
);

void writeWithoutInterleaveVolatileArrayAccessEventGen (int slidingWindowId
, int     programCounter
, int     order
, long     index
, int     methodCounter
, int     methodId
, int     operation
, long     objectHashCode
);

void writeVolatileDirectMemoryEventGen (int slidingWindowId
, int     programCounter
, int     methodCounter
, long     objectHashCode
, int     operation
, int     order
);

void writeWithoutInterleaveVolatileDirectMemoryEventGen (int slidingWindowId
, int     programCounter
, int     methodCounter
, long     objectHashCode
, int     operation
, int     order
);

void writeLockEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
);

void writeWithoutInterleaveLockEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
);

void writeLockExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
);

void writeWithoutInterleaveLockExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
);

void writeStampedLockEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
, int     stampedLockMethodId
);

void writeWithoutInterleaveStampedLockEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
, int     stampedLockMethodId
);

void writeStampedLockExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
, int     stampedLockMethodId
);

void writeWithoutInterleaveStampedLockExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, boolean     isShared
, int     lockTyp
, int     stampedLockMethodId
);

void writeMonitorEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, int     methodId
, int     position
);

void writeWithoutInterleaveMonitorEnterEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, int     methodId
, int     position
);

void writeMonitorExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, int     methodId
, int     position
);

void writeWithoutInterleaveMonitorExitEventGen (int slidingWindowId
, int     programCounter
, int     order
, int     monitorId
, int     methodCounter
, int     methodId
, int     position
);

void writeMethodEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeMethodExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeParallizedMethodEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, int     parallizeId
);

void writeWithoutInterleaveParallizedMethodEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, int     parallizeId
);

void writeParallizedMethodExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveParallizedMethodExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
);

void writeMethodEnterSmallThreadIdEventGen (int slidingWindowId
, byte     smallThreadId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodEnterSmallThreadIdEventGen (int slidingWindowId
, byte     smallThreadId
, int     methodId
, int     methodCounter
);

void writeMethodExitSmallThreadIdEventGen (int slidingWindowId
, byte     smallThreadId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodExitSmallThreadIdEventGen (int slidingWindowId
, byte     smallThreadId
, int     methodId
, int     methodCounter
);

void writeMethodEnterShortThreadIdEventGen (int slidingWindowId
, short     shortThreadId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodEnterShortThreadIdEventGen (int slidingWindowId
, short     shortThreadId
, int     methodId
, int     methodCounter
);

void writeMethodExitShortThreadIdEventGen (int slidingWindowId
, short     shortThreadId
, int     methodId
, int     methodCounter
);

void writeWithoutInterleaveMethodExitShortThreadIdEventGen (int slidingWindowId
, short     shortThreadId
, int     methodId
, int     methodCounter
);

void writeThreadBeginEventGen (int slidingWindowId
, long     startedThreadId
, int     programCounter
, int     methodCounter
);

void writeWithoutInterleaveThreadBeginEventGen (int slidingWindowId
, long     startedThreadId
, int     programCounter
, int     methodCounter
);

void writeThreadStoppedEventGen (int slidingWindowId
, long     stoppedThreadId
, int     programCounter
, int     methodCounter
);

void writeWithoutInterleaveThreadStoppedEventGen (int slidingWindowId
, long     stoppedThreadId
, int     programCounter
, int     methodCounter
);

void writeMethodAtomicEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, byte     hasCallback
, int     loopId
, int     runId
, int     runPosition
);

void writeWithoutInterleaveMethodAtomicEnterEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, byte     hasCallback
, int     loopId
, int     runId
, int     runPosition
);

void writeMethodAtomicExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, byte     hasCallback
, int     loopId
, int     runId
, int     runPosition
);

void writeWithoutInterleaveMethodAtomicExitEventGen (int slidingWindowId
, int     methodId
, int     methodCounter
, byte     hasCallback
, int     loopId
, int     runId
, int     runPosition
);

void writeMethodCallbackEnterEventGen (int slidingWindowId
, int     methodCounter
, int     loopId
, int     runId
, int     runPosition
);

void writeWithoutInterleaveMethodCallbackEnterEventGen (int slidingWindowId
, int     methodCounter
, int     loopId
, int     runId
, int     runPosition
);

void writeMethodCallbackExitEventGen (int slidingWindowId
, int     methodCounter
, int     loopId
, int     runId
, int     runPosition
);

void writeWithoutInterleaveMethodCallbackExitEventGen (int slidingWindowId
, int     methodCounter
, int     loopId
, int     runId
, int     runPosition
);

void writeLoopStartEventGen (int slidingWindowId
, int     loopId
);

void writeWithoutInterleaveLoopStartEventGen (int slidingWindowId
, int     loopId
);

void writeLoopEndEventGen (int slidingWindowId
, int     loopId
, int     status
);

void writeWithoutInterleaveLoopEndEventGen (int slidingWindowId
, int     loopId
, int     status
);

void writeRunStartEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeWithoutInterleaveRunStartEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeRunEndEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeWithoutInterleaveRunEndEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeLoopWarningEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeWithoutInterleaveLoopWarningEventGen (int slidingWindowId
, int     loopId
, int     runId
);

void writeStateEventFieldGen (int slidingWindowId
, int     classId
, int     methodId
, int     methodCounter
, int     operation
, long     objectHashCode
);

void writeWithoutInterleaveStateEventFieldGen (int slidingWindowId
, int     classId
, int     methodId
, int     methodCounter
, int     operation
, long     objectHashCode
);

void writeStateEventFieldInitialGen (int slidingWindowId
, long     threadIdAtEvent
, int     classId
, int     methodId
, int     methodCounter
, int     operation
, long     objectHashCode
, int     slidingWindowIdAtEvent
);

void writeWithoutInterleaveStateEventFieldInitialGen (int slidingWindowId
, long     threadIdAtEvent
, int     classId
, int     methodId
, int     methodCounter
, int     operation
, long     objectHashCode
, int     slidingWindowIdAtEvent
);

void writeStateEventStaticFieldGen (int slidingWindowId
, int     fieldId
, int     methodId
, int     methodCounter
, int     operation
);

void writeWithoutInterleaveStateEventStaticFieldGen (int slidingWindowId
, int     fieldId
, int     methodId
, int     methodCounter
, int     operation
);

void writeStateEventStaticFieldInitialGen (int slidingWindowId
, long     threadIdAtEvent
, int     fieldId
, int     methodId
, int     methodCounter
, int     operation
, int     slidingWindowIdAtEvent
);

void writeWithoutInterleaveStateEventStaticFieldInitialGen (int slidingWindowId
, long     threadIdAtEvent
, int     fieldId
, int     methodId
, int     methodCounter
, int     operation
, int     slidingWindowIdAtEvent
);

void writeStateEventArrayGen (int slidingWindowId
, int     methodId
, int     position
, int     methodCounter
, int     operation
, long     objectHashCode
, int     classId
);

void writeWithoutInterleaveStateEventArrayGen (int slidingWindowId
, int     methodId
, int     position
, int     methodCounter
, int     operation
, long     objectHashCode
, int     classId
);

}