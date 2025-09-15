package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapter;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.*;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;

import static org.objectweb.asm.Opcodes.*;

public class ClassVisitorApplyMethodVisitor extends ClassVisitor implements NeedsLoadsClassContainer {


    private final String className;
    private final MethodRepositoryForTransform methodCallIdMap;
    private final FactoryCollectionAdapter factoryCollectionAdapter;
    private final boolean isInRetransform;

    private int classVersion;
    private boolean needsLoadMethod = false;

    public ClassVisitorApplyMethodVisitor(ClassVisitor classVisitor,
                                          String className,
                                          MethodRepositoryForTransform methodCallIdMap,
                                          FactoryCollectionAdapter factoryCollectionAdapter,
                                          boolean isInRetransform) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.className = className;
        this.methodCallIdMap = methodCallIdMap;
        this.factoryCollectionAdapter = factoryCollectionAdapter;
        this.isInRetransform = isInRetransform;
    }

    @Override
    public final void visit(int version, int access, String name, String signature,
                            String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        classVersion = version;
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String descriptor,
                                     String signature,
                                     String[] exceptions) {
        MethodVisitor previous = super.visitMethod(access, name, descriptor, signature, exceptions);
        if ((access & ACC_NATIVE) == ACC_NATIVE) {
            return previous;
        }
        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));

        NameAndDescriptor nameAndDescriptor = new NameAndDescriptor(name, descriptor);
        FactoryCollectionAdapterContext adapterContext = new FactoryCollectionAdapterContext(className,
                nameAndDescriptor,
                access,
                inMethodId,
                methodCallIdMap);

        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> factoryList =
                factoryCollectionAdapter.get(adapterContext);

        FactoryContext context = new FactoryContext();
        context.setMethodId(inMethodId);
        context.setClassName(className);

        boolean isConstructor = "<init>".equals(name);
        context.setIsConstructor(isConstructor);

        boolean isStatic = ((access & ACC_STATIC) == ACC_STATIC);
        context.setMethodEnterExitStrategy(createMethodEnterExitStrategy(isStatic, isConstructor));

        MethodVisitor current = previous;
        for (TLinkableWrapper<MethodVisitorFactory> element : factoryList) {
            current = element.element().create(context, current);
        }
        return current;
    }

    @Override
    public void setNeedsLoadMethod() {
        needsLoadMethod = true;
    }

    @Override
    public void visitEnd() {
        if(needsLoadMethod) {
            MethodVisitor methodVisitor = cv.visitMethod(
                    ACC_PRIVATE | ACC_STATIC,
                    "vmlensGeneratedLoadClass",
                    "()Ljava/lang/Class;",
                    null,
                    new String[]{}
            );
            methodVisitor.visitCode();

            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/ClassNotFoundException");

            methodVisitor.visitLabel(label0);
            methodVisitor.visitLdcInsn(classNameForLoad());
            methodVisitor.visitMethodInsn(
                    INVOKESTATIC,
                    "java/lang/Class",
                    "forName",
                    "(Ljava/lang/String;)Ljava/lang/Class;"
            );
            methodVisitor.visitLabel(label1);
            methodVisitor.visitInsn(ARETURN);

            methodVisitor.visitLabel(label2);
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitInsn(ARETURN);

            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        super.visitEnd();
    }

    private String classNameForLoad() {
        return className.replace('/' , '.');
    }

    private boolean jvmAtLeast_1_5() {
        /*
        asm stores major version and minor version in one int
        & 0xFF makes sure we are only looking at the unsigned low 8 bits of the class file’s major version,
        ignoring the minor version and any higher bits.
         */
        int major = classVersion & 0xFF;
        return major >= Opcodes.V1_5;
    }

    private MethodEnterExitStrategy createMethodEnterExitStrategy(boolean isStatic, boolean isConstructor) {
        if(! isStatic) {
            if (isConstructor) {
                return new Constructor();
            }
            return new NormalMethod();
        }

        if(jvmAtLeast_1_5()) {
            return new StaticMethodJvmAtLeast_1_5();
        }

        if(isInRetransform) {
            new StaticMethodNoOp();
        }

        return new StaticMethodJvmLower_1_5(this);

    }

}
