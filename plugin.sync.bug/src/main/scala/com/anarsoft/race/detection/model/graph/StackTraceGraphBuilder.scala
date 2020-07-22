package com.anarsoft.race.detection.model.graph

import com.netflix.nfgraph.build._;
import com.netflix.nfgraph.util._;
import com.netflix.nfgraph.spec.NFPropertySpec._;
import com.netflix.nfgraph.spec._;
import  com.anarsoft.race.detection.model.method._;
import com.anarsoft.race.detection.model.field._;
import com.anarsoft.race.detection.model.result.StackTraceGraph
import scala.collection.mutable.HashSet;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.result._;

//  , val methodId2Ordinal : ModelKey2OrdinalMap[Int]

class StackTraceGraphBuilder(val nfsBuildGraph : NFBuildGraph,val methodId2Ordinal :  ModelKey2OrdinalMap[Int])
{
  
  val rootSet = new HashSet[Int]();

  
  def setHasMonitor( ordinal : Int)
  {
     nfsBuildGraph.addConnection(StackTraceGraph.NODE_STACK_TRACE , ordinal , StackTraceGraph.REL_STACK_TRACE_2_HAS_MONITOR , 1 );
  }
    
  
  
  def addThreadOrdinal2StackTrace( threadOrdinal : Int, stackTraceOrdinal : Int )
  {
       nfsBuildGraph.addConnection(StackTraceGraph.NODE_STACK_TRACE , stackTraceOrdinal ,StackTraceGraph.REL_STACK_TRACE_2_THREAD , threadOrdinal);
  }
  
  
  var maxStackTraceNodeOrdinal = 0;
  
  
  def addStackTraceNode(stackTraceGraphNode : StackTraceGraphNode, ordinal : Int )
  {
    maxStackTraceNodeOrdinal = Math.max( maxStackTraceNodeOrdinal  , stackTraceGraphNode.parentOrdinal  )
    maxStackTraceNodeOrdinal = Math.max( maxStackTraceNodeOrdinal  ,ordinal  )
    
    
    if( stackTraceGraphNode.parentOrdinal != StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT )
    {
      
     
       
      nfsBuildGraph.addConnection(StackTraceGraph.NODE_STACK_TRACE ,  stackTraceGraphNode.parentOrdinal ,StackTraceGraph.REL_STACK_TRACE_CHILD , ordinal);
      nfsBuildGraph.addConnection(StackTraceGraph.NODE_STACK_TRACE ,   ordinal ,StackTraceGraph.REL_STACK_TRACE_PARENT , stackTraceGraphNode.parentOrdinal);
      
      
    }
    else
    {
      if( ordinal != 0)
      {
         rootSet.add(ordinal);
      }
     
      
    }
    
    val methodOrdinal = methodId2Ordinal.getOrAddOrdinal(stackTraceGraphNode.methodId);
    

    
    nfsBuildGraph.addConnection(StackTraceGraph.NODE_STACK_TRACE ,   ordinal ,StackTraceGraph.REL_STACK_TRACE_2_METHOD , methodOrdinal);
    nfsBuildGraph.addConnection(StackTraceGraph.NODE_METHOD ,   methodOrdinal ,StackTraceGraph.REL_METHOD_2_STACK_TRACE , ordinal);
    
 
  }
  
  
  def addArrayOrdinal2Method(arrayOrdinal : Int, methodOrdinal : Int)
  {
       nfsBuildGraph.addConnection(StackTraceGraph.NODE_ARRAY ,   arrayOrdinal ,StackTraceGraph.REL_ARRAY_2_METHOD , methodOrdinal);
  }
  
  
  
 def create() =
  {
    
     val methodOrdinal2MethodModel = Array.ofDim[MethodModel](methodId2Ordinal.modelKey2Ordinal.size )
    
     // 
     //val nf =  nfsBuildGraph.compress();
     new StackTraceGraph(  nfsBuildGraph.compress() , methodOrdinal2MethodModel   , rootSet , maxStackTraceNodeOrdinal );
  }
 
  
  
}


object StackTraceGraphBuilder {

  
  
  val PARENT_ORDINAL_OF_ROOT = -1;
  val UNKNOWN_METHOD_ID = 0;
  
  
  
  
  
  
  
  
  
  def apply(methodId2Ordinal :  ModelKey2OrdinalMap[Int]) =
  {
      val stackTraceSchema = new NFGraphSpec(
	        new NFNodeSpec(
		     StackTraceGraph.NODE_STACK_TRACE  ,
		        new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_CHILD ,  StackTraceGraph.NODE_STACK_TRACE , MULTIPLE | COMPACT) ,
		        new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_PARENT ,  StackTraceGraph.NODE_STACK_TRACE , SINGLE | COMPACT), 
		        new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_2_METHOD ,  StackTraceGraph.NODE_METHOD , SINGLE | COMPACT) ,
		        new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_2_SHARED_STATE ,  StackTraceGraph.NODE_SHARED_STATE , MULTIPLE | COMPACT) ,
		        new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_2_HAS_MONITOR ,  StackTraceGraph.NODE_HAS_MONITOR , SINGLE | COMPACT) ,
		        
		          new NFPropertySpec( StackTraceGraph.REL_STACK_TRACE_2_THREAD ,  StackTraceGraph.NODE_THREAD , MULTIPLE | COMPACT) 

		         ),

		         new NFNodeSpec(
		     StackTraceGraph.NODE_METHOD  ,
		        new NFPropertySpec( StackTraceGraph.REL_METHOD_2_STACK_TRACE ,  StackTraceGraph.NODE_STACK_TRACE , MULTIPLE | COMPACT)  ) ,
		     
		        
		          new NFNodeSpec(
		     StackTraceGraph.NODE_SHARED_STATE  ,
		        new NFPropertySpec( StackTraceGraph.REL_SHARED_STATE_2_STACK_TRACE ,  StackTraceGraph.NODE_STACK_TRACE , MULTIPLE | COMPACT)  ) ,
		        
		     
		        
		               new NFNodeSpec(  StackTraceGraph.NODE_ARRAY , 
		        new NFPropertySpec( StackTraceGraph.REL_ARRAY_2_METHOD ,  StackTraceGraph.NODE_METHOD , MULTIPLE | COMPACT)     ) ,
		        
		        
		            new NFNodeSpec(  StackTraceGraph.NODE_HAS_MONITOR ) ,
		            
		                new NFNodeSpec(  StackTraceGraph.NODE_THREAD )
	   );
     // , methodId2Ordinal
     // 
     
     
      new StackTraceGraphBuilder(new NFBuildGraph(stackTraceSchema ), methodId2Ordinal );
      
      
   //   new NFBuildGraph(stackTraceSchema );
  }
  
  
  
  
  
  
}