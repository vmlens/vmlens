package com.anarsoft.race.detection.warning

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.TestResult
import com.vmlens.report.overview.UIWarning


class NotYetImplemented(val methodId : Int) extends Warning {

  override def forRun(): Boolean = false;

  override def addToTestResult(descriptionContext : DescriptionContext, testResult: TestResult): Unit = {
    testResult.addLargeWarning(new UIWarning(descriptionContext.methodName(methodId ) + " is not yet testable "));
  }

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(methodId)
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
