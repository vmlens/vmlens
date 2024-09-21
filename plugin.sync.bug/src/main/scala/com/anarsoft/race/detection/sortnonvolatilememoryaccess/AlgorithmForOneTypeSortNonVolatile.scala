package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.AlgorithmForOneType
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.sortutil.ThreadIdToLastSortableEvent

private class AlgorithmForOneTypeSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(private val partialOrder: PartialOrder, val reportBuilder: RunReportForNonVolatileMemoryAccessBuilder)
  extends AlgorithmForOneType[EVENT] {

  // visible for test 
  val sortedMemoryAccessList = new SortedMemoryAccessList[EVENT]();
  
  val threadIdToLastSortableEvent = new ThreadIdToLastSortableEvent[EVENT](
    (event) => EventContainerForNonVolatileMemoryAccess[EVENT](event));

  override def prozess(event: EVENT): Unit = {
    var sortable = true;
    threadIdToLastSortableEvent.foreachOpposite(event, previous => {
      if (!partialOrder.isLeftBeforeRight(previous, event)) {
        sortable = false;
        sortedMemoryAccessList.setDataRace(previous)
      }
    });
    threadIdToLastSortableEvent.put(event);
    if (sortable) {
      sortedMemoryAccessList.add(event)
    } else {
      sortedMemoryAccessList.addDataRace(event)
    }
  }

  override def stop(): Unit = {
    sortedMemoryAccessList.buildResult(reportBuilder);
  }
}
