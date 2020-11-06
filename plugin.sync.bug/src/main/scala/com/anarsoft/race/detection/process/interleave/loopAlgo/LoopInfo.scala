package com.anarsoft.race.detection.process.interleave.loopAlgo

import com.anarsoft.race.detection.process.interleave._;
import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import scala.collection.mutable.HashSet
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import  com.vmlens.trace.agent.bootstrap.util.LoopResultStatusCodes


class LoopInfo(val id: Int) extends LoopOrRunEventVisitor[Option[LoopIdRunId]] {

  val runMap = new GenericMap[RunInfo]((id) => { new RunInfo(id) })
 
  var hasWarning = false;
  var statusFromLoopEnd = LoopResultStatusCodes.OK;
  
  
  
  def addRace(read: InterleaveEventNonVolatileAccess) {
    runMap.getOrCreate(read.runId).addRace(read)
  }

  def add(event: LoopOrRunEvent) = {
    event.accept(this)
  }

  def add(event: InterleaveEventStatement) = {
     runMap.getOrCreate(event.runId).add(event)
  }

    def addMonitorEvent(event: InterleaveEventStatement,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] ) = {
     runMap.getOrCreate(event.runId).addMonitorEvent(event,deadlockFilter)
  }

  
  
  
  def visit(event: LoopStartEvent) = None;

  def visit(event: LoopEndEvent)= {
    
    
    statusFromLoopEnd = event.status;
    None;
  }
  
  def visit(event: RunEndEvent) = {
     runMap.getOrCreate(event.runId).endEventReceived()
     Some(LoopIdRunId(event.loopId , event.runId));
  
  }
  def visit(event: RunStartEvent) = None;

}