package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.description.FieldAccessDescription;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.repositorydeprecated.CallbackFactory;
import com.anarsoft.trace.agent.runtime.repositorydeprecated.FieldRepositoryFacade;
import com.anarsoft.trace.agent.runtime.transformer.gen.Add2TemplateMethodDescListGen;
import com.anarsoft.trace.agent.runtime.transformer.template.Add2TemplateMethodDescList;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplate;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplateBeforeAfter;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.trace.agent.bootstrap.StaticMonitorRepository;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.Iterator;

import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_METHOD;
import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT;

public class MethodTransformer extends MethodTransformerAbstract {
    static final TLinkedList<TemplateMethodDesc> templateMethodDescList = new TLinkedList<TemplateMethodDesc>();

    static {
        Add2TemplateMethodDescListGen.add2TemplateList(templateMethodDescList);
        Add2TemplateMethodDescList.add2TemplateList(templateMethodDescList);
    }

    private final HasGeneratedMethods hasGeneratedMethods;
    private final CallbackFactory callbackFactory;
    private final TLinkedList<TemplateMethodDesc> methodSpecificTemplatelist;
    private final TLinkedList<ApplyMethodTemplateBeforeAfter> templatelistBeforeAfter;
    private final boolean startsThread;
    private final boolean beganNewThread;
    private TraceSynchronization traceSynchronization;
    private int monitorPosition = 1;
    private int arrayPosition = 0;
    private int monitorExitPosition = 0;

    public MethodTransformer(MethodVisitor mv, int access, String name, String desc, String className,
                             String superClassName, MethodDescriptionBuilder methodDescriptionBuilder,
                             TraceSynchronization traceSynchronization,
                             int tryCatchBlockCount, HasGeneratedMethods hasGeneratedMethods, CallbackFactory callbackFactory,
                             TLinkedList<TemplateMethodDesc> methodSpecificTemplatelist,
                             boolean startsThread, boolean beganNewThread, boolean dottyProblematic,
                             boolean useExpandedFrames, TLinkedList<ApplyMethodTemplateBeforeAfter> templatelistBeforeAfter) {
        super(mv, access, desc, name, className, superClassName, tryCatchBlockCount, methodDescriptionBuilder, dottyProblematic, useExpandedFrames);

        this.traceSynchronization = traceSynchronization;
        this.hasGeneratedMethods = hasGeneratedMethods;
        this.callbackFactory = callbackFactory;
        this.methodSpecificTemplatelist = methodSpecificTemplatelist;
        this.startsThread = startsThread;
        this.beganNewThread = beganNewThread;
        this.templatelistBeforeAfter = templatelistBeforeAfter;

    }

    protected void onMethodEscapedException() {
        onMethodReturn();
    }


    private boolean isSemaphoreAcquire() {
        if (className.startsWith("java/util/concurrent/Semaphore")) {
            if (name.startsWith("tryAcquire")) {
                return true;
            }

            if (name.startsWith("acquire")) {
                return true;
            }

        }
        return false;

    }

    private boolean isFutureTask() {
        if (className.equals("java/util/concurrent/FutureTask") && name.equals("run")) {
            return true;
        }
        return false;
    }


    private boolean isForkJoinFork() {
        if (className.equals("java/util/concurrent/ForkJoinTask") && name.equals("fork")) {
            return true;
        }
        return false;
    }


    private boolean isConditionAwait() {
        if (className.equals("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject") && name.equals("await")) {
            return true;
        }
        return false;
    }


