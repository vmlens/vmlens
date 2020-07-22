package com.anarsoft.race.detection.process.interleave


import java.util.ArrayList
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.anarsoft.race.detection.process.SortArrayList
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.process.interleave.eventList.LoopStateHolder
import com.vmlens.api._
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
/*
 * StepProcessInterleaveEventListDuringRead
 * StepProcessInterleaveEventListAfterRead
 * 
 */

class InterleaveEventList  {
  
  val loopId2LoopStateHolder = new HashMap[Int,LoopStateHolder]
  
  
  def add(event : LoopWarningEvent)
  {
    
    
    loopId2LoopStateHolder.getOrElseUpdate(event.loopId, LoopStateHolder(event.loopId)).add(event);
  }
  
  
  
  def add(event : LoopOrRunEvent)
  { 
    loopId2LoopStateHolder.getOrElseUpdate(event.loopId, LoopStateHolder(event.loopId)).add(event);
  }
  
  def add(event : InterleaveEventStatement)
  {
   
    
     loopId2LoopStateHolder.getOrElseUpdate(event.loopId, LoopStateHolder(event.loopId)).add(event);
  }
  

  
  
  def processListDuringRead()
  {

    for( elem <- loopId2LoopStateHolder )
    {
      elem._2.processAtSlingWingowIdEnd();
    }
         
       
  }
  
  
  
  def stopProcessing() =
  {
     val id2Result = new   HashMap[Int,LoopResult]
     
      for( elem <- loopId2LoopStateHolder )
      {
        id2Result.put( elem._1 , elem._2.stopProcessing() );
      }
     
     id2Result;
       
  }
  
  
 /*
  *   val readEvent  = race.read;
    var writeEvent = race.write;
    
    if( readEvent.isInstanceOf[InterleaveEventStatement] && writeEvent.isInstanceOf[InterleaveEventStatement])
    {
      add(readEvent.asInstanceOf[InterleaveEventStatement]  );
      add(writeEvent.asInstanceOf[InterleaveEventStatement]  );
  */
 
  
  
  
  def addRace(race : RaceConditionFoundException)
  {

    val readEvent  = race.read;
    var writeEvent = race.write;
    
    if( readEvent.isInstanceOf[InterleaveEventNonVolatileAccess] && writeEvent.isInstanceOf[InterleaveEventNonVolatileAccess])
    {
      
      val hasRead =   MemoryAccessType.containsRead(readEvent.operation | writeEvent.operation);
      
      
      val r = readEvent.asInstanceOf[InterleaveEventNonVolatileAccess];
      val w = writeEvent.asInstanceOf[InterleaveEventNonVolatileAccess]
      
       loopId2LoopStateHolder.getOrElseUpdate(r.loopId, LoopStateHolder(r.loopId)).addRace(r,w,hasRead);
      
      
   
    }
  }

  
  
}