package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.processEventByType.ProcessEventByType
import com.anarsoft.race.detection.sortUtil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class CreatePartialOrder[EVENT <: SyncActionEvent[EVENT]](val createContainer: (EVENT) => EventContainer[EVENT]) {

  def process(syncActionEvents: EventArray[EVENT], partialOrderBuilder: PartialOrderBuilder): Unit = {
    syncActionEvents.sort(new SyncActionEventOrdering[EVENT]);
    val algorithm = new AlgorithmForEventCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);
    val process = new ProcessEventByType[EVENT](algorithm);
    process.process(syncActionEvents);
  }

}
