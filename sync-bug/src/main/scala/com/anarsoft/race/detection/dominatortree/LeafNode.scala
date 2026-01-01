package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.run.{CreateReverseCallTreeReport, DominatorTreeTraversalContext}
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import com.vmlens.report.dominatortree.UIStateElement
import com.vmlens.report.dominatortree.UIStateElementSortKey

import java.util

trait LeafNode extends DominatorTreeVertex {

  override def addToReport(parent: Option[UIDominatorTreeElement],
                           level: Int,
                           context : DominatorTreeTraversalContext): UIDominatorTreeElement = {
    val current = parent.get;
    val uiStateElement = new UIStateElement(getLabel(context.descriptionContext),getUIStateElementSortKey)
    val link = context.nextFileName();
    uiStateElement.setLink(link)
    current.add(uiStateElement)
    
    new CreateReverseCallTreeReport().createReport(this,context,link)
    
    current;
  }

  def getLabel(descriptionContext: DescriptionContext): String
  def getUIStateElementSortKey : UIStateElementSortKey;
  
}
