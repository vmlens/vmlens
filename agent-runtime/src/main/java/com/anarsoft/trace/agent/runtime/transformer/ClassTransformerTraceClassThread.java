package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodCounts;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.MethodIdentifier;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;

public class ClassTransformerTraceClassThread extends ClassTransformerAbstract {

	public ClassTransformerTraceClassThread(ClassVisitor cv, String className, FilterList filterList,
			TransformConstants callBackStrings, ClassVisitorCreateDesc classVisitorCreateDesc,
			WriteClassDescription writeClassDescription) {
		super(cv, className, filterList, callBackStrings, classVisitorCreateDesc, writeClassDescription);

	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		if (name.equals("start")) {

			MethodIdentifier methodIdentifier = new MethodIdentifier(name, desc);

			MethodCounts methodCounts = classVisitorCreateDesc.getMethodCounts(methodIdentifier);

			boolean traceCalls = classVisitorCreateDesc.traceMethodCalls(methodIdentifier);

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

			boolean traceCalls = classVisitorCreateDesc.traceMethodCalls(methodIdentifier);

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
