package com.anarsoft.race.detection.process.mode.state

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue



class CreateGroupsByPackageNameAlgo(val maxSize : Int) {

  
  def build(sharedStateList : Seq[SharedState]) =
  {
    
    var result =  new ArrayBuffer[ListOfNameAndState]
    
    val  nameAndStateList = sharedStateList.map( x => NameAndState(x)  )
    
    val elements = CreateGroupsByPackageNameAlgo.seperate(nameAndStateList);
    
    val queue = new Queue[ListOfNameAndState]
    
    for( e <- elements )
    {
      result.append(e);
  
      if( ! e.done()  )
      {
        queue.enqueue(e);
      }
      
      
      
    }
    
    
    while(  ! queue.isEmpty && result.length < maxSize ) 
    {
      
      val current = queue.dequeue();
      
      val seperated =   current.seperate()
      
      if( ( seperated.length + result.length  ) < maxSize  )
      {
        val newList =  new ArrayBuffer[ListOfNameAndState]
        
        for( x <- result )
        { 
           if( x != current )
           {
             
             newList.append(x);
             
           }
          
           

          
          
        } 
        
       for( x <- seperated )
           {
              newList.append(x);
  
              if( ! x.done()  )
              {
                      queue.enqueue(x);
              }
           }
        
        
        
        result = newList;
        
      }
      
      
      
      
      
      
    }
    
    
    
    
    
    
   
    result.map(  x =>  x.createSharedState() )
    
    
  }
  
  
  
  
  
}

object CreateGroupsByPackageNameAlgo
{
  
  def seperate(nameAndStateList : Seq[NameAndState]) =
  {
     val result = new ArrayBuffer[ListOfNameAndState];
     val name2ListOfNameAndState = new HashMap[String,ListOfNameAndState]
     
     
     for( elem <- nameAndStateList )
    {
      
      if( elem.hasNext() )
      {
        val name = elem.next();
        
        name2ListOfNameAndState.get(name) match
        {
          
          case None =>
            {
              val n = ListOfNameAndState(elem);
              name2ListOfNameAndState.put(  name , n );
              
              result.append(n)
            }
          
          case Some(x) =>
            {
              x.add( elem );
            }
          
        }
        
        
      }
      else
      {
          result.append(ListOfNameAndState(elem))
      }
      
    }
     
     result
     
  }
  
  
}