package com.anarsoft.race.detection.process.read.event

trait ReadEventConfig {
  
  
  /*
   * val READ_METHOD_EVENTS = false;
  val READ_MONITOR_EVENTS = false;
  val READ_FIELD_EVENTS = false;
  val READ_SYNC_ACTIONS = true;
   val READ_SCHEDULER_EVENTS = false;
   */
  
  def methods : Boolean;
  def monitor : Boolean;
  def field   : Boolean;
  def syncActions : Boolean;
  def scheduler   : Boolean;
  
}