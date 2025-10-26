package com.anarsoft.race.detection.process.load

trait EventFilter[EVENT] {

  def take( event : EVENT ) : Boolean;

}
