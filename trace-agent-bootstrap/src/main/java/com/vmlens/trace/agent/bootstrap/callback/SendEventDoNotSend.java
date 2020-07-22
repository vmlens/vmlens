package com.vmlens.trace.agent.bootstrap.callback;



import com.vmlens.trace.agent.bootstrap.event.gen.SendEvent;

public class SendEventDoNotSend  implements SendEvent{

	public SendEventDoNotSend() {
		super();
		
		
	}


	@Override
	public void writeVolatileAccessEventStaticGen(int slidingWindowId, int programCounter, int order, int fieldId,
			int methodCounter, int methodId, boolean isWrite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeVolatileAccessEventGen(int slidingWindowId, int programCounter, int order, int fieldId,
			int methodCounter, int methodId, int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeVolatileDirectMemoryEventGen(int slidingWindowId, int programCounter, int methodCounter,
			long objectHashCode, int operation, int order) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void writeMonitorEnterEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, int methodId, int position) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void writeMethodEnterEventGen(int slidingWindowId, int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodExitEventGen(int slidingWindowId, int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodEnterSmallThreadIdEventGen(int slidingWindowId, byte smallThreadId, int methodId,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodExitSmallThreadIdEventGen(int slidingWindowId, byte smallThreadId, int methodId,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodEnterShortThreadIdEventGen(int slidingWindowId, short shortThreadId, int methodId,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodExitShortThreadIdEventGen(int slidingWindowId, short shortThreadId, int methodId,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeThreadBeginEventGen(int slidingWindowId, long startedThreadId, int programCounter,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeThreadStoppedEventGen(int slidingWindowId, long stoppedThreadId, int programCounter,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void writeParallizedMethodExitEventGen(int slidingWindowId, int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeParallizedMethodEnterEventGen(int slidingWindowId, int methodId, int methodCounter,
			int parallizeId) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void writeMethodCallbackEnterEventGen(int slidingWindowId, int methodCounter, int programCounter,
			int parallizeId, int order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeMethodCallbackExitEventGen(int slidingWindowId, int methodCounter, int programCounter,
			int parallizeId, int order) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void writeFieldAccessEventGen(int slidingWindowId, int programCounter, int fieldId, int methodCounter,
			int operation, int methodId, boolean stackTraceIncomplete, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeFieldAccessEventStaticGen(int slidingWindowId, int programCounter, int fieldId, int methodCounter,
			int operation, int methodId, boolean stackTraceIncomplete) {
		// TODO Auto-generated method stub
		
	}


	


	@Override
	public void writeVolatileArrayAccessEventGen(int slidingWindowId, int programCounter, int order, long index,
			int methodCounter, int methodId, int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void writeStateEventFieldGen(int slidingWindowId, int classId, int methodId, int methodCounter,
			int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStateEventFieldInitialGen(int slidingWindowId, long threadIdAtEvent, int classId, int methodId,
			int methodCounter, int operation, long objectHashCode, int slidingWindowIdAtEvent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStateEventStaticFieldGen(int slidingWindowId, int fieldId, int methodId, int methodCounter,
			int operation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStateEventStaticFieldInitialGen(int slidingWindowId, long threadIdAtEvent, int fieldId,
			int methodId, int methodCounter, int operation, int slidingWindowIdAtEvent) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void writeMethodAtomicEnterEventGen(int slidingWindowId, int methodId, int methodCounter, byte hasCallback,
			int loopId, int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeMethodAtomicExitEventGen(int slidingWindowId, int methodId, int methodCounter, byte hasCallback,
			int loopId, int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeArrayAccessEventGen(int slidingWindowId, int programCounter, int fieldId, int methodCounter,
			int operation, int methodId, boolean stackTraceIncomplete, long objectHashCode, int position, int classId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStateEventArrayGen(int slidingWindowId, int methodId, int position, int methodCounter,
			int operation, long objectHashCode, int classId) {
		// TODO Auto-generated method stub
		
	}


	


	@Override
	public void writeLockEnterEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, boolean isShared, int lockTyp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeLockExitEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, boolean isShared, int lockTyp) {
		// TODO Auto-generated method stub
		
	}


	


	@Override
	public void writeLoopEndEventGen(int slidingWindowId, int loopId, int status) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeRunStartEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeRunEndEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeLoopWarningEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStampedLockEnterEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, boolean isShared, int lockTyp, int stampedLockMethodId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeStampedLockExitEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, boolean isShared, int lockTyp, int stampedLockMethodId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeLoopStartEventGen(int slidingWindowId, int loopId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeMonitorExitEventGen(int slidingWindowId, int programCounter, int order, int monitorId,
			int methodCounter, int methodId, int position) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveFieldAccessEventGen(int slidingWindowId, int programCounter, int fieldId,
			int methodCounter, int operation, int methodId, boolean stackTraceIncomplete, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveFieldAccessEventStaticGen(int slidingWindowId, int programCounter, int fieldId,
			int methodCounter, int operation, int methodId, boolean stackTraceIncomplete) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveArrayAccessEventGen(int slidingWindowId, int programCounter, int fieldId,
			int methodCounter, int operation, int methodId, boolean stackTraceIncomplete, long objectHashCode,
			int position, int classId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveVolatileAccessEventStaticGen(int slidingWindowId, int programCounter, int order,
			int fieldId, int methodCounter, int methodId, boolean isWrite) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveVolatileAccessEventGen(int slidingWindowId, int programCounter, int order,
			int fieldId, int methodCounter, int methodId, int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveVolatileArrayAccessEventGen(int slidingWindowId, int programCounter, int order,
			long index, int methodCounter, int methodId, int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveVolatileDirectMemoryEventGen(int slidingWindowId, int programCounter,
			int methodCounter, long objectHashCode, int operation, int order) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveLockEnterEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, boolean isShared, int lockTyp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveLockExitEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, boolean isShared, int lockTyp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStampedLockEnterEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, boolean isShared, int lockTyp, int stampedLockMethodId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStampedLockExitEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, boolean isShared, int lockTyp, int stampedLockMethodId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMonitorEnterEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, int methodId, int position) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMonitorExitEventGen(int slidingWindowId, int programCounter, int order,
			int monitorId, int methodCounter, int methodId, int position) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodEnterEventGen(int slidingWindowId, int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodExitEventGen(int slidingWindowId, int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveParallizedMethodEnterEventGen(int slidingWindowId, int methodId,
			int methodCounter, int parallizeId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveParallizedMethodExitEventGen(int slidingWindowId, int methodId,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodEnterSmallThreadIdEventGen(int slidingWindowId, byte smallThreadId,
			int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodExitSmallThreadIdEventGen(int slidingWindowId, byte smallThreadId,
			int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodEnterShortThreadIdEventGen(int slidingWindowId, short shortThreadId,
			int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodExitShortThreadIdEventGen(int slidingWindowId, short shortThreadId,
			int methodId, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveThreadBeginEventGen(int slidingWindowId, long startedThreadId, int programCounter,
			int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveThreadStoppedEventGen(int slidingWindowId, long stoppedThreadId,
			int programCounter, int methodCounter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodAtomicEnterEventGen(int slidingWindowId, int methodId, int methodCounter,
			byte hasCallback, int loopId, int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodAtomicExitEventGen(int slidingWindowId, int methodId, int methodCounter,
			byte hasCallback, int loopId, int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodCallbackEnterEventGen(int slidingWindowId, int methodCounter, int loopId,
			int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveMethodCallbackExitEventGen(int slidingWindowId, int methodCounter, int loopId,
			int runId, int runPosition) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveLoopStartEventGen(int slidingWindowId, int loopId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveLoopEndEventGen(int slidingWindowId, int loopId, int status) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveRunStartEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveRunEndEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveLoopWarningEventGen(int slidingWindowId, int loopId, int runId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStateEventFieldGen(int slidingWindowId, int classId, int methodId,
			int methodCounter, int operation, long objectHashCode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStateEventFieldInitialGen(int slidingWindowId, long threadIdAtEvent, int classId,
			int methodId, int methodCounter, int operation, long objectHashCode, int slidingWindowIdAtEvent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStateEventStaticFieldGen(int slidingWindowId, int fieldId, int methodId,
			int methodCounter, int operation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStateEventStaticFieldInitialGen(int slidingWindowId, long threadIdAtEvent,
			int fieldId, int methodId, int methodCounter, int operation, int slidingWindowIdAtEvent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeWithoutInterleaveStateEventArrayGen(int slidingWindowId, int methodId, int position,
			int methodCounter, int operation, long objectHashCode, int classId) {
		// TODO Auto-generated method stub
		
	}



	


	




}
