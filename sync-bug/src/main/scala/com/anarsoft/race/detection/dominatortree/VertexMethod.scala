package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

case class VertexMethod(methodId : Int, parent : InternalNode) extends InternalNode {

  override def getLabel(descriptionContext: DescriptionContext): String = descriptionContext.methodName(methodId)

  override def addToNeedsDescription(needsDescriptionCallback: NeedsDescriptionCallback): Unit = {
    needsDescriptionCallback.needsMethod(methodId)
  }
}
