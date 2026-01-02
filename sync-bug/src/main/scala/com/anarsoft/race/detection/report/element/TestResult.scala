package com.anarsoft.race.detection.report.element

import com.vmlens.report.overview.UIWarning

import java.util
import scala.collection.mutable

class TestResult(var text: String) {

  val largeWarningList: java.util.List[UIWarning] = new util.LinkedList[UIWarning]
  
  def addSmallWarningText(warning: String): Unit = {
    text += ", " + warning
  }

  def addLargeWarning(largeWarning: UIWarning): Unit = {
    largeWarningList.add(largeWarning)
  }
  
}
