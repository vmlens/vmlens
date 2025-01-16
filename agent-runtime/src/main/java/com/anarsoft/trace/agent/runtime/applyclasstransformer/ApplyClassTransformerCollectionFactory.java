package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl.ClassTransformerVmlensApi;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl.TransformerStrategyAll;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl.TransformerStrategyFilter;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ApplyClassTransformerCollectionFactory {


    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;
    ;
    private final TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result = new TLinkedList<>();

    public ApplyClassTransformerCollectionFactory(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                                  FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                                                  WriteClassDescriptionAndWarning writeClassDescription) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    public ApplyClassTransformerCollectionFactory(WriteClassDescriptionAndWarning writeClassDescription) {
        this(MethodRepositorySingleton.INSTANCE,
                FieldRepositorySingleton.INSTANCE, writeClassDescription);
    }

    // Visible for Test
    // From specific to generic
    void add(String name, TransformerStrategy transformerStrategy) {
        result.add(wrap(new ApplyClassTransformerElement(name, transformerStrategy)));
    }

    // Visible for Test
    ApplyClassTransformerCollection createInternal() {
        return new ApplyClassTransformerCollection(result);
    }

    public ApplyClassTransformerCollection create() {

        add("com/vmlens/api/AllInterleavingsBuilder", new TransformerStrategyFilter());
        add("com/vmlens/api/AllInterleavings", new ClassTransformerVmlensApi());

        add("com/vmlens/test", new TransformerStrategyAll(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription));

        return createInternal();
    }

}
