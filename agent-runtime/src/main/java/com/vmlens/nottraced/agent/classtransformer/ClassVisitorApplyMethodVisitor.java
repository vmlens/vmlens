package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapter;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ACC_NATIVE;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class ClassVisitorApplyMethodVisitor extends ClassVisitor {


    private final String className;
    private final MethodRepositoryForTransform methodCallIdMap;
    private final FactoryCollectionAdapter factoryCollectionAdapter;

    private int classVersion;

    public ClassVisitorApplyMethodVisitor(ClassVisitor classVisitor,
                                          String className,
                                          MethodRepositoryForTransform methodCallIdMap,
                                          FactoryCollectionAdapter factoryCollectionAdapter) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.className = className;
        this.methodCallIdMap = methodCallIdMap;
        this.factoryCollectionAdapter = factoryCollectionAdapter;
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
        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));

        NameAndDescriptor nameAndDescriptor = new NameAndDescriptor(name, descriptor);
        FactoryCollectionAdapterContext adapterContext = new FactoryCollectionAdapterContext(className,nameAndDescriptor, access, inMethodId, methodCallIdMap);
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> factoryList =
                factoryCollectionAdapter.get(adapterContext);

        FactoryContext context = new FactoryContext();
        context.setMethodId(inMethodId);
        context.setClassName(className);
        context.setConstructor("<init>".equals(name));
        context.setDescription(descriptor);
        context.setStatic((access & ACC_STATIC) == ACC_STATIC);
        context.setUseExpandedFrames(useExpandedFrames());

        MethodVisitor current = previous;
        for (TLinkableWrapper<MethodVisitorFactory> element : factoryList) {
            current = element.element().create(context, current);
        }
        return current;
    }

    private boolean useExpandedFrames() {
        int major = classVersion & 0xFF;
        return major < Opcodes.V1_6;
    }

}
