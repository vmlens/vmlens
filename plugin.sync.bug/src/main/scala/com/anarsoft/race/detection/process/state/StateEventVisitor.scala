package com.anarsoft.race.detection.process.state

trait StateEventVisitor {
  
  def visit( event : StateEventField);
  def visit( event : StateEventStaticField);
  def visit( event : StateEventArray);
}