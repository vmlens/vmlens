package com.anarsoft.trace.agent.runtime.transformer;




import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodCounts;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.MethodIdentifier;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;

public class ClassTransformerTraceClassLoader  extends ClassTransformerAbstract {

	public ClassTransformerTraceClassLoader(ClassVisitor cv, String className,
			ClassVisitorCreateDesc classVisitorCreateDesc,
			WriteClassDescription writeClassDescription) {
        super(cv, className, classVisitorCreateDesc,
                writeClassDescription);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
        if ("java/io/ObjectStreamClass".equals(className) && "computeDefaultSUID".equals(name) && "(Ljava/lang/Class;)J".equals(desc)) {
            return new MethodTransformerApplyReflectionFilter(super.visitMethod(access, name, desc, signature, exceptions));
        }

        if (!name.startsWith("<")) {
            MethodIdentifier methodIdentifier = new MethodIdentifier(name, desc);
            MethodCounts methodCounts = classVisitorCreateDesc.getMethodCounts(methodIdentifier);
            MethodDescriptionBuilder methodDescriptionBuilder = new MethodDescriptionBuilder(name, newMethodId(), desc, access, classVisitorCreateDesc);
            methodBuilderList.add(methodDescriptionBuilder);

            return new MethodTransformerForClassloader(super.visitMethod(access, name, desc, signature, exceptions),
                    access, desc, name,
                    className, superClassName, methodCounts.tryCatchBlockCount, methodDescriptionBuilder, methodCounts.dottyProblematic, useExpandedFrames());

        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

	}
}
