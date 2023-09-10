package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.callback.state.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


/*
 * ObjectState
    SingleThreaded
    AbstractMultiThreaded
    	 VolatileState
    	MultiThreaded
    SharedState
     

null -> SingleThreaded
null -> MultiThreaded
null -> VolatileState


 Struktur:
 	State state = getState
 		if( state == null )
 		{
           state  = setzeSingleThreaded();
        }    		
 		else if( state instanceof SingleState )
 		{
 		  // threadId prüfen und möglicherweise nulti threaded setzen
 		  state = setzeMultiThreaded()
 		}
 		// ansonsten schon multi threaded
 		
    state.process() bzw send event		
    potentialSendEvent() // da sync block wenn notwendig


  setzeSingleThreaded()
		wenn failure
			wenn multithreaded alles o.k.
			wenn auch single threade
				setze multithreaded
				
		setze multithreaded
			wenn falure 
				wenn single threaded retry.


 konfigurierbare anzahl an events


	object state logik
			ähnlich wie hier
	 
	 
	volatile logik	 
			zum testen auch in diese klasse



if(callbackStatePerThread.mode.isInterleave())
		{
			updateObjectState.nonVolatileAccess(callbackStatePerThread, orig, (ObjectState) c, fieldId, methodId, operation,  CallbackState.traceFields(callbackStatePerThread) );
		}
		else if(callbackStatePerThread.mode.isState())
		{
			ModeStateFieldAccess.access( callbackStatePerThread, orig, c, methodId, operation );
		}
		

 */

public class UpdateObjectState {

	public final int maxReadEvents;
	public final int maxWriteEvents;

	private static final sun.misc.Unsafe UNSAFE;

