package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.model.result.ModelFacadeState
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.model.result.MethodWithSharedState
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.model.description.ThreadNames;
import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model._;
import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import java.util.ArrayList
import com.anarsoft.race.detection.model.result.MethodWithSharedState
import com.anarsoft.race.detection.process.nonVolatileField.NonVolatileFieldId
import org.roaringbitmap.RoaringBitmap;

trait ContextCreateModelFacadeMonitor {
    
  var modelFacadeMonitor : ModelFacadeMonitor = null;
  
  def  ownerId2Name : HashMap[Int,String]
  

 def  sharedStateCollection : ArrayBuffer[MethodWithMonitor]
 
   def threadNames : ThreadNames;
  def stackTraceGraph : StackTraceGraph;
  
  def  deadlocks : HashSet[Deadlock];
  
  def stackTraceGraphMonitorAnnotation : StackTraceGraphMonitorAnnotation;
  
  
  /**
   * damit das nicht automatisch gelöscht wird, ansonsten tritt nullpointer exception auf:
   * 
   * java.lang.NullPointerException
    at com.anarsoft.race.detection.process.result.StepCreateStackTraceGraph.execute (StepCreateStackTraceGraph.scala:37)
    at com.anarsoft.race.detection.process.result.StepCreateStackTraceGraph.execute (StepCreateStackTraceGraph.scala:8)
    at com.anarsoft.race.detection.process.workflow.SingleStep$class.execute (SingleStep.scala:14)
    at com.anarsoft.race.detection.process.result.StepCreateStackTraceGraph.execute (StepCreateStackTraceGraph.scala:8)
    at com.anarsoft.race.detection.process.workflow.ProcessPipeline$$anonfun$execute$1.apply (ProcessPipeline.scala:243)
    at com.anarsoft.race.detection.process.workflow.ProcessPipeline$$anonfun$execute$1.apply (ProcessPipeline.scala:108)
    at scala.collection.mutable.ResizableArray$class.foreach (ResizableArray.scala:59)
    at scala.collection.mutable.ArrayBuffer.foreach (ArrayBuffer.scala:48)
    at com.anarsoft.race.detection.process.workflow.ProcessPipeline.execute (ProcessPipeline.scala:108)
    at com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor.createAndExecutePipeline (ProcessMonitor.scala:165)
    at com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor.createAndExecutePipeline (ProcessMonitor.scala:62)
    at com.anarsoft.race.detection.process.ProcessTemplate.prozessWithMonitor (ProcessTemplate.scala:222)
    at com.anarsoft.race.detection.process.ProcessTemplate.prozessMavenPlugin (ProcessTemplate.scala:169)
    at com.vmlens.api.callback.APICallbackSharedImpl.prozessMonitor (APICallbackSharedImpl.scala:18)
    at com.vmlens.maven.plugin.VMLensPlugin.processShared (VMLensPlugin.java:188)
    at com.vmlens.maven.plugin.VMLensPlugin.execute (VMLensPlugin.java:96)
   * 
   * 
   */
  
   def stackTraceOrdinalWithMonitor: RoaringBitmap;
  
     def agentLog : ArrayBuffer[String];
}