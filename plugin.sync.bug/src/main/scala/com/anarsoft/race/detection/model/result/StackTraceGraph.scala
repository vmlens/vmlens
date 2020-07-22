package com.anarsoft.race.detection.model.result

import com.netflix.nfgraph.compressed.NFCompressedGraph
import com.anarsoft.race.detection.model.description._
import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Stack;
import com.netflix.nfgraph.OrdinalIterator;
import scala.collection.mutable.HashMap;

class StackTraceGraph(val graph : NFCompressedGraph, 
     val methodOrdinal2Model : Array[MethodModel], 
    val rootSet : HashSet[Int],val maxStackTraceOrdinal : Int) extends Graph
{
  
  def foreachMethodOrdinal( f : (  Int ,  MethodModel ) => Unit  )
  {
    
    var index = 0;
    
    for(  model <-  methodOrdinal2Model )
    {
      
      f(  index, model );
      
      index = index + 1;
      
    }
  }
  
  
  def hasMonitor( stackTraceOrdinal : StackTraceOrdinal )  =
  {
     
    graph.getConnection( StackTraceGraph.NODE_STACK_TRACE, stackTraceOrdinal.ordinal, StackTraceGraph.REL_STACK_TRACE_2_HAS_MONITOR) != -1;
    
    
  }
  
  
  def foreachThreadOrdinal(stackTraceOrdinal : StackTraceOrdinal , f : Int => Unit)
   = foreachOrdinal( StackTraceGraph.NODE_STACK_TRACE, stackTraceOrdinal.ordinal  , StackTraceGraph.REL_STACK_TRACE_2_THREAD ,     
   x => f( x )
  );
  
  
  def foreachMemoryAggregate(stackTraceOrdinal : StackTraceOrdinal, f : Int => Unit )
   = foreachOrdinal( StackTraceGraph.NODE_STACK_TRACE, stackTraceOrdinal.ordinal  , StackTraceGraph.REL_STACK_TRACE_2_SHARED_STATE ,     
   x => f( x )
  );
  
  
  
  def foreachStacktraceOrdinalForMemoryAggregate( memoryAggregateOrdinal : Int , f : (StackTraceOrdinal) => Unit ) 
     = foreachOrdinal( StackTraceGraph.NODE_SHARED_STATE, memoryAggregateOrdinal  , StackTraceGraph.REL_SHARED_STATE_2_STACK_TRACE ,     
   x => f( new StackTraceOrdinal(x) )
  );
  
  
  
  
  
  
  def foreachStackTraceNodeOrdinal(methodOrdinal : MethodOrdinal, f : StackTraceOrdinal => Unit )
   = foreachOrdinal( StackTraceGraph.NODE_METHOD, methodOrdinal.ordinal  , StackTraceGraph.REL_METHOD_2_STACK_TRACE ,     
   x => f( new StackTraceOrdinal(x) )
  );

  
  
  
  def foreachStackTraceNodeChild(parent : StackTraceOrdinal , f : StackTraceOrdinal => Unit)
   = foreachOrdinal( StackTraceGraph.NODE_STACK_TRACE, parent.ordinal  , StackTraceGraph.REL_STACK_TRACE_CHILD ,     
   x => f( new StackTraceOrdinal(x) )
  );
  
  
  
  
  def foreachMethodOrdinalForArray(arrayOrdinal : Int, f : Int => Unit)
  {
     foreachOrdinal( StackTraceGraph.NODE_ARRAY, arrayOrdinal  , StackTraceGraph.REL_ARRAY_2_METHOD ,     
   x => f( x )
  )
    
    
  }
  
  
  
  
  

  def onParent(childOrdinal : Int, f : StackTraceOrdinal => Unit)
  {
      foreachOrdinal(StackTraceGraph.NODE_STACK_TRACE, childOrdinal  , StackTraceGraph.REL_STACK_TRACE_PARENT, x =>  { 
         
       if( x != -1 )
       {
         f (new StackTraceOrdinal(x));
       }
         
          }  )
    
    
  }
  
  
  
  
  
  
  def formHereToRoot (stackTraceNodeOrdinal : StackTraceOrdinal, f : StackTraceOrdinal => Unit)
  {
    var current : Option[StackTraceOrdinal] = new Some(stackTraceNodeOrdinal);
    
    while( ! current.isEmpty )
    {
       val node = current.get;
       
       f(node)
       
       current = None;
      
       foreachOrdinal(StackTraceGraph.NODE_STACK_TRACE, node.ordinal  , StackTraceGraph.REL_STACK_TRACE_PARENT, x =>  { 
         
       if( x != -1 )
       {
          current = new Some(new StackTraceOrdinal(x));
       }
         
          }  )
    
    }
      
    
    
    
  }
  
  
  def getSecondaryMethodModel(stackTraceOrdinal : Int) =
  {
    var current = stackTraceOrdinal;
    
        foreachOrdinal(StackTraceGraph.NODE_STACK_TRACE, current  , StackTraceGraph.REL_STACK_TRACE_PARENT, x =>  { 
           if( x != -1 )
       {
          current = x;
       }
        })
        
        getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal( current));
  }
  
  
  
 
  
  def getMethodModelForStackTraceNodeOrdinal(stackTraceNodeOrdinal : StackTraceOrdinal) =
  {
    if(   graph.getConnection(StackTraceGraph.NODE_STACK_TRACE, stackTraceNodeOrdinal.ordinal, StackTraceGraph.REL_STACK_TRACE_2_METHOD)  == -1 )
    {
      System.err.print("not found model for " +stackTraceNodeOrdinal.ordinal )
      new MethodModelFromTrace("unkown",  
      "unknown", "", 0, 0, ClassModel());
      
    }
    else
    {
       val result =  methodOrdinal2Model ( graph.getConnection(StackTraceGraph.NODE_STACK_TRACE, stackTraceNodeOrdinal.ordinal, StackTraceGraph.REL_STACK_TRACE_2_METHOD) );
       
       if( result == null)
       {
          System.err.print("not found model for " +stackTraceNodeOrdinal.ordinal )
      new MethodModelFromTrace("unkown",  
      "unknown", "", 0, 0 , ClassModel());
       }
       else
       {
         result;
       }
       
    }
    
    
  
  }
  
   def getMethodModelOrdinalForStackTraceNodeOrdinal(stackTraceNodeOrdinal : StackTraceOrdinal) =
  {
     new MethodOrdinal( graph.getConnection(StackTraceGraph.NODE_STACK_TRACE, stackTraceNodeOrdinal.ordinal, StackTraceGraph.REL_STACK_TRACE_2_METHOD)) ;
  }
  
   
 
   
   def getMethodModelForOrdinal(ordinal : Int ) = {
       val result = methodOrdinal2Model(ordinal);
       
       if( result == null)
       {
         new MissingLink();
       }
       else
       {
         result;
       }
       
   }
   
   
   // for lock names
   
    def isLockName(name : String) =
      {
        if( name.startsWith("java.util.concurrent.locks") )
        {
          true
        }
        else  if( name.startsWith("java.util.concurrent.CountDownLatch") )
        {
          true
        }  
        else if( name.startsWith("java.util.concurrent.CyclicBarrier") )
        {
          true
        }  
        else if( name.startsWith("java.util.concurrent.Semaphore") )
        {
          true
        } 
        else
        {
          false
        }
        
        
      }
   
   
    def methodModel4Lock(stackTraceOrdinal : Int) = 
    {
      var firstStackTraceOrdinal: Option[StackTraceOrdinal] = None;
      
     formHereToRoot(new StackTraceOrdinal(stackTraceOrdinal), x => {
         
         firstStackTraceOrdinal match
         {
           case None =>
             {
                if(! isLockName( getMethodModelForStackTraceNodeOrdinal(x).getFullName()) )
            {
             firstStackTraceOrdinal  = Some(x);
            }
         
               
               
             }
           case Some(x) =>
             {
               
             }
           
           
         }
         
         
           
         
         
       })
      
      getMethodModelForStackTraceNodeOrdinal(firstStackTraceOrdinal.get)
      
    }
   
    
    def operationText4Lock(stackTraceOrdinal : Int)  =
   {
     var lastStackTraceOrdinal = new StackTraceOrdinal(stackTraceOrdinal);
     
    formHereToRoot(new StackTraceOrdinal(stackTraceOrdinal), x => {
         
            if(isLockName( getMethodModelForStackTraceNodeOrdinal(x).getFullName()) )
            {
              lastStackTraceOrdinal = x;
            }
         
         
         
       })
     
      
   getMethodModelForStackTraceNodeOrdinal(lastStackTraceOrdinal).getFullNameWithBoldName()
   } 
    
    
   
}



