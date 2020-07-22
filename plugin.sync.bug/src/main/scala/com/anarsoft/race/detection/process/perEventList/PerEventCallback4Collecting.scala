package com.anarsoft.race.detection.process.perEventList

import java.util.ArrayList;

trait PerEventCallback4Collecting[ID,EVENT]  {
 
  
  def addPrevoiusEvents(list : ArrayList[EVENT]) : ArrayList[EVENT];
  def resetCallback();
  
   
 
  def processPrevoiusEvents(currentId : ID);
  def onEvent( event : EVENT,position : Int );
  def getId( event : EVENT ) : ID;
  
}