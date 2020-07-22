package com.anarsoft.race.detection.process.interleave

trait LoopOrRunEventVisitor[RESULT] {
  
  def visit(event : LoopStartEvent) : RESULT;
  def visit(event : LoopEndEvent) : RESULT;
  def visit(event : RunEndEvent) : RESULT;
  def visit(event : RunStartEvent) : RESULT;
  
}