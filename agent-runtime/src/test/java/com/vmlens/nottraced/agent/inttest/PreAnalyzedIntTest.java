package com.vmlens.nottraced.agent.inttest;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedSpecificMethods;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;
import com.vmlens.nottraced.agent.classtransformer.RunTestClassTransformer;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;

import java.io.IOException;


import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_WRITE_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.LockMethod.EXIT_REENTRANT_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;

public class PreAnalyzedIntTest {

    private final String CLASS_NAME = "com/vmlens/test/guineapig/PreAnalyzedGuineaPig";



    //@Test
    public void atomicReadWriteLock() throws IOException {
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(METHOD_WITH_READ_LOCK);
        runTest(PreAnalyzedAllMethods.SINGLETON, preAnalyzedMethodBuilder.build(), "/noMethodCall.txt");
    }

    //@Test
    public void lock() throws IOException {
        PreAnalyzedMethodBuilder preAnalyzedMethodBuilder = new PreAnalyzedMethodBuilder(EXIT_REENTRANT_LOCK);
        runTest(PreAnalyzedSpecificMethods.SINGLETON, preAnalyzedMethodBuilder.build(), "/noMethodCall.txt");
    }

    //@Test
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
