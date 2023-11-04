package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;



public class ModeStateObject extends ModeStateAbstract{

	public final long id;

	private static long maxId = 0;
	
	
	

	public ModeStateObject(long lastThreadId) {
		super();
		this.lastThreadId = lastThreadId;
		this.id = getNewId();
	}




	public synchronized static long getNewId() {
		maxId++;
		return maxId;
	}


    @Override
    protected void write(int slidingWindowId, int classOrFieldId, int methodId, int operation,
                         UpdateObjectState updateObjectStateNew, CallbackStatePerThreadForParallelize callbackStatePerThread) {
        updateObjectStateNew.sendStateEvent4Object(slidingWindowId, classOrFieldId, id, methodId, operation, callbackStatePerThread);

    }


    @Override
    protected void writeInitial(int currentSlidingWindowId, int classOrFieldId, long threadIdAtAccess, int methodId,
                                int methodCount, int operation, int slidingWindowIdAtAccess, UpdateObjectState updateObjectStateNew,
                                CallbackStatePerThreadForParallelize callbackStatePerThread) {


        updateObjectStateNew.sendStateEventInitial4Object(currentSlidingWindowId, classOrFieldId, id, threadIdAtAccess,
                methodId, methodCount, operation, slidingWindowIdAtAccess, callbackStatePerThread);
    }
	
}
