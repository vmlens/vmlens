package com.anarsoft.race.detection.warning

import com.vmlens.report.element.{LargeWarning, TestResult}

class NotYetImplemented(val methodId : Int) extends Warning {

  override def forRun(): Boolean = false;

  override def addToTestResult(testResult: TestResult): Unit = {
    testResult.addLargeWarning(new LargeWarning(methodId))
  }
 
  override def equals(other: Any): Boolean = other match {
    case that: NotYetImplemented =>
      that.canEqual(this) &&
        methodId == that.methodId
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(methodId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[NotYetImplemented]


}
