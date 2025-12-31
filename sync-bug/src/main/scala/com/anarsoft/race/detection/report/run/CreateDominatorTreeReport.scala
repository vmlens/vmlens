package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.vmlens.report.createreport.CreateHtmlReport
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import java.io.StringWriter
import java.nio.file.{Files, Path}
import java.util

/**
 * we iterate over the the graph using a depth first algorithm tracking the level
 * We create a list containing inner nodes and leafs if any
 * 
 * 
 * 
 * 
 */
class CreateDominatorTreeReport {

  def createReport(runData: ReportLoopData,
                   descriptionContext: DescriptionContext,
                   createHtmlReport : CreateHtmlReport): Unit = {

    runData.dominatorTree match {
      case None => {
      }

      case Some(graph) => {
        val list = new DominatorTreeTraversal().traverse(graph.graph,graph.root,descriptionContext);
        createHtmlReport.createDominatorTreeReport(list, descriptionContext.loopName(runData.loopId) , runData.dominatorTreeLink)
      }

    }
  }

}
