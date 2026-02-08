package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.preanalyzed.serialize.SerializePreAnalyzed

import java.io.{DataOutputStream, FileOutputStream, PrintWriter}
import java.nio.file.Path
import scala.collection.mutable
import java.nio.file.Files
import scala.collection.mutable.ArrayBuffer

class IdToAutomaticTest(val idToAutomaticTest : mutable.HashMap[Int,AutomaticTest]) {

  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    for(elem <- idToAutomaticTest) {
      elem._2.addToNeedsDescription(callback);
    }
  }

  def buildPreAnalyzedClasses(idToClassName : Map[Int,String],
                              context: DescriptionContext,
                              reportDir: Path): Unit = {
    val fileOutputStream = Files.newOutputStream(reportDir.resolve("additional.vmlens"));
    val writer = new PrintWriter(Files.newBufferedWriter(reportDir.resolve("preAnalyzed.txt")))
    val result = new ArrayBuffer[ClassModel]();
    try {
      for (elem <- idToAutomaticTest) {
        val className = idToClassName.get(elem._1).get
        elem._2.buildPreAnalyzedClasses(className, context: DescriptionContext, writer, result);
      }


      val dataOutputStream = new DataOutputStream(fileOutputStream);
      new SerializePreAnalyzed().serialize(result.toList, dataOutputStream);
      dataOutputStream.close();

    } finally {
      writer.close
    }
  }


}
