package com.anarsoft.race.detection.process.method

import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.method._;
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap;
import scala.collection.mutable.ArrayBuffer;

class StackTraceTree {
  
    var currentOrdinalId = 0;
  
     val methodId2RootNode  = new  HashMap[Int,Int]();
 //  val stackTrace2OrdinalMap =   ModelKey2OrdinalMap( new StackTraceGraphNode(0,-1)); 
   val stackTraceGraphNode2Ordinal  = new HashMap[StackTraceGraphNode,Int]
   val stackTraceOrdinal2MethodId = new ArrayBuffer[Int]();
   
   stackTraceOrdinal2MethodId.append(-1);

   stackTraceGraphNode2Ordinal.put(  new StackTraceGraphNode(0,-1) , 0);
    
    def getOrAddOrdinal(mode_key : StackTraceGraphNode) =
  {
      
    stackTraceGraphNode2Ordinal.get(mode_key) match
    {
      
      case None =>
        {
          currentOrdinalId = currentOrdinalId + 1;
          stackTraceGraphNode2Ordinal.put(mode_key , currentOrdinalId );
          
          stackTraceOrdinal2MethodId.append( mode_key.methodId );
          
          currentOrdinalId;
          
          
          
        }
      
      case Some(x) => x; 
      
      
    }
  }
  
  
  def getOrdinal(mode_key : StackTraceGraphNode) = stackTraceGraphNode2Ordinal.get(mode_key) ;
  
  
 // def getMethodId4StackTraceordinal( ordinal : Int  ) =  stackTraceOrdinal2MethodId(ordinal);
   
   
   def getStackTraceOrdinalInternal( methodId : Int , parentOrdinal : Int ) =
  {
    val node = new StackTraceGraphNode( methodId ,  parentOrdinal);
    
     getOrAddOrdinal(node);
    
  }
  
  
}