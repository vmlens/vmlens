package com.anarsoft.race.detection.createautomatictest

import com.anarsoft.race.detection.automatictest.AutomaticTestMethodBuilder

trait CreateAutomaticTestEvent {
  
  def addToAutomaticTestBuilder(builder : AutomaticTestMethodBuilder) : Unit;

}
