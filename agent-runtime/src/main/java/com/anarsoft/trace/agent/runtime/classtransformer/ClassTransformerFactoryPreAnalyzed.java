package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classanalyzer.ClassVisitorAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorForTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.MethodEnterExitAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassTransformerFactoryPreAnalyzed implements ClassTransformerFactory {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;

    public ClassTransformerFactoryPreAnalyzed(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                              FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                                              WriteClassDescriptionAndWarning writeClassDescription) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    @Override
    public ClassTransformer create() {
        ClassVisitor classVisitor = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);
        return createPreAnalyzed(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                classVisitor);
    }

    private ClassTransformer createPreAnalyzed(MethodCallIdMap methodCallIdMap,
                                               FieldOwnerAndNameToIntMap fieldIdMap,
                                               // can be null
                                               ClassVisitor previousClassVisitor) {
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>>
                methodVisitorFactoryAnalyzeList = new TLinkedList<>();
        // The methodCallFactory must be added last, since it transforms method calls
        methodVisitorFactoryAnalyzeList.add(wrap(new MethodEnterExitAnalyzeAndTransformFactoryFactory()));
        TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> methodVisitorFactoryList
                = new TLinkedList<>();
        return new ClassTransformer(methodCallIdMap, methodVisitorFactoryAnalyzeList,
                methodVisitorFactoryList, previousClassVisitor);
    }


}
