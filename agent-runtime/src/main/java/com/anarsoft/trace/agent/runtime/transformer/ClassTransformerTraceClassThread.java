package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodCounts;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.MethodIdentifier;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;


public class ClassTransformerTraceClassThread extends ClassTransformerAbstract {

    public ClassTransformerTraceClassThread(ClassVisitor cv, String className,
                                            ClassVisitorCreateDesc classVisitorCreateDesc,
                                            WriteClassDescription writeClassDescription) {
        super(cv, className, classVisitorCreateDesc, writeClassDescription);

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        if (name.equals("start")) {
            MethodIdentifier methodIdentifier = new MethodIdentifier(name, desc);
			MethodCounts methodCounts = classVisitorCreateDesc.getMethodCounts(methodIdentifier);
			MethodDescriptionBuilder methodDescriptionBuilder = new MethodDescriptionBuilder(name, newMethodId(), desc,
					access, classVisitorCreateDesc);
			methodBuilderList.add(methodDescriptionBuilder);

			return new MethodTransformerThreadStart(super.visitMethod(access, name, desc, signature, exceptions),
					access, desc, name, className, superClassName, methodCounts.tryCatchBlockCount, methodDescriptionBuilder, methodCounts.dottyProblematic, useExpandedFrames());

		} else if (name.equals("join") && desc.equals("(J)V")) {
			return new MethodTransformerThreadJoin(super.visitMethod(access, name, desc, signature, exceptions));
		} else if (name.equals("run")) {
			MethodIdentifier methodIdentifier = new MethodIdentifier(name, desc);
			MethodCounts methodCounts = classVisitorCreateDesc.getMethodCounts(methodIdentifier);
			MethodDescriptionBuilder methodDescriptionBuilder = new MethodDescriptionBuilder(name, newMethodId(), desc,
					access, classVisitorCreateDesc);
			methodBuilderList.add(methodDescriptionBuilder);
			return new MethodTransformerThreadRun(super.visitMethod(access, name, desc, signature, exceptions), access,
					desc, name, className, superClassName, methodCounts.tryCatchBlockCount, methodDescriptionBuilder , methodCounts.dottyProblematic, useExpandedFrames());

		} else {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}

	}

}
