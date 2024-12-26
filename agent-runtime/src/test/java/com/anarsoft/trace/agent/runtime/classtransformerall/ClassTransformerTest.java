package com.anarsoft.trace.agent.runtime.classtransformerall;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.classanalyzer.ClassVisitorAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethodcall.MethodCallFactoryFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepository;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.wrap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ClassTransformerTest {

    @Test
    public void threadStart() throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodRepository();

        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "<init>", "()V"));
        methodCallIdMap.asInt(new MethodCallId("com/vmlens/test/guineaPig/ThreadStart", "call", "()V"));

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.ThreadStart");


        TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> factoryFactoryList = new TLinkedList<>();
        factoryFactoryList.add(wrap(new MethodCallFactoryFactory()));

        ClassTransformer classArrayTransformer = new ClassTransformer(methodCallIdMap,
                factoryFactoryList,
                new TLinkedList<>(), null);
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/threadStart.txt", out.toString());
    }

    @Test
    public void testAll() throws IOException {
        testAll(null);
    }

    @Test
    public void testWithClassVisitorAnalyze() throws IOException {
        // Given
        MethodRepositoryForAnalyze methodRepositoryForAnalyze = mock(MethodRepositoryForAnalyze.class);
        FieldRepositoryForAnalyze fieldRepositoryForAnalyze = mock(FieldRepositoryForAnalyze.class);
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        WriteClassDescription writeClassDescription = new WriteClassDescriptionDuringStartup(classAnalyzedEventList);
        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze, writeClassDescription);

        // When
        testAll(classVisitorAnalyze);

        // Then
        ClassDescription classDescription = classAnalyzedEventList.get(0).element();
        assertThat(classDescription.name(), is("com/vmlens/test/guineaPig/StaticFieldAccess"));
        assertThat(classDescription.methodArray()[1].name(), is("update"));
    }

    private void testAll(ClassVisitor previous) throws IOException {
        MethodCallIdMap methodCallIdMap = new MethodRepository();
        FieldOwnerAndNameToIntMap fieldIdMap = new FieldRepository();

        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.StaticFieldAccess");

        ClassTransformer classArrayTransformer = ClassTransformer.createAll(methodCallIdMap, fieldIdMap, previous);
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        classArrayTransformer.transform(classArray, "com/vmlens/test/guineaPig/ThreadStart", new TraceClassVisitor(writer));

        new DiffText().assertEquals("/testAll.txt", out.toString());
    }

}
