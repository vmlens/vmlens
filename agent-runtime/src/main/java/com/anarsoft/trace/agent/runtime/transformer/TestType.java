package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.Type;



public class TestType {

	public static void main(String[] args) {
		
		
		Type[]  result =  Type.getArgumentTypes("(IIF)V");
		
		
		for(Type t : result )
		{
			//System.out.println(t);
			
			//System.out.println( t.getInternalName() ); fuer arrays
			
		    System.out.println( t.getDescriptor() );
			System.out.println( t.getSort() );
			
			if(  t.getSort() == Type.OBJECT ||  t.getSort() == Type.ARRAY )
			{
				System.out.println( t.getInternalName() );
			}
			
		}
		
		/*
		 * mv.visitFrame(Opcodes.F_FULL, 5, new Object[] {"com/anarsoft/SynchronizedWithException", Opcodes.INTEGER, Opcodes.LONG, "java/lang/String", "[Ljava/lang/String;"}, 1, new Object[] {"java/lang/Throwable"});

		 */
		
		
		
		

	}

}
