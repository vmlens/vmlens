package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddMonitorCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class AddMonitorCallTest {

    public static ClassTransformer classArrayTransformer(MethodCallIdMap methodCallIdMap,
                                                         MethodVisitorFactory methodVisitorFactory) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> factoryList = new TLinkedList<>();
        factoryList.add(wrap(methodVisitorFactory));

        return new ClassTransformer(methodCallIdMap,
                new TLinkedList<>(),
                factoryList);
    }

    @Test
    public void monitor() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();


        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.SynchronizedBlock");

        ClassTransformer classArrayTransformer = classArrayTransformer(methodCallIdMap, AddMonitorCall.factory());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/SynchronizedBlock", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/synchronizedBlock.txt", out.toString());
    }

}
