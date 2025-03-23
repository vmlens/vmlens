package com.anarsoft.race.detection.createpartialorderthreadoperation

import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.util.EventArray

class AddToPartialOrderBuilder {

  def process(syncActionEvents: EventArray[ThreadOperation], partialOrderBuilder: BuildPartialOrderContext): Unit = {
    for (threadOperation <- syncActionEvents) {
      threadOperation.addToPartialOrderBuilder(partialOrderBuilder);
    }
  }
}
