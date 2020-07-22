package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.gen._;


trait SyncAction {
  
   def accept(visitor : SyncActionsVisitor);
  
}