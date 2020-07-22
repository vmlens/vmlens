package com.vmlens.api

trait Command {
  
  def execute( commandContext : CommandContext);
	def id() : CommandId;
	
	
	
	def accept[R](visitor :  CommandVisitor[R] ) : R;
  
  
}