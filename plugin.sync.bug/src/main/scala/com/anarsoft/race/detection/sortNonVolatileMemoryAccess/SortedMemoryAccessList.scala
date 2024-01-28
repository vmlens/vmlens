package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

private class SortedMemoryAccessList[EVENT] {

  def add(memoryAccess: NonVolatileMemoryAccessEvent[EVENT]): Unit = {

  }

  def addDataRace(alreadyAdded: NonVolatileMemoryAccessEvent[EVENT],
                  newMemoryAccess: NonVolatileMemoryAccessEvent[EVENT]): Unit = {

  }

  def buildResult(accessReportBuilder: MemoryAccessReportBuilder): Unit = {

  }

}
