package com.anarsoft.race.detection.process.interleave.eventList
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import com.anarsoft.race.detection.process.interleave.LoopOrRunEvent
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.LoopWarningEvent
/*
 *  LoopOpen 
 * 
 * LoopWarningEvent
 */



class LoopStateHolder(var current  : LoopState) {
  
  def add(event : LoopWarningEvent)
  {
    current.add(event);
  }
  
  
  
  def add(event : LoopOrRunEvent)
  {
     current.add(event);
  }
  
  
  def add(event : InterleaveEventStatement)
  { 
     current.add(event);
  }
  
  
  
  def processAtSlingWingowIdEnd()
  {
     current =  current.processAtSlingWingowIdEnd();
  }
  
  
  
  def stopProcessing() =
  {
    current.stopProcessing();
  }
  
  
  def addRace(reading :  InterleaveEventNonVolatileAccess , writing :  InterleaveEventNonVolatileAccess,hasRead : Boolean)
  {
    current.addRace(reading, writing,hasRead);
  }
  
  
}


object LoopStateHolder
{
  
  def apply(id : Int) =
  {
   new  LoopStateHolder( new LoopOpen(id) );
  }
  
  
}
