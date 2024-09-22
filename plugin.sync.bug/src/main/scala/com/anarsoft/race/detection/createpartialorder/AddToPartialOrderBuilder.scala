package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.processeventbytype.ProcessEventByType
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class AddToPartialOrderBuilder[EVENT <: SyncActionEvent[EVENT]](val createContainer: (EVENT) => EventContainer[EVENT]) {

  def process(syncActionEvents: EventArray[EVENT], partialOrderBuilder: PartialOrderBuilder): Unit = {
    syncActionEvents.sort(new SyncActionEventOrdering[EVENT]);
    val algorithm = new AlgorithmForOneTypeFactoryCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);
    val process = new ProcessEventByType[EVENT](algorithm);
    process.process(syncActionEvents);
  }

}
