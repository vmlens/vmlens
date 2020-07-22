package com.anarsoft.race.detection.process.perEventList

trait PerEventCallback4Aggregate[ID,EVENT] {
  
  
  def getId( current : EVENT ) : ID;
  
  def onEventWithNewId(  current : EVENT   );
  def onEventWithExistingId( current : EVENT );
  
  def afterLoop();
  
  
}