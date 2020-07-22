package com.anarsoft.race.detection.process.read




trait ReadCallback[EVENT] {
  
  def onEvent( event : EVENT);
  
  def readSlidingWindowId( id : Int  );
  
 
  
  
}