	static {

		try {
			Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
			unsafeConstructor.setAccessible(true);
			UNSAFE = unsafeConstructor.newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static long getFieldOffset(Class cl) {
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		try {
			return getFieldOffset(cl, "_pAnarsoft_");
		}
		finally {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}
		
		
	}

	protected static long getFieldOffset(Class cl, String name) {
		try {
			Field f = cl.getDeclaredField(name);
			return UNSAFE.objectFieldOffset(f);

		} catch (NoSuchFieldException e) {
			return -1;
		} catch (SecurityException e) {
			return -1;
		}

	}

	public UpdateObjectState(int maxReadEvents, int maxWriteEvents) {
		super();
		this.maxReadEvents = maxReadEvents;
		this.maxWriteEvents = maxWriteEvents;
	}

	void stateAccess(Object obj, long offset, long threadId, int slidingWindowId, int classOrFieldId, int methodId,
			int operation, int methodCount, CallbackStatePerThread callbackStatePerThread) {
		ModeStateObject current = getModeStateObject(obj, offset);

		if (current == null) {
			current = new ModeStateObject(threadId);

			if (!setModeStateObjectIfNotThere(obj, offset, current)) {
				current = getModeStateObject(obj, offset);
			}
		}

		/*
		 * protected void access(long threadId, final int slidingWindowId, final int
		 * classOrFieldId, int methodId, int operation, int methodCount, final
		 * UpdateObjectStateNew updateObjectStateNew, final CallbackStatePerThread
		 * callbackStatePerThread) {
		 */

		current.access(threadId, slidingWindowId, classOrFieldId, methodId, operation, methodCount, this,
				callbackStatePerThread);

	}

	private boolean setModeStateObjectIfNotThere(Object obj, long offset, ModeStateObject newModeStateObject) {
		return UNSAFE.compareAndSwapObject(obj, offset, null, newModeStateObject);
	}

	private ModeStateObject getModeStateObject(Object obj, long offset) {
		return (ModeStateObject) UNSAFE.getObjectVolatile(obj, offset);
	}

	void volatileAccess(Object obj, long offset, int fieldId, int methodId,
			int operation, CallbackStatePerThread callbackStatePerThread) {
		ObjectState current = getState(obj, offset);

		ObjectStateVolatile objectStateVolatile = null;

		if (!(current instanceof ObjectStateVolatile)) {
			objectStateVolatile = setVolatile(obj, offset, current);
		} else {
			objectStateVolatile = (ObjectStateVolatile) current;
		}

		objectStateVolatile.sendVolatile( fieldId, methodId, operation, this,
				callbackStatePerThread);

	}

	void nonVolatileAccess(boolean isInInterleaveLoop, Object obj, long offset, long threadId, int slidingWindowId,
			int fieldId, int methodId, int operation, CallbackStatePerThread callbackStatePerThread) {

		ObjectState current = getState(obj, offset);
		if (isInInterleaveLoop) {
			if (!(current instanceof ObjectStateAbstractMultiThreaded)) {
				current = setMultiThreaded(obj, offset, current);
			}

		} else {
			if (current == null) {
				current = setSingleThreaded(obj, offset, threadId, current);
			} else if (current instanceof ObjectStateSingleThreaded) {
				ObjectStateSingleThreaded st = (ObjectStateSingleThreaded) current;

				if (st.threadId != threadId) {
					current = setMultiThreaded(obj, offset, current);
				}

			}

		}

		current.sendNonVolatile(threadId, slidingWindowId, fieldId, methodId, operation, this, callbackStatePerThread);

	}

	private ObjectState getState(Object obj, long offset) {
		return (ObjectState) UNSAFE.getObjectVolatile(obj, offset);
	}

	/*
	 * 
	 * static final <K,V> boolean casTabAt(Node<K,V>[] tab, int i, Node<K,V> c,
	 * Node<K,V> v) { return U.compareAndSwapObject(tab, ((long)i << ASHIFT) +
	 * ABASE, c, v);
	 */

	private ObjectState setMultiThreaded(Object obj, long offset, ObjectState state) {
		ObjectState current = state;

		while (!(current instanceof ObjectStateAbstractMultiThreaded)) {
			ObjectStateMultiThreaded n = new ObjectStateMultiThreaded();
			if (UNSAFE.compareAndSwapObject(obj, offset, current, n)) {
				return n;
			} else {
				current = getState(obj, offset);
			}
		}
		return current;
	}

	private ObjectState setSingleThreaded(Object obj, long offset, long threadId, ObjectState state) {
		ObjectState n = new ObjectStateSingleThreaded(threadId);
		if (UNSAFE.compareAndSwapObject(obj, offset, state, n)) {
			return n;
		} else {
			return setMultiThreaded(obj, offset, getState(obj, offset));

		}
	}

	private ObjectStateVolatile setVolatile(Object obj, long offset, Object state) {
		Object current = state;

		while (!(current instanceof ObjectStateVolatile)) {
			ObjectStateVolatile n = null;

			if (current instanceof ObjectStateMultiThreaded) {
				// ObjectStateVolatile(int writeEventCount, int readEventCount, long id)
				ObjectStateMultiThreaded c = (ObjectStateMultiThreaded) current;
				n = new ObjectStateVolatile(c.writeEventCount, c.readEventCount, c.id);
			} else {
				n = new ObjectStateVolatile();
			}

			new ObjectStateVolatile();

			if (UNSAFE.compareAndSwapObject(obj, offset, current, n)) {
				return n;
			} else {
				current = getState(obj, offset);
			}
		}
		return (ObjectStateVolatile) current;
	}

	
	
	
	public void sendEventVolatile(CallbackStatePerThread callbackStatePerThread,int order,
			int fieldId, int methodId, int operation, long objectId) {
		// Fixme Callback
//		callbackStatePerThread.sendEvent.writeVolatileAccessEventGen(
//				CallbackState.traceSyncStatements(callbackStatePerThread), callbackStatePerThread.programCount,
//				order, fieldId, callbackStatePerThread.methodCount, methodId, operation,objectId);
	}

	public void parallizeFacadeBeforeFieldAccessVolatile(long id, int fieldId, int operation,
			CallbackStatePerThread callbackStatePerThread) {
// Fixme Callback
		//		ParallelizeFacade.beforeFieldAccessVolatile(callbackStatePerThread, id, fieldId, operation);
	}

	/*
	 * void writeFieldAccessEventGen (int slidingWindowId , int programCounter , int
	 * fieldId , int methodCounter , int operation , int methodId , boolean
	 * stackTraceIncomplete , long objectHashCode );
	 */

	public void sendEventNonVolatile(CallbackStatePerThread callbackStatePerThread, long threadId, int slidingWindowId,
			int fieldId, int methodId, int operation, long objectId) {
		//	Fixme Callback
		//		callbackStatePerThread.sendEvent.writeFieldAccessEventGen(slidingWindowId, callbackStatePerThread.programCount,
//				fieldId, callbackStatePerThread.methodCount, operation, methodId,
//				callbackStatePerThread.isStackTraceIncomplete(), objectId);
	}

	public void sendStateEvent4Object(int slidingWindowId, int classOrFieldId, long id, int methodId, int operation,
			CallbackStatePerThread callbackStatePerThread) {
//		callbackStatePerThread.sendEvent.writeStateEventFieldGen(slidingWindowId, classOrFieldId, methodId,
//				callbackStatePerThread.methodCount, operation, id);
	}

	public void sendStateEventInitial4Object(int currentSlidingWindowId, int classOrFieldId, long id, long lastThreadId,
			int methodId, int methodCount, int operation, int slidingWindowIdAtAccess,
			CallbackStatePerThread callbackStatePerThread) {
//		callbackStatePerThread.sendEvent.writeStateEventFieldInitialGen(currentSlidingWindowId, lastThreadId,
//				classOrFieldId, methodId, methodCount, operation, id, slidingWindowIdAtAccess);

	}

}
