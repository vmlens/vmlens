package com.anarsoft.race.detection.process.read.event

trait ExpectedEvent {
  
   def isExpected(event : Object, context : ContextReadEvent) : Boolean;
  
  
}