package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.vmlens.report.dominatortree.UIDominatorTreeElement

case class Frame(node: DominatorTreeVertex, parent: Option[UIDominatorTreeElement], level: Int) {

}
