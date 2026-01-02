package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTree
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.vmlens.report.createreport.CreateHtmlReport
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import java.util

class DominatorTreeTraversalContext(val dominatorTree : DominatorTree,
                                    val descriptionContext: DescriptionContext,
                                    val createHtmlReport : CreateHtmlReport,
                                    val result : util.LinkedList[UIDominatorTreeElement],
                                    val levelToCSS : LevelToCSS,
                                    val dominatorTreePrefix : String,
                                    val runName : String,
                                    val runFileName : String) {

  var index : Int = 0;
  
  def nextFileName() : String = {
    index = index + 1;
    dominatorTreePrefix + "_call_" + index + ".html";
  }
  
}
