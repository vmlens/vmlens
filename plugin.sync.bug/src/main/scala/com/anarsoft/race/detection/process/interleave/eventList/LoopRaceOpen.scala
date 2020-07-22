package com.anarsoft.race.detection.process.interleave.eventList

import java.util.ArrayList
import com.anarsoft.race.detection.process.interleave._
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess

class LoopRaceOpen(val statementList : ArrayList[InterleaveEventStatement], val raceHasRead : Boolean, var count : Int,val runWithRace : Int)  extends LoopState with LoopOrRunEventVisitor[Unit] {
  
  def add(event : LoopWarningEvent)
  {
    
  }
  
  def add(event : LoopOrRunEvent)
  {
    
  }
  
  def add(event : InterleaveEventStatement)
  {
    if( event.runId ==  runWithRace)
    {
      statementList.add(event);
    }
  }
  
  def processAtSlingWingowIdEnd() = this;
 
  // case class LoopResultRace( val list : ArrayList[InterleaveEventStatement] , val raceHasRead : Boolean,var count : Int)
  
  def stopProcessing() =
  {
    //  LoopEndedNormally( val list : ArrayList[InterleaveEventStatement] ,val count : Int,val status : Int,val hasWarning : Boolean) 
    LoopResultRace(statementList,raceHasRead , count);
  }
  
  def addRace(reading :  InterleaveEventNonVolatileAccess , writing :  InterleaveEventNonVolatileAccess, raceHasRead : Boolean)
  {
    
  }
   def visit(event : LoopStartEvent) 
  {
    
  }
   
  def visit(event : LoopEndEvent) 
  {
   
  }
  
  
  def visit(event : RunEndEvent) 
  {
    
  }
  
  
  def visit(event : RunStartEvent)
  {
    count= event.runId;
  }
  
  
}