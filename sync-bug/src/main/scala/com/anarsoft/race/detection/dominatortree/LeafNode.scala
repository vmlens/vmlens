package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.report.run.LevelToCSS
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import com.vmlens.report.dominatortree.UIStateElement
import com.vmlens.report.dominatortree.UIStateElementSortKey

import java.util

trait LeafNode extends DominatorTreeVertex {

  override def addToReport(parent: Option[UIDominatorTreeElement],
                           level: Int,
                           descriptionContext: DescriptionContext,
                           levelToCSS: LevelToCSS,
                           result: util.LinkedList[UIDominatorTreeElement]): UIDominatorTreeElement = {
    val current = parent.get;
    current.add(new UIStateElement(getLabel(descriptionContext),getUIStateElementSortKey))
    current;
  }

  def getLabel(descriptionContext: DescriptionContext): String
  def getUIStateElementSortKey : UIStateElementSortKey;
  
}
