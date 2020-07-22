package com.vmlens.api

trait CommandVisitor[R] {
  
  	def visit(undoableCommand    : UndoableCommand)   : R;
	  def visit(nonUndoableCommand : NonUndoableCommand): R;
  
  
}