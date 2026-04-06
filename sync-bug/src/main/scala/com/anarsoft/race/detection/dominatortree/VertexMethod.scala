package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import com.anarsoft.race.detection.dominatortree.key.VertexKeyMethod

class VertexMethod(val key : VertexKeyMethod) extends DefaultVertex {
  
  override def getLabel(descriptionContext: DescriptionContext): String = descriptionContext.methodName(key.methodId)

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    needsDescriptionCallback.needsMethod(key.methodId)
  }

  override def isMethodCall  : Boolean = true

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement =
    reportCallback.withOutReverseCallTree(this, parent, level)
  
  
}

