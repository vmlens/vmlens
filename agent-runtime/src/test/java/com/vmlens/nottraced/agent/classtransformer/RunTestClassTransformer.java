package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.transformed.agent.bootstrap.description.ClassDescription;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.nottraced.agent.LoadClassArray;
import com.vmlens.nottraced.agent.applyclasstransformer.*;
import com.vmlens.nottraced.agent.classtransformer.logging.ClassVisitorForLogging;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.test.util.DiffText;
import com.vmlens.transformed.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryImpl;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class RunTestClassTransformer {

    private final ClassFilterAndTransformerStrategyCollection collection;

    private final MethodRepositoryImpl methodRepositoryForAnalyze;


    public RunTestClassTransformer(ClassFilterAndTransformerStrategyCollection collection,
                                   MethodRepositoryImpl methodRepositoryForAnalyze) {
        this.collection = collection;
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
    }

    public static RunTestClassTransformer create(TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed)  {
        MethodRepositoryImpl methodRepositoryForAnalyze = new MethodRepositoryImpl();
        FieldRepositoryImpl fieldRepositoryForAnalyze = new FieldRepositoryImpl();

        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList = new TLinkedList<>();

        WriteClassDescriptionAndWarningDuringStartup writeClassDescription =
                new WriteClassDescriptionAndWarningDuringStartup(classAnalyzedEventList, infoMessageEventList);


        ClassFilterAndTransformerStrategyCollectionFactory factory =
                new ClassFilterAndTransformerStrategyCollectionFactory(methodRepositoryForAnalyze,
                        fieldRepositoryForAnalyze,
                        writeClassDescription,
                        preAnalyzed);

        ClassFilterAndTransformerStrategyCollection collection = factory.create();
        return new RunTestClassTransformer(collection,methodRepositoryForAnalyze);
    }


    public static RunTestClassTransformer createFromLoaded() throws IOException {
        TLinkedList<TLinkableWrapper<PackageOrClass>> result = new TLinkedList<>();
        new LoadPreAnalyzed().loadFromClasspath(result);
        return create(result);
    }


    public TransformerStrategy getStrategy(String className)  {


        String normalizedName = className.replace('.', '/');
        return collection.get(normalizedName);
    }

    public void runTest(String className, String resource) throws IOException {
        TransformerStrategy strategy = getStrategy(className);

        byte[] classfileBuffer = new LoadClassArray().loadResource(calculateFileName(className));
        TransformerContext transformerContext = new TransformerContext(classfileBuffer, className, false);
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


    protected String calculateFileName(String name) {
        String fileName = "/" + name.replace('.', '/') + ".class";
        return fileName;
    }
}
