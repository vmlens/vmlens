package com.anarsoft.race.detection.process.load

class EventFilterNoOp[EVENT] extends EventFilter[EVENT] {

  def take( event : EVENT ) = true;

}
