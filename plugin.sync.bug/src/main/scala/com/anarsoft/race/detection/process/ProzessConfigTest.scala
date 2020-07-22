package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.syncAction._
import java.util.ArrayList


class ProzessConfigTest extends ProzessConfig {
  
  /*
   * 
   * ContextProcessSyncAction
   * auswerten von   context.lockEventList.add(in);
   * 
   */
  def checkLocksStep() = Some(new StepCheckLocks());
  def throwExceptionFromAgent()  = true;
  
  
  
}