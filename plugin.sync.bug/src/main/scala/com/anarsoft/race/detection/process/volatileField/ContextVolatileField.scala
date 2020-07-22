package com.anarsoft.race.detection.process.volatileField

import java.util.ArrayList;

trait ContextVolatileField {
  
  def volatileAccessEventList      : ArrayList[VolatileAccessEvent]; 
  def volatileAccessEventStatic    : ArrayList[VolatileAccessEventStatic]; 
  def volatileAccessArrayEventList : ArrayList[VolatileArrayAccessEvent];
  
}