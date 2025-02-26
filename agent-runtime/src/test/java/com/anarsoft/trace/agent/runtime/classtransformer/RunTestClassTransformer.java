package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.*;
import com.anarsoft.trace.agent.runtime.classtransformer.logging.ClassVisitorForLogging;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class RunTestClassTransformer {

    private final MethodRepositoryImpl methodRepositoryForAnalyze = new MethodRepositoryImpl();
    private final FieldRepositoryImpl fieldRepositoryForAnalyze = new FieldRepositoryImpl();

    public TransformerStrategy getStrategy(String className) throws IOException {
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList = new TLinkedList<>();

        WriteClassDescriptionAndWarningDuringStartup writeClassDescription =
                new WriteClassDescriptionAndWarningDuringStartup(classAnalyzedEventList, infoMessageEventList);
        ClassFilterAndTransformerStrategyCollectionFactory factory =
                new ClassFilterAndTransformerStrategyCollectionFactory(methodRepositoryForAnalyze,
                        fieldRepositoryForAnalyze,
                        writeClassDescription,
                        new LoadPreAnalyzed().loadFromClasspath());

        ClassFilterAndTransformerStrategyCollection collection = factory.create();
        String normalizedName = className.replace('.', '/');
        return collection.get(normalizedName);
    }

    public void runTest(String className, String resource) throws IOException {
        TransformerStrategy strategy = getStrategy(className);

        byte[] classfileBuffer = new LoadClassArray().loadResource(calculateFileName(className));
        TransformerContext transformerContext = new TransformerContext(classfileBuffer, className);
        byte[] transformed = strategy.transform(transformerContext);

        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);

        ClassReader readerForAnalyze = new ClassReader(transformed);
        readerForAnalyze.accept(new ClassVisitorForLogging(writer), 0);

        new DiffText().assertEquals(resource, out.toString());
    }

    public MethodRepositoryImpl methodRepositoryForAnalyze() {
        return methodRepositoryForAnalyze;
    }

    public FieldRepositoryImpl fieldRepositoryForAnalyze() {
        return fieldRepositoryForAnalyze;
    }

    protected String calculateFileName(String name) {
        String fileName = "/" + name.replace('.', '/') + ".class";
        return fileName;
    }
}
