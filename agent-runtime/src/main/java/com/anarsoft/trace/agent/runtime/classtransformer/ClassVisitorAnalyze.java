package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactoryFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorAnalyze extends ClassVisitor {

    private final TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>>
            methodVisitorFactoryAnalyzeList;
    private final MethodVisitorAnalyzeAndTransformFactoryMap
            methodIdToFactory;
    private final String className;
    private final MethodCallIdMap methodCallIdMap;


    public ClassVisitorAnalyze(MethodCallIdMap methodCallIdMap,
                               String className,
                               TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> methodVisitorFactoryAnalyzeList,
                               MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory) {
        super(ASMConstants.ASM_API_VERSION);
        this.methodCallIdMap = methodCallIdMap;
        this.className = className;
        this.methodVisitorFactoryAnalyzeList = methodVisitorFactoryAnalyzeList;
        this.methodIdToFactory = methodIdToFactory;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {

        MethodId methodId = new MethodId(access, name, descriptor, signature, exceptions);
        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));

        MethodVisitor previous = super.visitMethod(access, name, descriptor, signature, exceptions);
        for (TLinkableWrapper<MethodVisitorFactoryFactory> element : methodVisitorFactoryAnalyzeList) {
            MethodVisitorAnalyzeAndTransformFactory factory = element.element.create(methodCallIdMap);
            methodIdToFactory.put(methodId, factory);
            previous = factory.createAnalyze(inMethodId, previous);
        }

        return previous;
    }
}
