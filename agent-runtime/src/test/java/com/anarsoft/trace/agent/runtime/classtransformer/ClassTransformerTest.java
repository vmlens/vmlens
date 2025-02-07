package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilterTakeAll;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.MethodCallAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepository;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassTransformerTest {

    @Test
    public void threadStart() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodRepository();

        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "<init>", "()V"));
        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "call", "()V"));

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.ThreadStart");


        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>> factoryFactoryList = new TLinkedList<>();
        factoryFactoryList.add(wrap(new MethodCallAnalyzeAndTransformFactoryFactory()));

        ClassTransformer classArrayTransformer = new ClassTransformer(methodCallIdMap,
                factoryFactoryList,
                new TLinkedList<>(), null, new MethodFilterTakeAll());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/threadStart.txt", out.toString());
    }

    //@Test
    public void testAll() throws IOException {
        testAll(new WriteClassDescriptionAndWarningNoOp());
    }

    //@Test
    public void testWithClassVisitorAnalyze() throws IOException {
        // Given
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        WriteClassDescriptionAndWarning writeClassDescription = new WriteClassDescriptionAndWarningDuringStartup(classAnalyzedEventList, new TLinkedList<>());

        // When
        testAll(writeClassDescription);

        // Then
        ClassDescription classDescription = classAnalyzedEventList.get(0).element();
        assertThat(classDescription.name(), is("com/vmlens/test/guineaPig/StaticFieldAccess"));
        assertThat(classDescription.methodArray()[1].name(), is("update"));
    }

    private void testAll(WriteClassDescriptionAndWarning writeClassDescriptionAndWarning) throws IOException {
        MethodRepository methodCallIdMap = new MethodRepository();
        FieldRepository fieldIdMap = new FieldRepository();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassTransformer classArrayTransformer = new ClassTransformerFactoryAll(methodCallIdMap, fieldIdMap, writeClassDescriptionAndWarning)
                .create(new MethodFilterTakeAll());
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/testAll.txt", out.toString());
    }

}
