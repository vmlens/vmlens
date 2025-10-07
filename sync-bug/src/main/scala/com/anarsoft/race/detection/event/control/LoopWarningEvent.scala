package com.anarsoft.race.detection.event.control

import com.anarsoft.race.detection.warning.Warning

trait LoopWarningEvent extends LoadedControlEvent {

  def  messageId  : Int;
  def  messageParam  : Int;

  override def process(context: ProcessContext): Unit = {
    context.warningList.add(Warning(this));
  }
  
}
