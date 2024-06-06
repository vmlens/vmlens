package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.processeventbytype.AlgorithmForOneType
import com.anarsoft.race.detection.sortutil.{EventContainer, ThreadIdToLastSortableEvent}

class AlgorithmForOneTypeCreatePartialOrder[EVENT <: SyncActionEvent[EVENT]]
(val partialOrderBuilder: PartialOrderBuilder, createContainer: (EVENT) => EventContainer[EVENT])
  extends AlgorithmForOneType[EVENT] {


  val threadIdToLastSortableEvent = new ThreadIdToLastSortableEvent[EVENT](createContainer);

  override def prozess(event: EVENT): Boolean = {
    threadIdToLastSortableEvent.foreachOppositeAndPut(event, previous => {
      partialOrderBuilder.addLeftBeforeRight(previous, event);
    });
    true;
  }


}
