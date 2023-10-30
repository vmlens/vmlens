package com.anarsoft.race.detection.event.load

class SourceImpl[EVENT, CONTEXT <: LoadedEventContext[EVENT]]
(val context: CONTEXT) {

  def distributeLoadedEvent(event: EVENT): Unit = {
    context.addLoadedEvent(event);
  }

}