    protected void onMethodReturn() {


        if (isConditionAwait()) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject", "this$0", "Ljava/util/concurrent/locks/AbstractQueuedSynchronizer;");
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback",
                    "conditionAwait", "(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer;)V");
        }


        if (startsThread) {
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback",
                    "threadStartMethodExit", "()V");
        }

        if (isForkJoinFork()) {
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "forkJoinTaskForkExit", "()V");
        }


        if (beganNewThread) {
            this.mv.visitVarInsn(ALOAD, 0);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "methodExitExecTask", "(Ljava/lang/Object;)V");
        }

        switch (this.traceSynchronization) {
            case NORMAL:
                this.mv.visitVarInsn(ALOAD, 0);
                this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT,
                        "synchronizedMethodExit", "(Ljava/lang/Object;I)V");

                break;
            case STATIC:
                Integer monitorId = Integer.valueOf(StaticMonitorRepository.getOrCreate(this.className));
                this.mv.visitLdcInsn(monitorId);
                this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT,
                        "staticSynchronizedMethodExit", "(II)V");

                break;
            case NONE:
                break;

        }

        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_METHOD, "methodExit", "(I)V");

        if (isFutureTask()) {
            this.mv.visitMethodInsn(INVOKESTATIC,
                    CALLBACK_CLASS_METHOD,
                    "taskMethodExit", "()V");
        }
        if (isSemaphoreAcquire()) {
            this.mv.visitMethodInsn(INVOKESTATIC,
                    CALLBACK_CLASS_METHOD,
                    "semaphoreAcquireExit", "()V");
        }

    }

    protected void onMethodEnter() {


        if (startsThread) {
            this.mv.visitVarInsn(ALOAD, 1);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "threadStartMethodEnter", "(Ljava/lang/Object;)V");

        }
        if (isForkJoinFork()) {
            this.mv.visitVarInsn(ALOAD, 0);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "forkJoinTaskForkEnter", "(Ljava/util/concurrent/ForkJoinTask;)V");
        }

        if (beganNewThread) {
            this.mv.visitVarInsn(ALOAD, 0);
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "methodEnterExecTask", "(Ljava/lang/Object;)V");

        }

        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_METHOD, "methodEnter", "(I)V");

            switch (this.traceSynchronization) {
                case NORMAL:
                    this.mv.visitVarInsn(ALOAD, 0);
                    this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                    this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "synchronizedMethod", "(Ljava/lang/Object;I)V");

                    break;
                case STATIC:
                    Integer monitorId = Integer.valueOf(StaticMonitorRepository.getOrCreate(this.className));
                    this.mv.visitLdcInsn(monitorId);
                    this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                    this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "staticSynchronizedMethod", "(II)V");
                    break;
                case NONE:
                    break;
            }

        if (isFutureTask()) {
            this.mv.visitMethodInsn(INVOKESTATIC,
                    CALLBACK_CLASS_METHOD,
                    "taskMethodEnter", "()V");
        }


    }

    protected int getMethodId() {
        return this.methodDescriptionBuilder.getId();
    }

    private void tracePutField(String owner, String fieldName, String desc, FieldIdAndTyp typAndId) {

        Type fieldType = Type.getType(desc);
        if (fieldType.getSize() == 2) {
            this.mv.visitInsn(DUP2_X1);

            this.mv.visitInsn(POP2);

            this.mv.visitInsn(DUP_X2);
        } else {
            this.mv.visitInsn(DUP2);
            this.mv.visitInsn(POP);
        }
        this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        callbackFactory.createCallbackForFieldWrite(owner, fieldName, mv, typAndId,
                this.hasGeneratedMethods.hasGeneratedMethods(owner));

    }

    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        FieldIdAndTyp typAndId = FieldRepositoryFacade.get(owner, name);
        boolean isFinal = typAndId.fieldTyp == FieldTyp.FINAL;
        boolean isTraced = false;
        boolean isWrite = false;
        boolean isStatic = false;

        switch (opcode) {
            case (PUTFIELD): {
                isWrite = true;

                if (!isFinal) {
                    isTraced = true;
                    tracePutField(owner, name, desc, typAndId);
                }
                break;
            }
            case (PUTSTATIC): {
                isWrite = true;
                isStatic = true;

                if (!isFinal) {
                    isTraced = true;
                    this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
                    this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                    callbackFactory.createStaticCallbackForFieldWrite(owner, name, mv, typAndId);
                }
                break;
            }
            case (GETFIELD): {

                if (!isFinal) {
                    isTraced = true;
                    this.mv.visitInsn(DUP);
                    this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
                    this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                    callbackFactory.createCallbackForFieldRead(owner, name, mv, typAndId,
                            this.hasGeneratedMethods.hasGeneratedMethods(owner));
                }
                break;
            }
            case (GETSTATIC): {
                isStatic = true;

                if (!isFinal) {
                    isTraced = true;
                    this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
                    this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
                    callbackFactory.createStaticCallbackForFieldRead(owner, name, mv, typAndId);

                }
                break;
            }
        }

        this.mv.visitFieldInsn(opcode, owner, name, desc);

        if (isTraced) {
            this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
            int operation = MemoryAccessType.IS_READ;
            if (isWrite) {
                operation = MemoryAccessType.IS_WRITE;
            }
            this.mv.visitLdcInsn(Integer.valueOf(operation));
            this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallelize/facade/ParallelizeCallback",
                    "afterFieldAccess", "(II)V");

        }

        this.methodDescriptionBuilder.getFieldDescriptionList().add(new TLinkableWrapper(
                new FieldAccessDescription(name, owner, typAndId.id, isStatic, isWrite, isTraced, isFinal)));
    }

    protected void onMonitorEnter() {
            this.mv.visitInsn(DUP);

            this.mv.visitInsn(MONITORENTER);

            this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
            this.mv.visitLdcInsn(Integer.valueOf(monitorPosition));
            monitorPosition++;

        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorEnter",
                "(Ljava/lang/Object;II)V");

    }


    protected void onMonitorExit() {
        this.mv.visitInsn(DUP);
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitLdcInsn(Integer.valueOf(monitorExitPosition));
        monitorExitPosition++;
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorExit",
                "(Ljava/lang/Object;II)V");
    }


    protected void onWaitCall(int opcode, String owner, String name, String desc) {
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        if (desc.equals("()V")) {
            this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
                    "(Ljava/lang/Object;I)V");
        } else if (desc.equals("(J)V")) {
            this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
                    "(Ljava/lang/Object;JI)V");
        } else if (desc.equals("(JI)V")) {
            this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
                    "(Ljava/lang/Object;JII)V");
        }
    }



    private boolean isWaitCall(int opcode, String owner, String name, String desc) {
        return (opcode == INVOKEVIRTUAL) && (owner.equals("java/lang/Object")) && (name.equals("wait"))
                && ((desc.equals("()V")) || (desc.equals("(J)V")) || (desc.equals("(JI)V")));

    }

    public void visitMethodInsnInChild(int opcode, String owner, String name, String desc, boolean isInterface) {
        ApplyMethodTemplate applyMethodTemplate = null;

        Iterator<TemplateMethodDesc> it = templateMethodDescList.iterator();

        while (it.hasNext()) {
            TemplateMethodDesc current = it.next();
            applyMethodTemplate = current.applies(opcode, owner, name, desc);
            if (applyMethodTemplate != null) {
                break;
            }
        }

        ApplyMethodTemplateBeforeAfter applyMethodTemplateBeforeAfter = null;
        if (templatelistBeforeAfter != null) {
            Iterator<ApplyMethodTemplateBeforeAfter> iter = templatelistBeforeAfter.iterator();

            while (iter.hasNext()) {
                ApplyMethodTemplateBeforeAfter current = iter.next();
                ApplyMethodTemplateBeforeAfter temp = current.applies(opcode, owner, name, desc);

                if (temp != null) {
                    applyMethodTemplateBeforeAfter = temp;

                    break;
                }

            }
        }

        if (methodSpecificTemplatelist != null) {
            Iterator<TemplateMethodDesc> iter = methodSpecificTemplatelist.iterator();

            while (iter.hasNext()) {
                TemplateMethodDesc current = iter.next();
                ApplyMethodTemplate temp = current.applies(opcode, owner, name, desc);

                if (temp != null) {
                    applyMethodTemplate = temp;

                    break;
                }
            }
        }

        if (applyMethodTemplateBeforeAfter != null) {
            applyMethodTemplateBeforeAfter.applyBefore(mv);
        }


        if (applyMethodTemplate != null) {
            mv.visitLdcInsn(getMethodId());
            applyMethodTemplate.apply(mv);
        } else {

            if (isWaitCall(opcode, owner, name, desc)) {
                onWaitCall(opcode, owner, name, desc);
            } else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getDeclaredMethods".equals(name)
                    && "()[Ljava/lang/reflect/Method;".equals(desc)) {
                mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
                        "getFilteredDeclaredMethods", "(Ljava/lang/Class;)[Ljava/lang/reflect/Method;", false);
            } else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getDeclaredFields".equals(name)
                    && "()[Ljava/lang/reflect/Field;".equals(desc)) {
                mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
                        "getFilteredDeclaredFields", "(Ljava/lang/Class;)[Ljava/lang/reflect/Field;", false);
            } else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getMethods".equals(name)
                    && "()[Ljava/lang/reflect/Method;".equals(desc)) {
                mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
                        "getFilteredMethods", "(Ljava/lang/Class;)[Ljava/lang/reflect/Method;", false);
            } else {
                this.mv.visitMethodInsn(opcode, owner, name, desc, isInterface);
                // Wenn clone dann müssen wir den state für das geclonte objekt zurücksetzen
                if (opcode == INVOKESPECIAL && owner.equals("java/lang/Object") && name.equals("clone")
                        && desc.equals("()Ljava/lang/Object;")) {
                    this.mv.visitInsn(DUP);
                    this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/CloneCallback",
                            "resetState", "(Ljava/lang/Object;)V", false);
                }
            }
        }
        if (applyMethodTemplateBeforeAfter != null) {
            applyMethodTemplateBeforeAfter.applyAfter(mv);
        }
    }
    protected void onArrayLoad() {
        this.mv.visitInsn(DUP2);
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitLdcInsn(Integer.valueOf(arrayPosition));
        arrayPosition++;
        this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
                "get", "(Ljava/lang/Object;III)V");

    }

    protected void onArrayStore() {
        this.mv.visitInsn(DUP_X2);
        this.mv.visitInsn(POP);
        this.mv.visitInsn(DUP2_X1);
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitLdcInsn(Integer.valueOf(arrayPosition));
        arrayPosition++;

        this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
                "put", "(Ljava/lang/Object;III)V");
    }

}
