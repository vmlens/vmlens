package com.anarsoft.race.detection.process.perEventList


import com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 *  */


abstract class PerEventListAbstract[EVENT,CONTEXT] extends PerEventListTrait[CONTEXT] {
  
  
  
  
  
  def getCurrentReadFields ( context : CONTEXT ) :   ArrayList[_ <: EVENT];
  def comparator : Comparator[EVENT]; 
  def processEventList(  list : ArrayList[_ <: EVENT] ,context : CONTEXT  )
  
  
  def process( context : CONTEXT  )
  {
    val list = getCurrentReadFields(context);
    
    if( list != null )
    {
    
      SortArrayList.sort(    list ,comparator);
      processEventList(  list  ,context  );
      
      
      
    }
    
    
    
    
    
  }
  
}