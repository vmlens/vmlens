package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.callback.field.*;




public class FieldAccessCallback {

/*
 * isWrite:false FieldTyp:NON_VOLATILE hasWaitpoint:false result:0
isWrite:true FieldTyp:NON_VOLATILE hasWaitpoint:false result:1
isWrite:false FieldTyp:VOLATILE hasWaitpoint:false result:2
isWrite:true FieldTyp:VOLATILE hasWaitpoint:false result:3

isWrite:false FieldTyp:NON_VOLATILE hasWaitpoint:true result:4
isWrite:true FieldTyp:NON_VOLATILE hasWaitpoint:true result:5

isWrite:false FieldTyp:VOLATILE hasWaitpoint:true result:6
isWrite:true FieldTyp:VOLATILE hasWaitpoint:true result:7

isWrite:false FieldTyp:UNDEFINED hasWaitpoint:false result:8
isWrite:true FieldTyp:UNDEFINED hasWaitpoint:false result:9


isWrite:false FieldTyp:UNDEFINED hasWaitpoint:true result:8
isWrite:true FieldTyp:UNDEFINED hasWaitpoint:true result:9
 */

	
	 private static final Strategy[] strategyArray;

	 static
	 {
		 strategyArray = new Strategy[10];
		 strategyArray[0] = new   StrategyImplNonVolatile(false);
		 strategyArray[1] = new   StrategyImplNonVolatile(true);
		 
		 strategyArray[2] = new   StrategyImplVolatile(false);
		 strategyArray[3] = new   StrategyImplVolatile(true);
	 
	 }
	  
	  
	  
	  
	  public static void field_access_static(int fieldId, int methodId, int callbackId)
	  {
		  strategyArray[callbackId].field_access_static(fieldId, methodId);
	  }
	  
	  
	  public static Long field_access_from_generated_method(Object orig,Long offset , int fieldId, int methodId , int callbackId)
	  {
		  if(orig == null)
		  {
			  return offset;
		  }
		  	
		Long calculatedOffset = offset;
		  
		
	    if( calculatedOffset == null )
	    {
	    	calculatedOffset = UpdateObjectState.getFieldOffset(orig.getClass());
	    }
		  
		  strategyArray[callbackId].field_access_generated(orig, calculatedOffset, fieldId, methodId);
		  
		  
		return calculatedOffset;  
	  }
	  
	
	  public static void field_access(Object orig,  int fieldId, int methodId , int callbackId)
	  {
		  if(orig == null)
		  {
			  return;
		  }
		  
		  
		  strategyArray[callbackId].field_access_default(orig, fieldId, methodId);
	  }





}
