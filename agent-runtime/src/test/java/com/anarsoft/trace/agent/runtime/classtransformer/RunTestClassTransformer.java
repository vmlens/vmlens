package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.modelfactory.ModelFactory;
import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ClassFilterAndTransformerStrategyCollection;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.builder.ClassBuilderImpl;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.builder.TransformerStrategyFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.logging.ClassVisitorForLogging;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class RunTestClassTransformer {

    private final MethodRepositoryImpl methodRepositoryForAnalyze = new MethodRepositoryImpl(null);
    private final FieldRepository fieldRepositoryForAnalyze = new FieldRepository();

    public void runTest(String className, String resource) throws IOException {
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList = new TLinkedList<>();

        WriteClassDescriptionAndWarningDuringStartup writeClassDescription =
                new WriteClassDescriptionAndWarningDuringStartup(classAnalyzedEventList, infoMessageEventList);
        TransformerStrategyFactory transformerStrategyFactory = new TransformerStrategyFactory(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze, writeClassDescription);

        ClassBuilderImpl classBuilderImpl = new ClassBuilderImpl(transformerStrategyFactory);
        TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed = new ModelFactory().create();
        for (TLinkableWrapper<PackageOrClass> p : preAnalyzed) {
            p.element().addToBuilder(classBuilderImpl);
        }

        ClassFilterAndTransformerStrategyCollection collection = classBuilderImpl.build();
        String normalizedName = className.replace('.', '/');
        TransformerStrategy strategy = collection.get(normalizedName);
        byte[] classfileBuffer = new LoadClassArray().load(className);

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

    public FieldRepository fieldRepositoryForAnalyze() {
        return fieldRepositoryForAnalyze;
    }
}
