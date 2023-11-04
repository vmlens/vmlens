package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.hash.THashSet;

public abstract class ModeStateAbstract {

	public THashSet<Access4State> access4StateSet;
	public long lastThreadId;

	/*
	 * synchronized (state) { final ObjectState objectState = (ObjectState) state;
	 * 
	 * if (objectState.lastThreadId == objectState.LAST_THREAD_ID_NOT_SET ||
	 * objectState.lastThreadId == callbackStatePerThread.threadId) {
	 * objectState.lastThreadId = callbackStatePerThread.threadId;
	 * 
	 * if (objectState.access4StateSet == null) { objectState.access4StateSet = new
	 * THashSet<Access4State>(); }
	 * 
	 * objectState.access4StateSet.add( new
	 * Access4State(callbackStatePerThread.methodCount, methodId, operation,
	 * slidingWindowId));
	 * 
	 * } else if (objectState.lastThreadId > 0) {
	 * 
	 * if (objectState.access4StateSet != null) {
	 * objectState.access4StateSet.forEach(new TObjectProcedure<Access4State>() {
	 * 
	 * public boolean execute(Access4State object) {
	 * callbackStatePerThread.sendEvent.writeStateEventFieldInitialGen(
	 * slidingWindowId, objectState.lastThreadId, classId, object.methodId,
	 * object.methodNumber, object.operation, objectState.getId(),
	 * object.slidingWindowId);
	 * 
	 * return true; }
	 * 
	 * });
	 * 
	 * }
	 * 
	 * objectState.access4StateSet = null;
	 * 
	 * callbackStatePerThread.sendEvent.writeStateEventFieldGen(slidingWindowId,
	 * classId, methodId, callbackStatePerThread.methodCount, operation,
	 * objectState.getId());
	 * 
	 * objectState.lastThreadId = -10L;
	 * 
	 * } else {
	 * callbackStatePerThread.sendEvent.writeStateEventFieldGen(slidingWindowId,
	 * classId, methodId, callbackStatePerThread.methodCount, operation,
	 * objectState.getId()); }
	 */

    public void access(long threadId, final int slidingWindowId, final int classOrFieldId, int methodId,
                       int operation, int methodCount, final UpdateObjectState updateObjectStateNew,
                       final CallbackStatePerThreadForParallelize callbackStatePerThread) {
        synchronized (this) {
            if (lastThreadId == threadId) {

                if (access4StateSet == null) {
                    access4StateSet = new THashSet<Access4State>();
                }

                access4StateSet.add(new Access4State(methodCount, methodId, operation, slidingWindowId));

            } else if (lastThreadId > 0) {

				if (access4StateSet != null) {
					access4StateSet.forEach(new TObjectProcedure<Access4State>() {

						public boolean execute(Access4State object) {
							// callbackStatePerThread.sendEvent.writeStateEventFieldInitialGen(slidingWindowId,
							// lastThreadId, classOrFieldId, object.methodId, object.methodNumber,
							// object.operation, objectState.getId(), object.slidingWindowId);

							writeInitial(slidingWindowId, classOrFieldId, lastThreadId, object.methodId,
									object.methodNumber, object.operation, object.slidingWindowId, updateObjectStateNew,
									callbackStatePerThread);

							return true;
						}

					});

				}

				access4StateSet = null;

				// callbackStatePerThread.sendEvent.writeStateEventFieldGen(slidingWindowId,
				// classId, methodId,
				// callbackStatePerThread.methodCount, operation, objectState.getId());

				write(slidingWindowId, classOrFieldId, methodId, operation, updateObjectStateNew,
						callbackStatePerThread);

				lastThreadId = -10L;

			} else {

				// callbackStatePerThread.sendEvent.writeStateEventFieldGen(slidingWindowId,
				// classId, methodId,
				// callbackStatePerThread.methodCount, operation, objectState.getId());

				write(slidingWindowId, classOrFieldId, methodId, operation, updateObjectStateNew,
						callbackStatePerThread);

			}
		}
	}

    protected abstract void write(int slidingWindowId, int classOrFieldId, int methodId, int operation,
                                  UpdateObjectState updateObjectStateNew, CallbackStatePerThreadForParallelize callbackStatePerThread);

    protected abstract void writeInitial(int slidingWindowId, int classOrFieldId, long lastThreadId, int methodId,
                                         int methodNumber, int operation, int slidingWindowId2, UpdateObjectState updateObjectStateNew,
                                         CallbackStatePerThreadForParallelize callbackStatePerThread);

}
