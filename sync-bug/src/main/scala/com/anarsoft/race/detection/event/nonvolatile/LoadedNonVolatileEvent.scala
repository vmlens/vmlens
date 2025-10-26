package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId


trait LoadedNonVolatileEvent extends EventWithLoopAndRunId {

  def addToContext(context: LoadedNonVolatileEventContext): Unit;
  
  def take(filteredFieldIds : Set[Int]) : Boolean;
  
}
