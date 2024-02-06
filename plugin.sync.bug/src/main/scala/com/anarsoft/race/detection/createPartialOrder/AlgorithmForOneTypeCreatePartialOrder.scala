package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.processEventByType.AlgorithmForOneType
import com.anarsoft.race.detection.sortUtil.{EventContainer, ThreadIdToLastSortableEvent}

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
