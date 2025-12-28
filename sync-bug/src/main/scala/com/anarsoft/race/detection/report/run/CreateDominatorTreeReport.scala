package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.ReportLoopData
import com.anarsoft.race.detection.report.description.DescriptionContext
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.nio.{Attribute, DefaultAttribute}
import org.jgrapht.nio.dot.DOTExporter

import java.io.StringWriter
import java.nio.file.{Files, Path}
import java.util

class CreateDominatorTreeReport {

  def createReport(runData: ReportLoopData,
                   descriptionContext: DescriptionContext,
                   reportDir: Path): Unit = {

    runData.dominatorTree match {
      case None => {
      }

      case Some(graph) => {
        val file = reportDir.resolve( runData.loopId +  "_graph.dot")
        val writer = Files.newBufferedWriter(file)
        val exporter = new DOTExporter[DominatorTreeVertex,DefaultEdge]()
        exporter.setVertexAttributeProvider((v) => {
          val map: util.HashMap[String, Attribute] = new util.LinkedHashMap
          map.put("label", DefaultAttribute.createAttribute(v.getLabel(descriptionContext)))
          map

        })
        exporter.exportGraph(graph.graph, writer)
        writer.close()
      }

    }
  }

}
