package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.MethodIdentifier;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;



public class ClassTransformerTraceMethod extends ClassVisitor {


	private THashMap<MethodIdentifier,FactoryMethodTransformer> methodName2Transformer;// = new THashMap<MethodIdentifier,MethodAdapter>();

	public ClassTransformerTraceMethod(
			ClassVisitor cv,
			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer) {
		super( AgentClassFileTransformer.ASM_API_VERSION, cv);
		this.methodName2Transformer = methodName2Transformer;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		 MethodIdentifier i = new  MethodIdentifier( name,  desc) ;

		 FactoryMethodTransformer factoryMethodTransformer =  methodName2Transformer.get(i);

        if (factoryMethodTransformer != null) {
			 return factoryMethodTransformer.create(super.visitMethod(access, name, desc, signature, exceptions));
		 } else {
			 return super.visitMethod(access, name, desc, signature, exceptions);
		 }
	}
}
