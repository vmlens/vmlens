package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.TransformFactoryContext;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class ClassVisitorTransform extends ClassVisitor {


    private final MethodCallIdMap methodCallIdMap;
    private final String className;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList;
    private final MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory;
    private int classVersion;

    public ClassVisitorTransform(MethodCallIdMap methodCallIdMap,
                                 ClassVisitor classVisitor, String className,
                                 TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList,
                                 MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.methodCallIdMap = methodCallIdMap;
        this.className = className;
        this.methodVisitorFactoryList = methodVisitorFactoryList;
        this.methodIdToFactory = methodIdToFactory;
    }

    @Override
    public final void visit(int version, int access, String name, String signature,
                            String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        classVersion = version;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));

        MethodVisitor previous = super.visitMethod(access, name, descriptor, signature, exceptions);
        for (TLinkableWrapper<MethodVisitorFactory> element : methodVisitorFactoryList) {
            previous = element.element.create(inMethodId, previous);
        }


        MethodId methodId = new MethodId(access, name, descriptor, signature, exceptions);
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> factoryList =
                methodIdToFactory.get(methodId);
        if (factoryList != null) {
            for (TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory> element : factoryList) {

                TransformFactoryContext transformFactoryContext = new TransformFactoryContext();
                transformFactoryContext.setMethodId(inMethodId);
                transformFactoryContext.setClassName(className);
                transformFactoryContext.setConstructor("<init>".equals(name));
                transformFactoryContext.setDescription(descriptor);
                transformFactoryContext.setPrevious(previous);
                transformFactoryContext.setStatic((access | ACC_STATIC) == ACC_STATIC);
                transformFactoryContext.setUseExpandedFrames(useExpandedFrames());

                previous = element.element.createTransform(transformFactoryContext);
            }
        }
        return previous;
    }

    private boolean useExpandedFrames() {
        int major = classVersion & 0xFF;
        return major < Opcodes.V1_6;
    }


}
