package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.element.{NeedsDescriptionCallback, OperationTextFactory}

class OperationTextFactoryGuineaPig extends OperationTextFactory {
  override def create(): String = "test"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {

  }
}
