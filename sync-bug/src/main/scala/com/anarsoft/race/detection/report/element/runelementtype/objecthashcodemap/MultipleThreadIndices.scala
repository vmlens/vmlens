package com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap

class MultipleThreadIndices(val code: Int) extends ThreadIndices {
  override def addThreadIndex(newThreadIndex: Int, objectHashCodeMap: ObjectHashCodeMap): ThreadIndices = this

  override def asUiString: String = "" + code

  def isSingleThreaded: Boolean = false;

  def id : Option[Int] = Some(code);
}
