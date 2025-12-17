package com.anarsoft.race.detection.report.description

trait NeedsDescriptionCallback {
  def needsField(fieldId: Int): Unit
  def needsMethod(methodId: Int): Unit
}
