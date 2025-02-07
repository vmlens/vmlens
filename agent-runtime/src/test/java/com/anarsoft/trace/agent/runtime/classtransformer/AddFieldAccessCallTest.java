package com.anarsoft.trace.agent.runtime.classtransformer;

import org.junit.Test;

import java.io.IOException;

public class AddFieldAccessCallTest {

    @Test
    public void staticField() throws IOException {
        // Fixme
    /*    MethodCallIdMap methodCallIdMap = new MethodRepository();
        FieldOwnerAndNameToIntMap fieldIdMap = new FieldRepository();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassTransformer classArrayTransformer = classArrayTransformer(methodCallIdMap,
                AddFieldAccessCall.factory(fieldIdMap));
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/StaticFieldAccess", new TraceClassVisitor(writer));
        new DiffText().assertEquals("/staticFieldAccess.txt", out.toString());
    */
    }

}
