package com.anarsoft.race.detection.warning

import com.vmlens.report.element.TestResult

class MaximumAlternatingOrdersReached(val alternatingOrderCount : Int) extends Warning{

  override def forRun(): Boolean = false;

  override def addToTestResult(testResult: TestResult): Unit = {
    testResult.addSmallWarningText("Maximum Alternating Orders Reached:" + alternatingOrderCount)
  }


  
  override def equals(other: Any): Boolean = other match {
    case that: MaximumAlternatingOrdersReached =>
      that.canEqual(this)
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(alternatingOrderCount)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 55 )
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[MaximumAlternatingOrdersReached]


}
