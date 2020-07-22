package com.anarsoft.race.detection.process.nonVolatileField

import java.util.ArrayList;
import com.anarsoft.race.detection.process.interleave.InterleaveEventList

trait ContextAddNonVolatileFields2InterleaveList {
  
    def arrayAccessEventList : ArrayList[ArrayAccessEvent] ;
  def nonVolatileFieldAccessEventList :  ArrayList[NonVolatileFieldAccessEvent];
  def nonVolatileFieldAccessEventStatic : ArrayList[NonVolatileFieldAccessEventStatic] ;
  
    def  interleaveEventList : InterleaveEventList;
}