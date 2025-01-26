package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.FieldCallbackFactory;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class AddFieldAccessCall extends MethodVisitor {

    private final FieldOwnerAndNameToIntMap fieldIdMap;
    private final FieldCallbackFactory fieldCallbackFactory;
    int position = 0;

    public AddFieldAccessCall(MethodVisitor methodVisitor, FieldOwnerAndNameToIntMap fieldIdMap, int inMethodId) {
        super(ASMConstants.ASM_API_VERSION, methodVisitor);
        this.fieldIdMap = fieldIdMap;
        this.fieldCallbackFactory = new FieldCallbackFactory(methodVisitor, inMethodId);
    }

    public static MethodVisitorForTransformFactory factory(FieldOwnerAndNameToIntMap fieldIdMap) {
        return new MethodVisitorForTransformFactory() {
            @Override
            public MethodVisitor create(int methodId, MethodVisitor previous) {
                return new AddFieldAccessCall(previous, fieldIdMap, methodId);
            }
        };
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        int fieldId = fieldIdMap.asInt(new FieldOwnerAndName(owner, name));

        switch (opcode) {
            case PUTFIELD:
                Type fieldType = Type.getType(descriptor);
                if (fieldType.getSize() == 2) {
                    super.visitInsn(DUP2_X1);
                    super.visitInsn(POP2);
                    super.visitInsn(DUP_X2);
                } else {
                    super.visitInsn(DUP2);
                    super.visitInsn(POP);
                }
                fieldCallbackFactory.beforeFieldWrite(fieldId, position);
                break;

            case PUTSTATIC:
                fieldCallbackFactory.beforeStaticFieldWrite(fieldId, position);
                break;

            case GETFIELD:
                this.mv.visitInsn(DUP);
                fieldCallbackFactory.beforeFieldRead(fieldId, position);
                break;

            case GETSTATIC:
                fieldCallbackFactory.beforeStaticFieldRead(fieldId, position);
                break;
        }

        super.visitFieldInsn(opcode, owner, name, descriptor);

        fieldCallbackFactory.afterFieldAccess();
    }
}
