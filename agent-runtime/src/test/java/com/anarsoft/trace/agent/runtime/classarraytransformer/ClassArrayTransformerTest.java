package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ClassArrayTransformerTest {

    @Test
    public void threadStart() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.ThreadStart");

        ClassArrayTransformer classArrayTransformer = new ClassArrayTransformer(methodCallIdMap);
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));
        System.out.println(out);
    }

}
