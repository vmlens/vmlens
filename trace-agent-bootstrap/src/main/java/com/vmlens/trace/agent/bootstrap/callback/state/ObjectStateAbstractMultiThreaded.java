package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public abstract class ObjectStateAbstractMultiThreaded implements ObjectState {

	public int writeEventCount;
	public int readEventCount;
	public final long id;

	private static long maxId = 0;

	public synchronized static long getNewId() {
		maxId++;
		return maxId;
	}

	
	
	
	public ObjectStateAbstractMultiThreaded(int writeEventCount, int readEventCount, long id) {
		super();
		this.writeEventCount = writeEventCount;
		this.readEventCount = readEventCount;
		this.id = id;
	}




	public ObjectStateAbstractMultiThreaded() {
		super();
		this.id = getNewId();
	}

    @Override
    public void sendNonVolatile(long threadId, int slidingWindowId, int fieldId, int methodId, int operation,
                                UpdateObjectState updateObjectStateNew,
                                CallbackStatePerThreadForParallelize callbackStatePerThread) {
        boolean sendEvent = true;


        if (MemoryAccessType.containsWrite(operation)) {
            if (writeEventCount > updateObjectStateNew.maxWriteEvents) {
                sendEvent = false;
            } else {
                writeEventCount++;
            }

			} else {
				if (readEventCount > updateObjectStateNew.maxReadEvents) {
					sendEvent = false;
				} else {
					readEventCount++;
				}
			}
		

		if (sendEvent) {
			updateObjectStateNew.sendEventNonVolatile(callbackStatePerThread, threadId, slidingWindowId, fieldId,
					methodId, operation, id);
		}

	}

}
