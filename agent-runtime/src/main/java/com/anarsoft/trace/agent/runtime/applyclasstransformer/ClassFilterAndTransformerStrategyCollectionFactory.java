package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.modelfactory.ModelFactory;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.builder.ClassBuilderImpl;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.builder.TransformerStrategyFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class ClassFilterAndTransformerStrategyCollectionFactory {

    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;
    private final TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed;
    private final TLinkedList<TLinkableWrapper<ClassFilterAndTransformerStrategy>> result = new TLinkedList<>();

    public ClassFilterAndTransformerStrategyCollectionFactory(MethodRepositoryForTransform methodRepositoryForAnalyze,
                                                              FieldRepositoryForTransform fieldRepositoryForAnalyze,
                                                              WriteClassDescriptionAndWarning writeClassDescription,
                                                              TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
        this.preAnalyzed = preAnalyzed;
    }

    public static ClassFilterAndTransformerStrategyCollectionFactory createFactory(WriteClassDescriptionAndWarning
                                                                                           writeClassDescription) {
        TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed = new ModelFactory().create();
        return new ClassFilterAndTransformerStrategyCollectionFactory(MethodRepositorySingleton.INSTANCE,
                FieldRepositorySingleton.INSTANCE, writeClassDescription, preAnalyzed);
    }

    public ClassFilterAndTransformerStrategyCollection create() {
        TransformerStrategyFactory transformerStrategyFactory = new TransformerStrategyFactory(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);
        ClassBuilderImpl classBuilder = new ClassBuilderImpl(transformerStrategyFactory);
        for (TLinkableWrapper<PackageOrClass> packageOrClass : preAnalyzed) {
            packageOrClass.element().addToBuilder(classBuilder);
        }
        return classBuilder.build();
    }
}
