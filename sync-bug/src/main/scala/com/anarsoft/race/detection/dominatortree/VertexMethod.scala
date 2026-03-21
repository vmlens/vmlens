package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement

case class VertexMethod(methodId : Int, stackSize : Int) extends InternalNode {

  override def getLabel(descriptionContext: DescriptionContext): String = descriptionContext.methodName(methodId)

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    needsDescriptionCallback.needsMethod(methodId)
  }

  override def isMethodCall  : Boolean = true

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement =
    reportCallback.withOutReverseCallTree(this, parent, level)
}

