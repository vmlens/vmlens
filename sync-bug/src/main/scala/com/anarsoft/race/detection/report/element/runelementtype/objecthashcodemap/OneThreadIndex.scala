package com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap

class OneThreadIndex(private val threadIndex: Int) extends ThreadIndices {
  override def addThreadIndex(newThreadIndex: Int, objectHashCodeMap: ObjectHashCodeMap): ThreadIndices = {
    if (threadIndex == newThreadIndex) return this
    new MultipleThreadIndices(objectHashCodeMap.newCode)
  }

  override def asUiString = ""

  def isSingleThreaded: Boolean = true;
}
