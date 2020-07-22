package com.anarsoft.plugin.view.action

import org.eclipse.jface.action.Action;
import com.vmlens.api._;


class NonUndoableAction(val nonUndoableCommand : NonUndoableCommand , val commandContext : CommandContext , name : String) extends Action(name) {

   override def run()
  {
    
   nonUndoableCommand.execute(commandContext);
    
  }
  
  
}