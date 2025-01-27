package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilter;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorForTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryContext;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryForAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryForTransform;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ACC_NATIVE;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class ClassVisitorApplyMethodVisitor extends ClassVisitor {

    private final MethodVisitorFactory methodVisitorFactory;
    private final String className;
    private final MethodCallIdMap methodCallIdMap;
    private final MethodVisitorAnalyzeAndTransformFactoryMap
            methodIdToFactory;
    private final MethodFilter methodFilter;
    private int classVersion;

    public ClassVisitorApplyMethodVisitor(ClassVisitor classVisitor,
                                          MethodVisitorFactory methodVisitorFactory,
                                          String className,
                                          MethodCallIdMap methodCallIdMap,
                                          MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                                          MethodFilter methodFilter) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.methodVisitorFactory = methodVisitorFactory;
        this.className = className;
        this.methodCallIdMap = methodCallIdMap;
        this.methodIdToFactory = methodIdToFactory;
        this.methodFilter = methodFilter;
    }

    public static ClassVisitor createAnalyze(ClassVisitor previousClassVisitor,
                                             String className,
                                             MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                                             TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>>
                                                     factoryFactoryList,
                                             MethodCallIdMap methodCallIdMap, MethodFilter methodFilter) {
        return new ClassVisitorApplyMethodVisitor(previousClassVisitor,
                new MethodVisitorFactoryForAnalyze(factoryFactoryList), className, methodCallIdMap, methodIdToFactory, methodFilter);
    }

    public static ClassVisitor createTransform(ClassVisitor classWriter,
                                               String className,
                                               MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                                               MethodCallIdMap methodCallIdMap,
                                               TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> transformFactoryList,
                                               MethodFilter methodFilter) {
        return new ClassVisitorApplyMethodVisitor(classWriter,
                new MethodVisitorFactoryForTransform(transformFactoryList),
                className, methodCallIdMap, methodIdToFactory, methodFilter);
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
        MethodVisitor previous = super.visitMethod(access, name, descriptor, signature, exceptions);
        if ("<init>".equals(name)) {
            return previous;
        }
        if ((access & ACC_NATIVE) == ACC_NATIVE) {
            return previous;
        }
        if (!methodFilter.take(name, descriptor)) {
            return previous;
        }

        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));
        MethodVisitorFactoryContext transformFactoryContext = new MethodVisitorFactoryContext();
        transformFactoryContext.setMethodId(inMethodId);
        transformFactoryContext.setClassName(className);
        transformFactoryContext.setConstructor("<init>".equals(name));
        transformFactoryContext.setDescription(descriptor);
        transformFactoryContext.setStatic((access & ACC_STATIC) == ACC_STATIC);
        transformFactoryContext.setUseExpandedFrames(useExpandedFrames());

        return methodVisitorFactory.create(transformFactoryContext, previous, methodIdToFactory, methodCallIdMap);
    }

    private boolean useExpandedFrames() {
        int major = classVersion & 0xFF;
        return major < Opcodes.V1_6;
    }

}
