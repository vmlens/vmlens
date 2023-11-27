package com.anarsoft.race.detection.sortMemoryAccess

trait MemoryAccessElement {

  def addToList(event: MemoryAccessEvent, currentNode: DoubleListNode, partialOrder: PartialOrderReadOnly): AddResult

}
