package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.dominatortree.ReportCallback
import com.vmlens.report.dominatortree.UIDominatorTreeElement

class VertexRoot extends DefaultVertex {
  

  override def getLabel(descriptionContext: DescriptionContext): String = "root"

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
  }


  private def canEqual(other: Any): Boolean = other.isInstanceOf[VertexRoot]

  override def equals(other: Any): Boolean =
    this.canEqual(other)


  override def hashCode(): Int = {
   74
  }

  override def isMethodCall  : Boolean = false

  override def addToReport(parent: Option[UIDominatorTreeElement], level: Int, reportCallback: ReportCallback): UIDominatorTreeElement =
    reportCallback.withOutReverseCallTree(this, parent, level)
  
}
