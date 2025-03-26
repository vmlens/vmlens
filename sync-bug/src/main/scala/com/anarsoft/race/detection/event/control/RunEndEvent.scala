package com.anarsoft.race.detection.event.control

trait RunEndEvent extends LoadedControlEvent {

  override def process(context: ProcessContext): Unit = {
    context.isFailure = false;
  }
  
}
