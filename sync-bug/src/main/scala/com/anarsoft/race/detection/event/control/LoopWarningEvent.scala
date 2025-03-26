package com.anarsoft.race.detection.event.control

trait LoopWarningEvent extends LoadedControlEvent {

  def  messageId  : Int;

  override def process(context: ProcessContext): Unit = {
    context.warningList.add(messageId);
  }
  
}
