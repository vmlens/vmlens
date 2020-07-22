package com.anarsoft.trace.agent.runtime;

import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

import org.objectweb.asm.AnnotationVisitor;

public class AnnotationVisitorReadArray extends AnnotationVisitor {

	
	
	private final THashSet<String> excludes;
	
	public AnnotationVisitorReadArray(THashSet<String> excludes) {
		super(  AgentClassFileTransformer.ASM_API_VERSION );
		this.excludes = excludes;
	}

	@Override
	public void visit(String arg0, Object arg1) {
		
		excludes.add(arg1.toString());
		
		
	}
	
}
