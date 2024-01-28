package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.processEventByType.ProcessEventByType
import com.anarsoft.race.detection.util.EventArray

class SortNonVolatileMemoryAccess[EVENT <: NonVolatileMemoryAccessEvent[EVENT]] {

  def sort(array: EventArray[EVENT], partialOrder: PartialOrder,
           sortedListBuilder: MemoryAccessReportBuilder): Unit = {
    array.sort(new NonVolatileMemoryAccessEventOrdering[EVENT]);
    val process = new ProcessEventByType[EVENT](new AlgorithmForEventSortNonVolatile[EVENT]
    (partialOrder, sortedListBuilder));
  }

}
