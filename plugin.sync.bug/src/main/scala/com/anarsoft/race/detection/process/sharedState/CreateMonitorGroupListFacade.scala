package com.anarsoft.race.detection.process.sharedState

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.Stack
import com.anarsoft.race.detection.model.result._


object CreateMonitorGroupListFacade {
   def createRoot(stackTraceOrdinalSet : HashSet[Int] , stackTraceGraph : StackTraceGraph,stackTraceGraphPackageAnnotation : StackTraceGraphPackageAnnotation) =
  {
    val filtered  = new HashSet[Int]
    
    val createGroupList = new CreateGroupListMonitor(stackTraceGraphPackageAnnotation);
    
    
    for( s <- stackTraceOrdinalSet )
    {
       
       
       if( createGroupList.take(new StackTraceOrdinal(s)) )
       {
         //println("filtered |" + name + "|");
         
         filtered.add(s);
       }
       
       
      
    }
    
    createGroupList.createChild(filtered.toSet , stackTraceGraph   ).map(  x => x.create(0) )

    
  }
  
  
  
  def create4Child( stackTraceOrdinalSet : Set[Int] , stackTraceGraph : StackTraceGraph,stackTraceGraphPackageAnnotation : StackTraceGraphPackageAnnotation) =
  {
    
     val createGroupList = new CreateGroupListMonitor(stackTraceGraphPackageAnnotation);
      val list =    createGroupList.createChild(stackTraceOrdinalSet , stackTraceGraph   );
      
      val result = new ArrayBuffer[MethodWithMonitor]
      
      
      for(elem <- list)
      {
        if( elem.group != 0 )
        {
          result.append(  elem.create(1) )
        }
        
        
        
      }
      
      result;
      
  }
  
  
  def create4Parent( stackTraceOrdinalSet : Set[Int] , stackTraceGraph : StackTraceGraph,stackTraceGraphPackageAnnotation : StackTraceGraphPackageAnnotation) =
  {
     val createGroupList = new CreateGroupListMonitor(stackTraceGraphPackageAnnotation);
      val list =    createGroupList.createChild(stackTraceOrdinalSet , stackTraceGraph   );
      
      val result = new ArrayBuffer[MethodWithMonitor]
      var element : MethodWithMonitor = null;
      
      for(elem <- list)
      {
        if( elem.group != 0 )
        {
          result.append(  elem.create(1) )
        }
        else
        {
          element =  elem.create(0);
        }
        
        
        
      }
      
       Tuple2(element,result);
  }
}