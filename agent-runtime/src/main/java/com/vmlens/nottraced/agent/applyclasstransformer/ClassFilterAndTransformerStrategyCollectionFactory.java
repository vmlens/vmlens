package com.vmlens.nottraced.agent.applyclasstransformer;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.nottraced.agent.applyclasstransformer.builder.ClassTransformerListBuilderImpl;
import com.vmlens.nottraced.agent.applyclasstransformer.builder.TransformerStrategyFactory;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.io.IOException;

public class ClassFilterAndTransformerStrategyCollectionFactory {



    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;
    private final TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed;


    public ClassFilterAndTransformerStrategyCollectionFactory(MethodRepositoryForTransform methodRepositoryForAnalyze,
                                                              FieldRepositoryForTransform fieldRepositoryForAnalyze,
                                                              WriteClassDescriptionAndWarning writeClassDescription,
                                                              TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
        this.preAnalyzed = preAnalyzed;
    }

    public static ClassFilterAndTransformerStrategyCollectionFactory createFactory(String libPath,
                                                                                   WriteClassDescriptionAndWarning
                                                                                           writeClassDescription) throws IOException {
        TLinkedList<TLinkableWrapper<PackageOrClass>> preAnalyzed = new LoadPreAnalyzed().load(libPath);
        return new ClassFilterAndTransformerStrategyCollectionFactory(MethodRepositorySingleton.INSTANCE,
                FieldRepositorySingleton.INSTANCE, writeClassDescription, preAnalyzed);
    }

    public ClassFilterAndTransformerStrategyCollection create() {
        TransformerStrategyFactory transformerStrategyFactory = new TransformerStrategyFactory(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);
        ClassTransformerListBuilderImpl classBuilder = new ClassTransformerListBuilderImpl(transformerStrategyFactory);
       

        for (TLinkableWrapper<PackageOrClass> packageOrClass : preAnalyzed) {
            packageOrClass.element().addToBuilder(classBuilder);
        }

        return classBuilder.build();
    }
}
