package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap

class ReportOperationNoOP extends ReportOperation {
  
  override def operation: String = ""


  override def element(context: DescriptionContext): String = "" 

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {}

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit =  {}

  override def objectString(context: DescriptionContext): String = ""

  override def take(): Boolean = true

  override def isDataRace: Boolean = false


}
