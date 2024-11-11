package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.MethodCallFactoryFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.repository.FieldIdMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassTransformerTest {

    @Test
    public void threadStart() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();

        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "<init>", "()V"));
        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "call", "()V"));

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.ThreadStart");


        TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> factoryFactoryList = new TLinkedList<>();
        factoryFactoryList.add(wrap(new MethodCallFactoryFactory()));

        ClassTransformer classArrayTransformer = new ClassTransformer(methodCallIdMap,
                factoryFactoryList,
                new TLinkedList<>());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/threadStart.txt", out.toString());
    }

    @Test
    public void testAll() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        FieldIdMap fieldIdMap = new FieldIdMap();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassTransformer classArrayTransformer = ClassTransformer.createAll(methodCallIdMap, fieldIdMap);
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/testAll.txt", out.toString());
    }

}
