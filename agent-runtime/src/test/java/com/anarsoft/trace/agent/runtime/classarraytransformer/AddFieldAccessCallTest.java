package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitor.AddFieldAccessCall;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.repository.FieldIdMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.anarsoft.trace.agent.runtime.classarraytransformer.AddMonitorCallTest.classArrayTransformer;

public class AddFieldAccessCallTest {

    @Test
    public void staticField() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        FieldIdMap fieldIdMap = new FieldIdMap();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassArrayTransformer classArrayTransformer = classArrayTransformer(methodCallIdMap,
                AddFieldAccessCall.factory(fieldIdMap));
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/StaticFieldAccess", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/staticFieldAccess.txt", out.toString());
    }

}
