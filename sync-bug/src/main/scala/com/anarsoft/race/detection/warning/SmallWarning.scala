package com.anarsoft.race.detection.warning

import com.vmlens.report.element.TestResult

class SmallWarning(val text : String) extends Warning {

  override def forRun(): Boolean = false;

  override def addToTestResult(testResult: TestResult): Unit = {
    testResult.addSmallWarningText(text)
  }
  
  override def equals(other: Any): Boolean = other match {
    case that: SmallWarning =>
      that.canEqual(this) &&
        text == that.text
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(text)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[SmallWarning]
  
}
