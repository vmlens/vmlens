package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactoryAll;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactoryPreAnalyzed;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerStrategyClassTransformer;
import com.anarsoft.trace.agent.runtime.classtransformervmlensapi.TransformerStrategyVmlensApi;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassNameAndTransformerStrategyCollectionFactory {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;
    private final TLinkedList<TLinkableWrapper<ClassNameAndTransformerStrategy>> result = new TLinkedList<>();

    public ClassNameAndTransformerStrategyCollectionFactory(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                                            FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                                                            WriteClassDescriptionAndWarning writeClassDescription) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    public ClassNameAndTransformerStrategyCollectionFactory(WriteClassDescriptionAndWarning writeClassDescription) {
        this(MethodRepositorySingleton.INSTANCE,
                FieldRepositorySingleton.INSTANCE, writeClassDescription);
    }

    // Visible for Test
    // From specific to generic
    void add(String name, TransformerStrategy transformerStrategy) {
        result.add(wrap(new ClassNameAndTransformerStrategy(name, transformerStrategy)));
    }

    // Visible for Test
    ClassNameAndTransformerStrategyCollection createInternal() {
        return new ClassNameAndTransformerStrategyCollection(result);
    }

    public ClassNameAndTransformerStrategyCollection create() {
        ClassTransformerFactory classTransformerFactoryAll = new ClassTransformerFactoryAll(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);

        ClassTransformerFactory classTransformerFactoryPreAnalyzed = new ClassTransformerFactoryPreAnalyzed(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);


        add("com/vmlens/api/AllInterleavingsBuilder", new TransformerStrategyFilter());
        add("com/vmlens/api/AllInterleavings", new TransformerStrategyVmlensApi());

        add("com/vmlens/test", new TransformerStrategyClassTransformer(classTransformerFactoryAll));

        add("java", new TransformerStrategyClassTransformer(classTransformerFactoryPreAnalyzed));

        return createInternal();
    }

}
