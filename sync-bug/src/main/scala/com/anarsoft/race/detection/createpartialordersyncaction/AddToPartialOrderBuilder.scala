package com.anarsoft.race.detection.createpartialordersyncaction

import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.processeventbytype.ProcessEventByType
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class AddToPartialOrderBuilder[EVENT <: SyncActionEventWithCompareType[EVENT]](val createContainer: (EVENT) => EventContainer[EVENT]) {

  def process(syncActionEvents: EventArray[EVENT], partialOrderBuilder: BuildPartialOrderContext): Unit = {
    syncActionEvents.sort(new SyncActionEventOrdering[EVENT]);
    val algorithm = new AlgorithmForOneTypeFactoryCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);
    val process = new ProcessEventByType[EVENT](algorithm);
    process.process(syncActionEvents);
  }

}
