package com.anarsoft.trace.agent.runtime.write;


import com.vmlens.trace.agent.bootstrap.callback.CallbackState;



import com.anarsoft.trace.agent.serialization.ClassDescription;

public class WriteClassDescriptionNormal implements WriteClassDescription {

	
	
	
	public  void write(final ClassDescription classDescription)
	{
		
		if(CallbackState.writeClassDescription())
		{
			CallbackState.queueFacade.putDirect  ( 	 	 
					  classDescription					
							
						
					 
					 );
			
		}
		
		
	}

	
}
