package com.anarsoft.trace.agent.runtime.inttest;

import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedAtomicReadWriteLock;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedSpecificMethods;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedAtomicNonBlocking;
import com.anarsoft.trace.agent.runtime.classtransformer.RunTestClassTransformer;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;

import java.io.IOException;


import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_WRITE_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.EXIT_REENTRANT_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.NonBlockingMethod.NON_BLOCKING_WRITE;

public class PreAnalyzedIntTest {

    private final String CLASS_NAME = "com/vmlens/test/guineapig/PreAnalyzedGuineaPig";

    //@Test
    public void atomicNonBlocking() throws IOException {
        // Given
        CallbackInNonBlockingMethod callbackInNonBlockingMethod = new CallbackInNonBlockingMethod(CLASS_NAME,"method", "()Ljava/lang/Object;", MemoryAccessType.IS_WRITE);
        CallbackInNonBlockingMethod[] callbak = new  CallbackInNonBlockingMethod[1];
        callbak[0] = callbackInNonBlockingMethod;
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(NON_BLOCKING_WRITE);
        preAnalyzedMethodBuilder.withCallback(callbak);
        runTest(PreAnalyzedAtomicNonBlocking.SINGLETON, preAnalyzedMethodBuilder.build(),"/methodCall.txt");
    }

    @Test
    public void atomicReadWriteLock() throws IOException {
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(METHOD_WITH_READ_LOCK);
        runTest(PreAnalyzedAtomicReadWriteLock.SINGLETON, preAnalyzedMethodBuilder.build(), "/noMethodCall.txt");
    }

    @Test
    public void lock() throws IOException {
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(EXIT_REENTRANT_LOCK);
        runTest(PreAnalyzedSpecificMethods.SINGLETON, preAnalyzedMethodBuilder.build(), "/noMethodCall.txt");
    }

    @Test
    public void getReadWriteLock() throws IOException {
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(GET_READ_WRITE_LOCK);
        runTest(PreAnalyzedSpecificMethods.SINGLETON, preAnalyzedMethodBuilder.build(), "/noMethodCall.txt");
    }

    @Test
    public void warningWhenMethodNotKnown() {

    }



    private void runTest(ClassType classType, PreAnalyzedMethod preAnalyzedMethod, String expected) throws IOException {
        PreAnalyzedMethod[] preAnalyzedMethods = new PreAnalyzedMethod[1];
        preAnalyzedMethods[0] = preAnalyzedMethod;
        PackageOrClass atomicNonBlocking = new PackageOrClass(CLASS_NAME, classType,preAnalyzedMethods);
        RunTestClassTransformer test = RunTestClassTransformer.create(TLinkableWrapper.singleton(atomicNonBlocking));
        test.runTest(CLASS_NAME,expected);
    }


}
