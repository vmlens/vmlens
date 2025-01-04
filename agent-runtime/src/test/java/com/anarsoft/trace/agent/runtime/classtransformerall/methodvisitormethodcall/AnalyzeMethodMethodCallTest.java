package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformerall.plan.MethodTransformPlanBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnalyzeMethodMethodCallTest {

    @Test
    @Ignore
    public void logLevelEnum() throws IOException {
        Map<String, MethodTransformPlanBuilder> methodNameToBuilder = new HashMap<>();
        runTest("LogLevelEnum", methodNameToBuilder);
    }

    @Test
    @Ignore
    public void staticMethodWithSynchronizedBlock() throws IOException {
        Map<String, MethodTransformPlanBuilder> methodNameToBuilder = new HashMap<>();
        runTest("StaticMethodWithSynchronizedBlock", methodNameToBuilder);
    }


    private void runTest(String orig,
                         Map<String, MethodTransformPlanBuilder> methodNameToBuilder)
            throws IOException {
        String name = "com/vmlens/test/guineaPig/" + orig;
        byte[] classfileBuffer = new LoadClassArray().load(name);
        ClassReader reader = new ClassReader(classfileBuffer);
        reader.accept(new ClassVisitorTestHelper(methodNameToBuilder), 0);
    }


}
