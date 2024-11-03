package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.TransformMethodMethodCallFactoryImpl;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ClassTransformerTest {

    @Test
    public void threadStart() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();

        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "<init>", "()V"));
        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "call", "()V"));

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.ThreadStart");

        ClassTransformer classArrayTransformer = new ClassTransformer(methodCallIdMap,
                new TransformMethodMethodCallFactoryImpl(methodCallIdMap),
                new TLinkedList<>());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/threadStart.txt", out.toString());
    }

}
