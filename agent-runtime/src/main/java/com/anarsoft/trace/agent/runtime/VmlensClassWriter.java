package com.anarsoft.trace.agent.runtime;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class VmlensClassWriter extends ClassWriter {

	
	

	public VmlensClassWriter() {
		super( ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	
	}




	@Override
	protected String getCommonSuperClass(String type1, String type2) {
		
		return "java/lang/Object";
	}
	
	

}
