package com.anarsoft.race.detection.process.result


import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class StepCreateStackTraceGraph  extends SingleStep[ContextCreateStackTraceGraph]{
  
  
  def execute(context : ContextCreateStackTraceGraph)
  {
    ;
    
      for( e <- context.stackTraceTree.stackTraceGraphNode2Ordinal )
     {
        

        context.stackTraceGraphBuilder.addStackTraceNode(e._1, e._2);
           
     }
      
   
      
      context.stackTraceOrdinalWithMonitorOption match
      {
        
        case None =>
          {
            
          }
        
        case Some(x) =>
          {
             val setHasMonitor = new SetHasMonitor(context);
      
              x.forEach(  setHasMonitor );
          }
        
        
      }
      
      
    
      
      
     
     
     
     val stackTrace2ThreadOrdinalSet = new HashMap[Int,ArrayBuffer[Int]]
     
     
     for( elem <- context.threadOrdinalAndStackTraceSet )
     {
          val set = stackTrace2ThreadOrdinalSet.getOrElseUpdate( elem.stackTraceOrdinal , new ArrayBuffer[Int]);
          set.append( elem.threadOrdinal )
     }
     
     
     for( x<- stackTrace2ThreadOrdinalSet )
     {
       for( t <- x._2 )
       {
           context.stackTraceGraphBuilder.addThreadOrdinal2StackTrace(  t , x._1);
       }
       
       
 
       
     }
     
     
     
     
       //  context.methodId2Ordinal =  context.stackTraceGraphBuilder.methodId2Ordinal;
    
        context.stackTraceGraph =    context.stackTraceGraphBuilder.create();
    
        
//        println("please generate Heap Dump");
//        
//        Thread.sleep(1000 * 60 * 2)
        
    
  }
  
  def desc = "";
  
  
  
}