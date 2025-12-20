package com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap

trait ThreadIndices {
  def addThreadIndex(threadIndex: Int, objectHashCodeMap: ObjectHashCodeMap): ThreadIndices

  def asUiString: String
  
  def isSingleThreaded : Boolean
}
