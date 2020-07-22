package com.vmlens.api

trait UndoableCommand extends Command {
  
  def undo( commandContext : CommandContext);
 
}