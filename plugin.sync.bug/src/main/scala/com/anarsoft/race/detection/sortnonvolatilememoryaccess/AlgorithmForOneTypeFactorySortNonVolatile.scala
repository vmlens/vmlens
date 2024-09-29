package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.{AlgorithmForOneType, AlgorithmForOneTypeFactory}
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder

import scala.collection.mutable.ArrayBuffer


private class AlgorithmForOneTypeFactorySortNonVolatile[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(val partialOrder: PartialOrder, val result: ArrayBuffer[SortedMemoryAccessList])
  extends AlgorithmForOneTypeFactory[EVENT] {

  override def create(): AlgorithmForOneType[EVENT] = {
    new AlgorithmForOneTypeSortNonVolatile[EVENT](partialOrder, result);
  }

}
