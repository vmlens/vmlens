package com.anarsoft.race.detection.process

import java.util.ArrayList;
import java.util.Comparator; 
import java.util.Arrays


object SortArrayList {
  
  
  def sort[EVENT](list : ArrayList[_ <: EVENT] , comparator : Comparator[EVENT])
  {
    
    // first check if we are running in jdk 8, e.g. sort is available:
    
    
    try{
      
    
    
    val method = list.getClass().getDeclaredMethod("sort", classOf[Comparator[Object]])
    
    method.invoke(list,  comparator.asInstanceOf[Comparator[Object]] )
    
    
    }
    catch
    {
      case exp : NoSuchMethodException  =>
        {
          // we are on jdk 7 or 6
          
             val field = list.getClass.getDeclaredField("elementData");
              field.setAccessible(true);
    
              val data = field.get(list).asInstanceOf[Array[Object]];
    
    
              Arrays.sort( data, 0, list.size(),   comparator.asInstanceOf[Comparator[Object]] );
        }
      
      
      
    }
    
    
    
    
 
    
    
    
    
    
  }
  
  def sort[EVENT](list : ArrayList[_ <: EVENT] ,toSize : Int , comparator : Comparator[EVENT])
  {
    val field = list.getClass.getDeclaredField("elementData");
    field.setAccessible(true);
    
    val data = field.get(list).asInstanceOf[Array[Object]];
    
    
    Arrays.sort( data, 0, toSize ,   comparator.asInstanceOf[Comparator[Object]] );
    
    
    
    
    
  }
  
  
  
  
  
  
  
}