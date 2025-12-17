package com.anarsoft.race.detection.report.element.runelementtype

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap


trait ReportOperation {
  def operation: String

  def element(context: DescriptionContext): String

  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit

  def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit

  def objectString(context: DescriptionContext): String
}

