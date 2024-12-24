package com.anarsoft.trace.agent.runtime.classanalyzer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.FieldInClassDescription;
import com.anarsoft.trace.agent.description.MethodDescription;
import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.anarsoft.trace.agent.runtime.classtransformerall.ASMConstants;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.TLinkableWrapper.toArray;
import static com.anarsoft.trace.agent.runtime.TLinkableWrapper.wrap;

public class ClassVisitorAnalyze extends ClassVisitor {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescription writeClassDescription;
    private final TLinkedList<TLinkableWrapper<FieldInClassDescription>> fieldDescriptionList = new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<MethodDescription>> methodDescriptionList = new TLinkedList<>();
    private String className;
    private String source;
    private String superClass;
    private String[] interfaces;

    public ClassVisitorAnalyze(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                               FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                               WriteClassDescription writeClassDescription) {
        super(ASMConstants.ASM_API_VERSION);
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
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
        OnMethodAccess onMethodAccess = new OnMethodAccess(methodRepositoryForAnalyze, methodCallId);
        new AnalyzeMethodAccess(onMethodAccess).analyze(access);

        MethodDescription methodDescription = new MethodDescription(name, onMethodAccess.id(), descriptor, access, 0);
        methodDescriptionList.add(wrap(methodDescription));

        return super.visitMethod(access, name, descriptor, signature, exceptions);
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
