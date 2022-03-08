package com.vmlens.trace.agent.bootstrap.callback;



import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.trace.agent.bootstrap.UndefinedFieldRepository;
import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callback.field.CallbackStatic;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;


public class UndefinedFieldAccessCallback {

	  public static void field_access_static_read( int undefinedId,  int methodId)
	  {
		  field_access_static(     undefinedId, methodId, false);
	  }
	  
	  
	  public static void field_access_static_write(  int undefinedId , int methodId)
	  {
		  field_access_static(    undefinedId, methodId, true);
	  }
	
	  
	  
	  public static void field_access_read(Object orig, int undefinedId , int methodId )
	  {
		  if(orig == null)
		  {
			  return;
		  }
		  
		  field_access_default( orig,     undefinedId,  methodId, false); 
		
	  }
	  
	  
	  public static void field_access_write(Object orig , int undefinedId ,  int methodId)
	  {
		  if(orig == null)
		  {
			  return;
		  }
		  
		  field_access_default( orig,    undefinedId,methodId,  true); 
		
	  }
	  
	  
	  
	  private static int getOperation(boolean isWrite)
		{
			if(isWrite)
			{
				return MemoryAccessType.IS_WRITE;
			}
			else
			{
				return MemoryAccessType.IS_READ;
			}
		}
		
	  
	  
	  
	
		private static void field_access_default(Object orig,  int undefinedId,int methodId,boolean isWrite) {
			
			FieldIdAndTyp fieldIdAndTyp = UndefinedFieldRepository.unknownIdArray.getOrCreateElement(undefinedId);
			  
			     
			      
					if( fieldIdAndTyp.fieldTyp == FieldTyp.FINAL)	
					{
						return;
					}
					
					if(fieldIdAndTyp.fieldTyp == FieldTyp.VOLATILE)
					{
						CallbackObject.volatile_access(orig, fieldIdAndTyp.id, methodId, getOperation(isWrite));
					}
					else
					{	
						CallbackObject.non_volatile_access(orig,  fieldIdAndTyp.id, methodId, isWrite);
						
					}
		}




	
		private static void field_access_static(   int undefinedId,int methodId,boolean isWrite) {
		
			
			FieldIdAndTyp fieldIdAndTyp = UndefinedFieldRepository.unknownIdArray.getOrCreateElement(undefinedId);
			  
				if( fieldIdAndTyp.fieldTyp == FieldTyp.FINAL)	
				{
					return;
				}
				
				if(fieldIdAndTyp.fieldTyp == FieldTyp.VOLATILE)
				{
					CallbackStatic.volatile_access(fieldIdAndTyp.id, methodId, isWrite);
				}
				else  
				{
					CallbackStatic.non_volatile_access(fieldIdAndTyp.id, methodId, isWrite);
				}
			
	
		}

	  
	  
	  
	
}
