package com.vmlens.api

trait CommandContext {
  
  def toggleParallelField(field : FieldDescription,  activateIn : MethodDescription);
	def toggleParallelMonitor(name : String, activateIn : MethodDescription,  arrayCalledIn : Seq[MethodDescription]  );
	def toggleParallelArray(name : String, activateIn  : MethodDescription , arrayCalledIn  : Seq[MethodDescriptionWithPosition]);
	
	def deleteWaitpoint( elementId : String);
	
	
	def openDeclaration( qualifiedClassName : String,  fieldName : String);
	def openDeclaration( qualifiedClassName : String,  lineNumber : Int);
	
	

	
	
  
  
}