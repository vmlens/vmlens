package com.anarsoft.race.detection.sortnonvolatilememoryaccess

private class SortedMemoryAccessListElement[EVENT](var isDataRace: Boolean,
                                                   val event: NonVolatileMemoryAccessEvent[EVENT]) {

  def positionInRun = event.positionInRun;

}
