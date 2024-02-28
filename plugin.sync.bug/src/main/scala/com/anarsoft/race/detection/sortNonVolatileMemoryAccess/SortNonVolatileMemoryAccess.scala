package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.processEventByType.ProcessEventByType
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.dataView.MemoryAccessReportBuilder

class SortNonVolatileMemoryAccess[EVENT <: NonVolatileMemoryAccessEvent[EVENT]] {

  def sort(array: EventArray[EVENT], partialOrder: PartialOrder,
           sortedListBuilder: MemoryAccessReportBuilder): Unit = {
    array.sort(new NonVolatileMemoryAccessEventOrdering[EVENT]);
    val algorithm = new AlgorithmForEventSortNonVolatile[EVENT](partialOrder, sortedListBuilder);
    val process = new ProcessEventByType[EVENT](algorithm);
    process.process(array);
    algorithm.done();
  }

}
