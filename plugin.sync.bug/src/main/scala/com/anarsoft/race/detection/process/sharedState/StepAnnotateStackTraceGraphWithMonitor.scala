package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import com.netflix.nfgraph.build._;
import com.netflix.nfgraph.util._;
import com.netflix.nfgraph.spec.NFPropertySpec._;
import com.netflix.nfgraph.spec._;
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import org.roaringbitmap.IntConsumer


class StepAnnotateStackTraceGraphWithMonitor extends SingleStep[ContextSharedMonitor] {
  
   def execute(context : ContextSharedMonitor)
  {
     
     
     val annotateStackTraceGraph = new AnnotateStackTraceGraphAlgo4Monitor(context.stackTraceGraph);
     val packageName2Ordinal = ModelKey2OrdinalMap[String]();
     
     
     
     val array = annotateStackTraceGraph.annotate(context.stackTraceGraph, packageName2Ordinal)
     val notStateless = new ArrayBuffer[NotStateless]
   

  
  
     
     val stackTraceSchema = new NFGraphSpec(
	        new NFNodeSpec(
		     StackTraceGraphMonitorAnnotation.NODE_STACK_TRACE  ,

		        new NFPropertySpec( StackTraceGraphMonitorAnnotation.REL_STACK_TRACE_2_HAS_MONITOR ,  StackTraceGraphMonitorAnnotation.NODE_HAS_MONITOR , SINGLE | COMPACT) ,
            new NFPropertySpec( StackTraceGraphMonitorAnnotation.REL_STACK_TRACE_2_PACKAGE ,  StackTraceGraphMonitorAnnotation.NODE_ROOT_PACKAGE , MULTIPLE | COMPACT) 

		         ),

		         new NFNodeSpec(StackTraceGraphMonitorAnnotation.NODE_HAS_MONITOR   ) ,
            new NFNodeSpec( StackTraceGraphMonitorAnnotation.NODE_ROOT_PACKAGE   ) 
		        
		  
	   );
     
     val buildGraph = new NFBuildGraph(stackTraceSchema );
  
     
     for(  i <- 0 until array.length )
     {
       val model= array(i);
       
       if( model.calledMethodContainsMonitor )
       {
            buildGraph.addConnection(StackTraceGraphMonitorAnnotation.NODE_STACK_TRACE , i , StackTraceGraphMonitorAnnotation.REL_STACK_TRACE_2_HAS_MONITOR , 1 );
       }
       
       
       
       model.packageIdSet.forEach( new IntConsumer()
       {
         def accept(id : Int)
         {
           buildGraph.addConnection( StackTraceGraphStateAnnotation.NODE_STACK_TRACE  , i , StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_PACKAGE  , id )
         }
         
         
       }  
       )
       
 
     }
     
 
     val packageArray= Array.ofDim[String]( packageName2Ordinal.currentOrdinalId + 1)
     
     for( elem <- packageName2Ordinal.modelKey2Ordinal )
     {
       packageArray(elem._2 ) = elem._1;
     }
     
     
     
    
     context.stackTraceGraphMonitorAnnotation = new StackTraceGraphMonitorAnnotation(buildGraph.compress(),packageArray);
     
     
     
  }
  
  
}