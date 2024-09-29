package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.ProcessEventByType
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable.ArrayBuffer

class SortNonVolatileMemoryAccess[EVENT <: NonVolatileMemoryAccessEvent[EVENT]] {

  def sort(array: EventArray[EVENT], partialOrder: PartialOrder): List[SortedMemoryAccessList] = {
    array.sort(new NonVolatileMemoryAccessEventOrdering[EVENT]);
    val result = new ArrayBuffer[SortedMemoryAccessList]();
    val algorithm = new AlgorithmForOneTypeFactorySortNonVolatile[EVENT](partialOrder, result);
    val process = new ProcessEventByType[EVENT](algorithm);
    process.process(array);
    result.toList;
  }

}
