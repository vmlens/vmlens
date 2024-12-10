package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddFieldAccessCall;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.anarsoft.trace.agent.runtime.classtransformer.AddMonitorCallTest.classArrayTransformer;

public class AddFieldAccessCallTest {

    @Test
    public void staticField() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodCallIdMap();
        FieldOwnerAndNameToIntMap fieldIdMap = new FieldOwnerAndNameToIntMap();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassTransformer classArrayTransformer = classArrayTransformer(methodCallIdMap,
                AddFieldAccessCall.factory(fieldIdMap));
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/StaticFieldAccess", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/staticFieldAccess.txt", out.toString());
    }

}
