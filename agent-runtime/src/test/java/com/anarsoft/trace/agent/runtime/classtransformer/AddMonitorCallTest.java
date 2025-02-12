package com.anarsoft.trace.agent.runtime.classtransformer;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import org.junit.Test;

import java.io.IOException;

public class AddMonitorCallTest {
    /*
        public static ClassTransformer classArrayTransformer(MethodRepositoryForTransform methodCallIdMap,
                                                             MethodVisitorForTransformFactory methodVisitorFactory) {
            TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> factoryList = new TLinkedList<>();
            factoryList.add(wrap(methodVisitorFactory));

            return null; //new ClassTransformer(methodCallIdMap,
                    //new TLinkedList<>(),
                    //factoryList, null, new MethodFilterTakeAll());
        }
    */
    @Test
    public void monitor() throws IOException {
        MethodRepositoryForTransform methodCallIdMap = MethodRepositoryImpl.create();

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
