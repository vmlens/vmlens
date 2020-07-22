package com.anarsoft.race.detection.process.syncAction

import scala.collection.mutable.HashMap;

trait ContextLastProgramCounter {
  
  var threadId2LastProgramCounter : HashMap[Long,Int] = null;
  
  
  def initializeContextLastProgramCounter()
  {
    threadId2LastProgramCounter = new HashMap[Long,Int]();
  }
  
  
  
}