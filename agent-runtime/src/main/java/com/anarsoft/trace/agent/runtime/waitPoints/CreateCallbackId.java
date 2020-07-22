package com.anarsoft.trace.agent.runtime.waitPoints;

import com.vmlens.trace.agent.bootstrap.FieldTyp;



public class CreateCallbackId {

	
//	
//	private static int createNormalOrVolatileId(boolean isWrite, boolean isVolatile)
//	{
//		int resultingId = 0;
//		
//		if( isWrite )
//		{
//			resultingId = 1;
//		}
//		
//		if(  isVolatile )
//		{
//			resultingId = resultingId | 2;
//		}
//		
//		
//		
//		return resultingId;
//		
//		
//	}
//	
	
	
	
	public static int create(boolean isWrite, FieldTyp fieldTyp )
	{
		
	return createInternal(isWrite, fieldTyp);
	
	}
	
	
	
	private static int createNormalOrVolatileId(boolean isWrite, boolean isVolatile)
	{
		int resultingId = 0;
		
		if( isWrite )
		{
			resultingId = 1;
		}
		
		if(  isVolatile )
		{
			resultingId = resultingId | 2;
		}
		
		
		
		return resultingId;
		
		
	}
	
	
	
	
	private static int createInternal(boolean isWrite, FieldTyp fieldTyp)
	{
	
		
		switch(fieldTyp)
		{
		  case NON_VOLATILE :
		  {
			return createNormalOrVolatileId(isWrite , false );
			
		  }
		 
		  
		  case VOLATILE:
		  {
			  return createNormalOrVolatileId(isWrite , true );
		  }
		
		  

		  
		
		
		
		
		}
		
		
		
		throw  new RuntimeException("should not happen");
		
		
	}
	
	
//	
//	
//	private static void createAndPrint(boolean isWrite, FieldTyp fieldTyp)
//	{
//		int result = create(isWrite, fieldTyp);
//		
//		System.out.println("isWrite:" + isWrite + " FieldTyp:" + fieldTyp.name()  + " result:" + result  );
//	}
//	
//	
//	
//	public static void main(String[] args) {
//		
//		createAndPrint(false, FieldTyp.NON_VOLATILE)   ;
//		createAndPrint(true, FieldTyp.NON_VOLATILE)    ;
//		
//		createAndPrint(false, FieldTyp.NON_VOLATILE)    ;
//		createAndPrint(true, FieldTyp.NON_VOLATILE)    ;
//		
//		
//		createAndPrint(false, FieldTyp.VOLATILE)    ;
//		createAndPrint(true, FieldTyp.VOLATILE)    ;
//		
//		createAndPrint(false, FieldTyp.VOLATILE)    ;
//		createAndPrint(true, FieldTyp.VOLATILE)    ;
//		
//		
//		
//		createAndPrint(false, FieldTyp.UNDEFINED)    ;
//		createAndPrint(true, FieldTyp.UNDEFINED)    ;
//		
//		createAndPrint(false, FieldTyp.UNDEFINED)    ;
//		createAndPrint(true, FieldTyp.UNDEFINED)    ;
//	}

}
