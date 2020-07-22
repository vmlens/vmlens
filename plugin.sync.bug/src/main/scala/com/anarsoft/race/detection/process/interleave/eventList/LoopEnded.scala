package com.anarsoft.race.detection.process.interleave.eventList

import java.util.ArrayList
import com.anarsoft.race.detection.process.interleave._
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess

class LoopEnded( val statementList : ArrayList[InterleaveEventStatement],val  hasWarning : Boolean,val loopEndEvent : LoopEndEvent, val lastStartedRunId : Int) extends LoopState {
  
  def add(event : LoopWarningEvent)
  {
    
  }
  
  def add(event : LoopOrRunEvent)
  {
    
  }
  
  def add(event : InterleaveEventStatement)
  {
  
  }
  
  def processAtSlingWingowIdEnd() = this;
 
  def stopProcessing() =
  {
    //  LoopEndedNormally( val list : ArrayList[InterleaveEventStatement] ,val count : Int,val status : Int,val hasWarning : Boolean) 
    LoopEndedNormally(statementList,lastStartedRunId , loopEndEvent.status  , hasWarning);
  }
  
  def addRace(reading :  InterleaveEventNonVolatileAccess , writing :  InterleaveEventNonVolatileAccess, raceHasRead : Boolean)
  {
    
  }
  
  
  
}