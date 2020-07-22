package com.anarsoft.trace.agent.runtime;

import org.objectweb.asm.AnnotationVisitor;


import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import java.util.Iterator;



public class AnnotationVisitorOwner  extends AnnotationVisitor {

	
	 
		private final ClassVisitorCreateDesc classVisitorCreateDesc;
		private final THashSet<String> excludes = new THashSet<String>();
		
		
		
	 
	
	public AnnotationVisitorOwner(ClassVisitorCreateDesc classVisitorCreateDesc) {
			super(  AgentClassFileTransformer.ASM_API_VERSION );
			this.classVisitorCreateDesc = classVisitorCreateDesc;
		}

	

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnotationVisitor visitArray(String arg0) {
		
		
		
		return new AnnotationVisitorReadArray(excludes);
	}

	@Override
	public void visitEnd() {
	
		classVisitorCreateDesc.except = new String[excludes.size()];
		Iterator<String> iter = excludes.iterator();
		
		int index = 0;
		
		while( iter.hasNext() )
		{
			classVisitorCreateDesc.except[index] = iter.next();
			
		    index++;
			
			
		}
		
		
		
	}

	@Override
	public void visitEnum(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

}
