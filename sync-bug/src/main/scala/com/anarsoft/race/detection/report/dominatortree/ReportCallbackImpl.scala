package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.vmlens.report.dominatortree.UIDominatorTreeElement

class ReportCallbackImpl(val context : DominatorTreeTraversalContext) extends ReportCallback {

  override def withReverseCallTree(node: DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int): 
  UIDominatorTreeElement = {
    val element = new UIDominatorTreeElement(node.getLabel(context.descriptionContext), context.levelToCSS.getCss(level))
    context.result.add(element)
    element;
  }

  override def withOutReverseCallTree(node: DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int): 
  UIDominatorTreeElement = {
      val element = new UIDominatorTreeElement(node.getLabel(context.descriptionContext), context.levelToCSS.getCss(level))
      context.result.add(element)
      element;
  }
}
