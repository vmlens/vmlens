package com.anarsoft.race.detection.process.interleave

import java.util.ArrayList
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.anarsoft.race.detection.process.SortArrayList
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import com.vmlens.api._
import com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import com.anarsoft.race.detection.process.interleave.loopAlgo.LoopId2LoopInfo
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import com.typesafe.scalalogging.Logger
/*

				2 Stufen:
						1) während prozessierung: unprocessedInterleaveEventStatements -> potentialInterleaveEventStatements wenn closed
								nach processed

						2) am ende der interleave prozessierung + filtern der races möglich: 		potentialInterleaveEventStatements -> beforeRaceFilter






 *
 */

class InterleaveEventList {

  
       val logger = Logger(classOf[InterleaveEventList])
  
  var unprocessedInterleaveEventStatements = new ArrayList[InterleaveEventStatement]()
  val potentialInterleaveEventStatements = new ArrayList[InterleaveEventStatement]()
  val loopId2LoopInfo = new LoopId2LoopInfo();

  def add(event: LoopWarningEvent) {

    loopId2LoopInfo.add(event);
  }

  def add(event: LoopOrRunEvent) {
    
    loopId2LoopInfo.add(event);

  }

  def add(event: InterleaveEventStatement) {
    
    logger.trace("" + event);
    
    loopId2LoopInfo.add(event);
    unprocessedInterleaveEventStatements.add(event);

  }
  
   def addMonitorEvent(event: InterleaveEventStatement,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] ) {
    loopId2LoopInfo.addMonitorEvent(event,  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor]);
    unprocessedInterleaveEventStatements.add(event);

  }
  
  
  

  def processListDuringRead() {

    unprocessedInterleaveEventStatements = loopId2LoopInfo.processBeforeNewSlidingWindowId(unprocessedInterleaveEventStatements, potentialInterleaveEventStatements);
    
  }

  def stopProcessing() ={
    
    processListDuringRead()
    loopId2LoopInfo.stopProcessing(unprocessedInterleaveEventStatements,potentialInterleaveEventStatements);
  }
    

  def addRace(race: RaceConditionFoundException) {

    val readEvent = race.read;
    var writeEvent = race.write;

    if (readEvent.isInstanceOf[InterleaveEventNonVolatileAccess] && writeEvent.isInstanceOf[InterleaveEventNonVolatileAccess]) {

      val hasRead = MemoryAccessType.containsRead(readEvent.operation | writeEvent.operation);

      val r = readEvent.asInstanceOf[InterleaveEventNonVolatileAccess];
      val w = writeEvent.asInstanceOf[InterleaveEventNonVolatileAccess]

      loopId2LoopInfo.addRace(r);

      unprocessedInterleaveEventStatements.add(r);
      unprocessedInterleaveEventStatements.add(w);
    }
  }

}