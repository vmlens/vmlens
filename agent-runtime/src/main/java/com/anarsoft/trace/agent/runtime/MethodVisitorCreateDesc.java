package com.anarsoft.trace.agent.runtime;

import com.vmlens.shaded.gnu.trove.map.hash.THashMap;




import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.waitPoints.MethodInClassIdentifier;


public class MethodVisitorCreateDesc extends MethodVisitor {

	private int methodCallCount;
	private String methodName;
	private String desc;
	private THashMap<MethodIdentifier,MethodCounts> methodIdentifierSet ;


    private int tryCatchBlockCount = 0;
    private boolean dottyProblematic = false;
    private final ClassVisitorCreateDesc classVisitorCreateDesc;
  


	public MethodVisitorCreateDesc(String name, String desc,
			THashMap<MethodIdentifier,MethodCounts> methodIdentifierSet,ClassVisitorCreateDesc classVisitorCreateDesc) {
		super( AgentClassFileTransformer.ASM_API_VERSION   );
		this.methodName = name;
		this.desc = desc;
		this.methodIdentifierSet = methodIdentifierSet;
		this.classVisitorCreateDesc = classVisitorCreateDesc;
	}
	
	

	

	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean arg1) {
	
		
		
		

		
		

		if("Lcom/vmlens/annotation/DoNotTrace;".equals(name))
		{
			classVisitorCreateDesc.filterList.doNotTraceIn.addFromAnnotation(
			 new MethodInClassIdentifier( classVisitorCreateDesc.className , methodName , desc ) );
			
			// MethodInClassIdentifier(String className, String methodName, String desc) 
			return null;
		}
		

		
		
		
		
		return null;
	}

	

	@Override
	public void visitEnd() {


	
		
		
		
		
		
		
		methodIdentifierSet.put(new MethodIdentifier(methodName,desc),new MethodCounts(tryCatchBlockCount,methodCallCount,dottyProblematic));



	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		if( "sun/misc/Unsafe".equals( owner ))
		{
			classVisitorCreateDesc.hasUnsafeAccess = true;
		}

	}

	

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc,boolean isInterface)
	{
		//  INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.


		
		if( "sun/misc/Unsafe".equals( owner ))
		{
			classVisitorCreateDesc.hasUnsafeAccess = true;
		}
		
		/*
		 * INVOKESPECIAL sind einmal konstruktor aufrufe aber
		 * auch aufrufe in der selbend klasse.
		 *  ||  opcode == Opcodes.INVOKESTATIC
		 *  
		 *  
		 *  
		 */

		if(  opcode == Opcodes.INVOKEVIRTUAL ||  opcode == Opcodes.INVOKEINTERFACE  ||  opcode == Opcodes.INVOKESTATIC || opcode == Opcodes.INVOKESPECIAL )
		{
			methodCallCount++;
		}

	}

	
	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2,
			String arg3) {
		tryCatchBlockCount++;

	}





	@Override
	public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
		
		// mv.visitFrame(Opcodes.F_NEW, 0, new Object[] {}, 1, new Object[] {"java/lang/Throwable"});
	
		
		
		if(  type ==  Opcodes.F_NEW &&   numLocal == 0  && numStack == 1  )
		{
			
			if(  "java/lang/Throwable".equals(stack[0]) )
			{
				
				dottyProblematic = true;
			}
		}
		
		
		
		
	}

	

}
