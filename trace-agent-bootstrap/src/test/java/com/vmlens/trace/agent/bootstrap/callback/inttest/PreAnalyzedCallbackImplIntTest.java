package com.vmlens.trace.agent.bootstrap.callback.inttest;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.inttest.util.CallbackTestContainer;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.*;
import com.vmlens.trace.agent.bootstrap.lock.LockEnter;
import com.vmlens.trace.agent.bootstrap.lock.LockTypes;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.*;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.After.after;
import static com.vmlens.trace.agent.bootstrap.callback.inttest.util.CallbackTestContainer.TEST_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.EndAtomicOperation.endAtomicOperation;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.StartAtomicOperationWithNewThread.startAtomicOperationWithNewThread;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PreAnalyzedCallbackImplIntTest {

    private static final int IN_METHOD_ID = 97;
    private static final int POSITION = 2;
    private static final int METHOD_ID = 5;
    private static final Object OBJECT = new Object();
    private static final long UNDERLYING_OBJECT_HASH_CODE = 5678L;

    @Test
    public void threadStart() {
        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);

        // Expected
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(OBJECT);
        ThreadStartEvent threadStartEvent = new ThreadStartEvent();
        threadStartEvent.setThreadIndex(TEST_THREAD_INDEX);
        threadStartEvent.setInMethodIdAndPosition(IN_METHOD_ID, POSITION,callbackTestContainer.readWriteLockMap());

        // When
        executeCalls(callbackTestContainer,ThreadStartStrategy.SINGLETON);

        // Then
        assertThat(callbackTestContainer.runActions().size(), is(2));
        assertThat(callbackTestContainer.runActions().get(0), is(
                startAtomicOperationWithNewThread(runnableOrThreadWrapper)));
        assertThat(callbackTestContainer.runActions().get(1), is(
                endAtomicOperation(threadStartEvent)));

    }

    @Test
    public void lock() {
        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        callbackTestContainer.readWriteLockMap().addUnderlying(System.identityHashCode(OBJECT),UNDERLYING_OBJECT_HASH_CODE);

        // Expected
        LockEnterEvent lockEnterEvent = new LockEnterEvent(LockTypes.WRITE_LOCK, OBJECT);
        lockEnterEvent.setThreadIndex(TEST_THREAD_INDEX);
        lockEnterEvent.setObjectHashCode(UNDERLYING_OBJECT_HASH_CODE);
        lockEnterEvent.setMethodId(IN_METHOD_ID);
        lockEnterEvent.setBytecodePosition(POSITION);

        // When
        executeCalls(callbackTestContainer,new LockMethodStrategy(new LockEnter(), LockTypes.WRITE_LOCK));

        // Then
        assertThat(callbackTestContainer.runActions().size(), is(1));
        assertThat(callbackTestContainer.runActions().get(0), is(
                after(lockEnterEvent)));
    }

    @Test
    public void getReadLock() {
        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        callbackTestContainer.setStrategyPreAnalyzed(METHOD_ID, new GetReadWriteLockMethodStrategy());
        Object returnValue = new Object();


        // When
        //  Object returnValue,Object object, int methodId
        callbackTestContainer.preAnalyzedCallbackImpl().methodExitObjectReturn(returnValue,OBJECT, METHOD_ID);

        // Then
        assertThat(callbackTestContainer.readWriteLockMap().getUnderlying(System.identityHashCode(returnValue)),
                is(((long)System.identityHashCode(OBJECT))));

    }

    @Test
    public void atomicNonBlocking() {
        // Expected
        AtomicNonBlockingEvent atomicNonBlockingEvent = new AtomicNonBlockingEvent(MemoryAccessType.IS_READ_WRITE,OBJECT);
        atomicNonBlockingEvent.setThreadIndex(TEST_THREAD_INDEX);
        atomicNonBlockingEvent.setObjectHashCode(System.identityHashCode(OBJECT));
        atomicNonBlockingEvent.setMethodId(IN_METHOD_ID);
        atomicNonBlockingEvent.setBytecodePosition(POSITION);
        atomicNonBlockingEvent.setAtomicMethodId(METHOD_ID);

        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);

        // When
        executeCalls(callbackTestContainer,new NonBlockingStrategy(MemoryAccessType.IS_READ_WRITE));

        // Then
        assertThat(callbackTestContainer.runActions().size(), is(1));
        assertThat(callbackTestContainer.runActions().get(0), is(
                after(atomicNonBlockingEvent)));
    }

    @Test
    public void atomicMethodWithLock() {
        // Expected
        AtomicReadWriteLockEnterEvent atomicEnter = new AtomicReadWriteLockEnterEvent(LockTypes.WRITE_LOCK);
        atomicEnter.setThreadIndex(TEST_THREAD_INDEX);
        atomicEnter.setObjectHashCode(System.identityHashCode(OBJECT));
        atomicEnter.setMethodId(IN_METHOD_ID);
        atomicEnter.setBytecodePosition(POSITION);
        atomicEnter.setAtomicMethodId(METHOD_ID);

        AtomicReadWriteLockExitEvent atomicExit = new AtomicReadWriteLockExitEvent(LockTypes.WRITE_LOCK,OBJECT);
        atomicExit.setThreadIndex(TEST_THREAD_INDEX);
        atomicExit.setObjectHashCode(System.identityHashCode(OBJECT));
        atomicExit.setMethodId(IN_METHOD_ID);
        atomicExit.setBytecodePosition(POSITION);
        atomicExit.setAtomicMethodId(METHOD_ID);

        // Given
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);

        // When
        executeCalls(callbackTestContainer,new MethodWithLockStrategy(LockTypes.WRITE_LOCK));

        // Then
        assertThat(callbackTestContainer.runActions().size(), is(2));
        assertThat(callbackTestContainer.runActions().get(0), is(
                after(atomicEnter)));
        assertThat(callbackTestContainer.runActions().get(1), is(
                after(atomicExit)));
    }

    private void executeCalls(CallbackTestContainer callbackTestContainer,
                                               StrategyPreAnalyzed strategyPreAnalyzed) {

        callbackTestContainer.setStrategyPreAnalyzed(METHOD_ID, strategyPreAnalyzed);

        // When
        callbackTestContainer.methodCallbackImpl().beforeMethodCall(IN_METHOD_ID, POSITION);
        callbackTestContainer.preAnalyzedCallbackImpl().methodEnter(OBJECT, METHOD_ID);
        callbackTestContainer.preAnalyzedCallbackImpl().methodExit(OBJECT, METHOD_ID);
        callbackTestContainer.methodCallbackImpl().afterMethodCall(IN_METHOD_ID, POSITION);

    }

}
