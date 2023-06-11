package com.anarsoft.trace.agent.runtime.waitPoints;

import com.anarsoft.trace.agent.runtime.filter.FilterState;
import com.anarsoft.trace.agent.runtime.transformer.MethodLockTypeEnum;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.mode.AgentMode;
import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;

/*

vmlens.excludeFromTrace
vmlens.doNotTraceIn
vmlens.onlyTraceIn
 */

public class FilterList {
	private final FilterState excludeFromTrace;
	private final FilterState excludeThroughDoNotTraceIn;
	public final FilterWithAnnotations doNotTraceIn;



	public final FilterState onlyTraceIn;

	public final AgentMode agentMode;

	public FilterList(FilterState excludeFromTrace, FilterState excludeThroughDoNotTraceIn, FilterState doNotTraceIn,
			FilterState onlyTraceIn, AgentMode agentMode) {
		super();
		this.excludeFromTrace = excludeFromTrace;
		this.excludeThroughDoNotTraceIn = excludeThroughDoNotTraceIn;
		this.doNotTraceIn = new FilterWithAnnotations(doNotTraceIn);
		this.onlyTraceIn = onlyTraceIn;

		this.agentMode = agentMode;

	}

	

	public boolean changeClass(String name) {
		
		if(   name.startsWith("java/util/concurrent/CompletableFuture"))
		{
			return false;
		}
		
		return true;
	}

	public boolean traceArray(String owner, String name) {
		if (agentMode.processFields()) {
			return traceInternal(owner + '/' + name);
		} else {
			return false;
		}

	}

	public boolean traceField(String owner, String name) {
		if (agentMode.processFields()) {
			return true;//traceInternal(owner + '/' + name);
		} else {
			return false;
		}
	}

	public boolean traceMethod(String owner, String name) {
		return traceInternal(owner + '/' + name);
	}

	private boolean traceInternal(String completeName) {
		return excludeFromTrace.take(completeName) && excludeThroughDoNotTraceIn.take(completeName);
		// return true;
	}

	public boolean onlyTraceWhenInMethod(String owner, String name) {
		// if( in.startsWith("com/vmlens/test"))
		// {
		// System.out.println("onlyTraceIn " + in + " " + this.onlyTraceIn.take(in) );
		// }
		//
		//
		return this.onlyTraceIn.take(owner + '/' + name);
	}

	public boolean doNotTraceWhenInMethod(String className, String methodName, String desc) {
		MethodInClassIdentifier id = new MethodInClassIdentifier(className, methodName, desc);

		if (doNotTraceIn.takeFromAnnotation(id)) {
			return true;
		}

		return this.doNotTraceIn.takeFromConfig(className + '/' + methodName);
	}

	// public boolean createMethodParallized(String owner,String name)
	// {
	//// System.out.println( in.replace('/', '.') + " " +
	// this.methodParallized.take(in));
	//
	//
	// return this.methodParallized.take(owner + '/' + name );
	// }

	public static volatile THashMap<String, AtomicMethodWithCallback[]> atomicClasses2AtomicMethodWithCallbackArray;

	public AtomicMethodWithCallback[] classIsAtomic(String className) {

		if (atomicClasses2AtomicMethodWithCallbackArray == null) {

			return null;
			// atomicClasses2AtomicMethodWithCallbackArray =
			// LoadAtomicClassesFromClasspath.load();

		}

		return atomicClasses2AtomicMethodWithCallbackArray.get(className);

	}

	private MethodLockTypeEnum internalMethodLockType(String methodName, boolean isShared) {

		//
		//

		if (methodName.equals("lock")) {
			if (isShared) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			} else {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

		}

		if (methodName.equals("lockInterruptibly")) {
			if (isShared) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			} else {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

		}

		if (methodName.equals("tryLock")) {
			if (isShared) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			} else {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

		}

		if (methodName.equals("unlock")) {
			if (isShared) {
				return MethodLockTypeEnum.SHARED_LOCK_EXIT;
			} else {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_EXIT;
			}
		}

		return MethodLockTypeEnum.NONE;
	}

	public MethodLockTypeEnum methodLockType(String className, String methodName) {

		if (className.equals("java/util/concurrent/locks/ReentrantLock")) {
			return internalMethodLockType(methodName, false);
		}

		if (className.equals("java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock")) {
			return internalMethodLockType(methodName, false);
		}

		if (className.equals("java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock")) {
			return internalMethodLockType(methodName, true);
		}

		if (className.equals("java/util/concurrent/locks/StampedLock")) {
			if (methodName.equals("readLock")) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			}
			if (methodName.equals("readLockInterruptibly")) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			}
			if (methodName.equals("tryOptimisticRead")) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			}
			if (methodName.equals("tryReadLock")) {
				return MethodLockTypeEnum.SHARED_LOCK_ENTER;
			}

			if (methodName.equals("tryWriteLock")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

			if (methodName.equals("writeLock")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

			if (methodName.equals("writeLockInterruptibly")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_ENTER;
			}

			if (methodName.equals("tryUnlockRead")) {
				return MethodLockTypeEnum.SHARED_LOCK_EXIT;
			}

			if (methodName.equals("unlockRead")) {
				return MethodLockTypeEnum.SHARED_LOCK_EXIT;
			}

			if (methodName.equals("tryUnlockWrite")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_EXIT;
			}
			if (methodName.equals("unlock")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_EXIT;
			}
			if (methodName.equals("unlockWrite")) {
				return MethodLockTypeEnum.EXCLUSIVE_LOCK_EXIT;
			}

		}

		return MethodLockTypeEnum.NONE;

	}

}
