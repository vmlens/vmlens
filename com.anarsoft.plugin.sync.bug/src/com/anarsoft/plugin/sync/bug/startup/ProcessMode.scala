package com.anarsoft.plugin.sync.bug.startup

abstract sealed class ProcessMode {
  
}

case class ProcessExternal() extends ProcessMode;

case class ProcessFromLaunch(val processDataAndResult : ProcessDataAndResult)  extends ProcessMode;
