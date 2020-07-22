package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.result.ThreadModel
import com.anarsoft.race.detection.model.result.ThreadNameAndCount
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


object CreateThreadGroups {
  
  
  def main(args : Array[String])
  {
    val set = Set(  new ThreadModel("DefaultThreadPool 11" , 5L) ,   new ThreadModel("DefaultThreadPool 20" , 5L) , 
        new ThreadModel("Compiler Source File Reader" , 20L) , new ThreadModel("Compiler Source File Reader" , 25L) , new ThreadModel("ActiveMQ Transport: tcp:///127.0.0.1:52010" , 29L) , new ThreadModel("ActiveMQ Transport Server: tcp://0.0.0.0:61616" , 35L)  );
  
    println( create(set) )
  
  }
  
  
 
  
  
  def create( threadNames : Set[ThreadModel] ) =
  {
    val pattern2Count = new HashMap[String,NameAndCount]
    
    for(  t <- threadNames )
    {
      
     
      
      val name =
      if( t.name.charAt(   t.name.length() -1 ).isDigit )
      {
        var index= t.name.length() -2 
        
        while(  t.name.charAt( index ).isDigit   )
        {
          index = index  - 1;  
        } 
        index = index+ 1;
        
        
        t.name.substring(0, index) + "**"
        
        
        
      }
      else
      {
        
        t.name
        
      }
      
      val nameAndCount = pattern2Count.getOrElseUpdate( name , new NameAndCount(t.name))
      
     nameAndCount.count =  nameAndCount.count + 1;
      
      
      
    }
    
    val result = new ArrayBuffer[ThreadNameAndCount]
    
    for( elem <- pattern2Count )
    {
      if(  elem._2.count == 1 )
      {
        result.append( new ThreadNameAndCount(  elem._2.name , elem._2.count ) )
      }
      else
      {
        result.append( new ThreadNameAndCount(  elem._1 , elem._2.count ) )
      }
      
      
      
    }
    
    result;
  }
  
  
  
}