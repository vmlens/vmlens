package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilterTakeAll;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorForTransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepository;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;

import java.io.IOException;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class AddMonitorCallTest {

    public static ClassTransformer classArrayTransformer(MethodCallIdMap methodCallIdMap,
                                                         MethodVisitorForTransformFactory methodVisitorFactory) {
        TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> factoryList = new TLinkedList<>();
        factoryList.add(wrap(methodVisitorFactory));

        return new ClassTransformer(methodCallIdMap,
                new TLinkedList<>(),
                factoryList, null, new MethodFilterTakeAll());
    }

    @Test
    public void monitor() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodRepository();

// Fixme
        /*
        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.SynchronizedBlock");

        ClassTransformer classArrayTransformer = classArrayTransformer(methodCallIdMap, AddMonitorCall.factory());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/SynchronizedBlock", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/synchronizedBlock.txt", out.toString());
    */
    }


}
