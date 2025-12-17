package com.vmlens.nottraced.agent.classanalyzer;

import com.vmlens.transformed.agent.bootstrap.description.ClassDescription;
import com.vmlens.transformed.agent.bootstrap.description.FieldInClassDescription;
import com.vmlens.transformed.agent.bootstrap.description.MethodDescription;
import com.vmlens.nottraced.agent.classtransformer.ASMConstants;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ClassVisitorAnalyze extends ClassVisitor {

    private final MethodRepositoryForTransform methodRepositoryForTransform;
    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;
    private final TLinkedList<TLinkableWrapper<FieldInClassDescription>> fieldDescriptionList = new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<MethodDescription>> methodDescriptionList = new TLinkedList<>();
    private String className;
    private String source;
    private String superClass;
    private String[] interfaces;

    public ClassVisitorAnalyze(MethodRepositoryForTransform methodRepositoryForTransform,
                               FieldRepositoryForTransform fieldRepositoryForAnalyze,
                               WriteClassDescriptionAndWarning writeClassDescription) {
        super(ASMConstants.ASM_API_VERSION);
        this.methodRepositoryForTransform = methodRepositoryForTransform;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superClass = superName;
        this.interfaces = interfaces;
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
        this.source = source;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldOwnerAndName fieldOwnerAndName = new FieldOwnerAndName(className, name);
        OnFieldAccess onFieldAccess = new OnFieldAccess(fieldRepositoryForAnalyze, fieldOwnerAndName);
        new AnalyzeFieldAccess(onFieldAccess).analyze(access);
        FieldInClassDescription fieldInClassDescription = new FieldInClassDescription(onFieldAccess.id(),
                access, name, descriptor, signature);
        fieldDescriptionList.add(wrap(fieldInClassDescription));
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodCallId methodCallId = new MethodCallId(className, name, descriptor);

        MethodDescription methodDescription = new MethodDescription(name, methodRepositoryForTransform.asInt(methodCallId),
                descriptor, access, -1);
        methodDescriptionList.add(wrap(methodDescription));
        return new MethodVisitorAnalyze(super.visitMethod(access, name, descriptor, signature, exceptions),
                methodDescription);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        ClassDescription classDescription = new ClassDescription(className, source,
                toArray(MethodDescription.class, methodDescriptionList),
                toArray(FieldInClassDescription.class, fieldDescriptionList), superClass, interfaces);
        writeClassDescription.write(classDescription);
    }
}
