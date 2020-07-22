package com.vmlens.api.internal.reports

import com.vmlens.api._;
import java.io._;

object WriteYaml {
  
  
  def write(list : Seq[Element4TreeViewer],stream :  PrintStream) 
  {
    for( element <- list )
		{
			write(element,stream,"");
		}
		
    
  }
  
  
  	
	
	
	/*
	 *
	 * 
	 * 
 - variable: Array@1133
   reading:
    thread: Thread-3
    stack:
     - com/anarsoft/test/TestAllTypes.testArrayAccess
     - sun/reflect/NativeMethodAccessorImpl.invoke
     - sun/reflect/DelegatingMethodAccessorImpl.invoke
     - java/lang/reflect/Method.invoke
     - org/junit/runners/model/FrameworkMethod$1.runReflectiveCall
     - org/junit/internal/runners/model/ReflectiveCallable.run
     - org/junit/runners/model/FrameworkMethod.invokeExplosively
     - org/junit/internal/runners/statements/InvokeMethod.evaluate
     - com/anarsoft/vmlens/concurrent/junit/internal/ConcurrentStatement.evaluateStatement
     - com/anarsoft/vmlens/concurrent/junit/internal/ConcurrentStatement.evaluate
     - com/anarsoft/vmlens/concurrent/junit/internal/ParallelExecutorThread.run
  writing:
    thread: Thread-0
    stack:
     - com/anarsoft/test/TestAllTypes.testArrayAccess
     - sun/reflect/NativeMethodAccessorImpl.invoke
     - sun/reflect/DelegatingMethodAccessorImpl.invoke
	 */
	
	
	def write( element : Element4TreeViewer,  stream : PrintStream,  whiteSpace : String)
	{
		 writeElement(element,stream,whiteSpace, 0 );
		
		 var position = 0;
		 
		for(  child <- element.children4Yaml() )
		{
			writeElement(child,stream,ONE_STEP , position );
			
			stream.println( ONE_STEP + ONE_STEP +  "stack:"  )
			
			for( s <- child.children4Yaml() )
			{
			  	stream.println( ONE_STEP + ONE_STEP +  ONE_STEP +  s.name4Yaml()  )
			}
			
			position = position + 1;
			
		}
		
		
		
	}
	
	val  ONE_STEP = "  ";
	
	
	def writeElement( element : Element4TreeViewer,  stream : PrintStream,  whiteSpace : String , position : Int )  =
	{
		
	  element.title4Yaml(position) match
	  {
	    case None =>
	      {
	        
	      }
	    
	    case Some(x) =>
	      {
	        	stream.println( whiteSpace +   x )
	      }
	      
	    
	  }
	  
	  
		
		stream.println( whiteSpace +  whiteSpace +  element.name4Yaml() )
		
		
	}
  
  
  
}