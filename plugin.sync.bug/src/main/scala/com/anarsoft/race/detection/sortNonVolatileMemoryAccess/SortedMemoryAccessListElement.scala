package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

private class SortedMemoryAccessListElement[EVENT](var isDataRace: Boolean,
                                                   val event: NonVolatileMemoryAccessEvent[EVENT]) {

  def positionInRun = event.positionInRun;

}
