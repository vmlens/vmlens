package com.anarsoft.trace.agent.runtime;

import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Type;

import com.anarsoft.trace.agent.runtime.repository.MethodInClassIdentifier;


public class AnnotationVisitorCallback extends AnnotationVisitor  {

	private final ClassVisitorCreateDesc classVisitorCreateDesc;
	private final MethodInClassIdentifier methodInClassIdentifier;
	
	
	public AnnotationVisitorCallback(ClassVisitorCreateDesc classVisitorCreateDesc,
			MethodInClassIdentifier methodInClassIdentifier) {
		super(  AgentClassFileTransformer.ASM_API_VERSION );
		this.classVisitorCreateDesc = classVisitorCreateDesc;
		this.methodInClassIdentifier = methodInClassIdentifier;
	}


	@Override
	public void visit(String name, Object value) {
		
		
		//System.out.println(name + " " + value + " " + );
		
		Type callbackType  = (Type) value;
		
		
		AtomicMethodWithCallback cb = 
		new  AtomicMethodWithCallback(methodInClassIdentifier.getMethodName()  ,methodInClassIdentifier.getDesc(), new String[] { callbackType.getClassName() }); 
		
		classVisitorCreateDesc.createAtomic.addCallback(  cb );
		
		
	}



	
	
	
	
	
	
	
}