object StackTraceGraph {
   
  
  val NODE_STACK_TRACE = "stackTrace"; 
  val NODE_METHOD = "method";
  val NODE_ARRAY = "array";
  val NODE_SHARED_STATE = "sharedState"
  val NODE_HAS_MONITOR = "hasMonitor";
 
  val NODE_THREAD = "thread"; 
//  val NODE_THREAD_COUNT = "threadCount";
  
  
  
  val REL_STACK_TRACE_CHILD    = "stackTraceChild";
  val REL_STACK_TRACE_PARENT   = "stackTraceParent";
  val REL_STACK_TRACE_2_METHOD = "stackTrace2Method";
  val REL_METHOD_2_STACK_TRACE = "method2StackTrace";
  
  val REL_ARRAY_2_METHOD = "array2Method";

  
  val REL_SHARED_STATE_2_STACK_TRACE = "sharedState2StackTrace";
  val REL_STACK_TRACE_2_SHARED_STATE = "stackTrace2SharedState"

  val REL_STACK_TRACE_2_HAS_MONITOR = "stackTrace2hasMonitor";
  
  val REL_STACK_TRACE_2_THREAD  = "stackTrace2thread";
 // val REL_STACK_TRACE_2_THREAD_COUNT  = "stackTrace2threadCount";

  
  
  
}