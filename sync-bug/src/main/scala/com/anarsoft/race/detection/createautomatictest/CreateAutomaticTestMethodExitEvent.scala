package com.anarsoft.race.detection.createautomatictest

import com.anarsoft.race.detection.automatictest.AutomaticTestMethodBuilder

trait CreateAutomaticTestMethodExitEvent extends CreateAutomaticTestEvent {

  override def addToAutomaticTestBuilder(builder: AutomaticTestMethodBuilder): Unit = {
    builder.methodExit()
  }
  
}
