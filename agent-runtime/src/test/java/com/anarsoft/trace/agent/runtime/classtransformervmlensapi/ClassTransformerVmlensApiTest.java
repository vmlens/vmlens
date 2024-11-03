package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.repository.FieldIdMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ClassTransformerVmlensApiTest {

    @Test
    public void transform() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        FieldIdMap fieldIdMap = new FieldIdMap();

        byte[] classArray = new LoadClassArray().load("com.vmlens.api.AllInterleavings");

        ClassTransformerVmlensApi classArrayTransformer = new ClassTransformerVmlensApi();
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, new TraceClassVisitor(writer));

        new DiffText().assertEquals("/allInterleavings.txt", out.toString());
    }
}
