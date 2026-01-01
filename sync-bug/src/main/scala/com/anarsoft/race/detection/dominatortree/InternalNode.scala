package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.run.DominatorTreeTraversalContext
import com.vmlens.report.dominatortree.UIDominatorTreeElement

import java.util

trait InternalNode extends DominatorTreeVertex {

  override def addToReport(parent: Option[UIDominatorTreeElement], 
                           level: Int,
                           context : DominatorTreeTraversalContext): UIDominatorTreeElement = {
    val element = new UIDominatorTreeElement( getLabel(context.descriptionContext) , context.levelToCSS.getCss(level) )
    context.result.add(element)
    element;
  }

  def getLabel(descriptionContext: DescriptionContext): String


}
