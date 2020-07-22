package com.anarsoft.race.detection.process.interleave.eventList


//import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import  com.anarsoft.race.detection.process.nonVolatileField.InterleaveEventNonVolatileAccess
import com.anarsoft.race.detection.process.interleave.LoopOrRunEvent
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.LoopResult
import com.anarsoft.race.detection.process.interleave.LoopWarningEvent
/**
 * 
 * loop start
 * run start
 * 
 * run end
 * loop end 
 */



trait LoopState {
  def add(event : LoopWarningEvent);
  def add(event : LoopOrRunEvent);
  def add(event : InterleaveEventStatement);
  def processAtSlingWingowIdEnd() : LoopState;
  def stopProcessing(): LoopResult;
  def addRace(reading :  InterleaveEventNonVolatileAccess , writing :  InterleaveEventNonVolatileAccess,hasRead : Boolean);
  
  
}