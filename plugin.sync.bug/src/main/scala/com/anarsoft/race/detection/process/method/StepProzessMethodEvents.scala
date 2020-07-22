package com.anarsoft.race.detection.process.method



import java.util.Comparator;
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.SortArrayList;



class StepProzessMethodEvents(val additionalOpOnParallizedMethodEnter : ( ParallizedMethodEnterEvent , ContextMethodData ) => Unit, val   additionalOpOnMethodInParallizeBlock : (MethodEvent , Int,ContextMethodData) => Unit ) extends SingleStep[ContextMethodData] {
  
  
  
  def execute(context : ContextMethodData)
  {
   
    
    if( context.currentReadMethodEvents   != null)
    {
          
 SortArrayList.sort(   context.currentReadMethodEvents , new Comparator[MethodEvent]()
    {
      def  compare(o1 : MethodEvent,o2 : MethodEvent) =
      {
        
        if(   o1.threadId !=  o2.threadId )
        {
          java.lang.Long.compare( o1.threadId  , o2.threadId   );
        }
        else
        {
          
           java.lang.Integer.compare( o1.methodCounter  , o2.methodCounter   );
          
        }
        
        
        
        
        
      }
    }
    
    )
    
    
    
    
    val iter = context.currentReadMethodEvents.iterator();
    
    var currentThreadId = -1L;
    var currentBuilder : StackTraceForestPerThreadBuilder = null;
    var currentMethodFlow : MethodFlowBlock = null;
    
    
    while( iter.hasNext() )
    {
      
      val current = iter.next();
      
      
    
      
      
      
//      if( current.isMethodEnter() )
//      {
//          
//        context.methodIdAndThreadIdSet.add(  new MethodIdAndThreadId( current.methodId() , current.threadId )  );
//      }
//        
      
      
      
      if( currentThreadId != current.threadId )
      {
        currentThreadId = current.threadId ;
        
        currentBuilder = context.threadId2StackTraceForestPerThreadBuilder.get(currentThreadId) match
      {
        case Some(x) => x;
        
        case None =>
          {
            
            
            val x = new StackTraceForestPerThreadBuilder( context.threadNames.threadId2ThreadOrdinal.get(currentThreadId) .get , context.threadOrdinalAndStackTraceSet  );
             context.threadId2StackTraceForestPerThreadBuilder.put(currentThreadId,x) 
            
            x;
          }
        
        
      }
        
        currentMethodFlow = context.methodFlow.createMethodFlowBlock(currentThreadId,currentBuilder.start)
        
        
      }
      
    
     
      
  
        currentBuilder.visit(current, currentMethodFlow , context.stackTraceTree,context.methodId2Ordinal , context , additionalOpOnParallizedMethodEnter, additionalOpOnMethodInParallizeBlock);
      
      
      
      
    }
    
    }
    

    
    
  }
  
  
  def desc = "";
  
}