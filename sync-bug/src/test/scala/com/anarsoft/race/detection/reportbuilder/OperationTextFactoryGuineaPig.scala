package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.report.operationtextfactory.OperationTextFactory

class OperationTextFactoryGuineaPig extends OperationTextFactory {
  override def create(context: DescriptionContext): String = "test"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {

  }
}
