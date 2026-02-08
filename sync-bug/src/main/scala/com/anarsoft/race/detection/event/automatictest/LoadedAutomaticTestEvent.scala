package com.anarsoft.race.detection.event.automatictest

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId

trait LoadedAutomaticTestEvent extends EventWithLoopAndRunId{

  def automaticTestId  : Int;
  
}
