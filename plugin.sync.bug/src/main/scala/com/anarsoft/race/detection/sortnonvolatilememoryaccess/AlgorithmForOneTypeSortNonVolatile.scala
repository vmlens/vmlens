package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.AlgorithmForOneType
import com.anarsoft.race.detection.sortutil.ThreadIdToLastSortableEvent

private class AlgorithmForOneTypeSortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(private val partialOrder: PartialOrder,
 private val sortedMemoryAccessList: SortedMemoryAccessList[EVENT]) extends AlgorithmForOneType[EVENT] {

  val threadIdToLastSortableEvent = new ThreadIdToLastSortableEvent[EVENT](
    (event) => NonVolatileMemoryAccessEventContainer[EVENT](event));

  override def prozess(event: EVENT): Boolean = {
    var sortable = true;
    threadIdToLastSortableEvent.foreachOppositeAndPut(event, previous => {
      if (partialOrder.leftBeforeRight(previous, event)) {
        sortedMemoryAccessList.add(event)
      } else {
        sortedMemoryAccessList.addDataRace(previous, event)
        sortable = false;
      }
    });
    sortable;
  }
}
