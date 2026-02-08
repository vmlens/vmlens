package com.anarsoft.race.detection.createautomatictest

import com.anarsoft.race.detection.automatictest.AutomaticTestMethodBuilder

trait CreateAutomaticTestMethodEnterEvent extends CreateAutomaticTestEvent {

  def methodId: Int;
  
  override def addToAutomaticTestBuilder(builder: AutomaticTestMethodBuilder): Unit = {
    builder.methodEnter(methodId)
  }
}
