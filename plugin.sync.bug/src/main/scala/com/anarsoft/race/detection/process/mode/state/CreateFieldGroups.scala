package com.anarsoft.race.detection.process.mode.state

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet


object CreateFieldGroups {
  
  
  
  
  def createForMemoryAggregateSet(memoryAggregateSet : Set[Int],modelFacade : ModelFacadeState,size : Int) =
  {
    
    val sharedStateList = new HashSet[SharedState]
    
    for( elem <- memoryAggregateSet)
    {
      val locationInClass = modelFacade.fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(elem);
      
      if( ! locationInClass.isReadOnly() )
      {
          locationInClass.location.toSharedState(modelFacade, elem: Int) match
      {
        case None =>
          {
            
          }
        case Some(x) =>  sharedStateList.add(x);
        
      }
      }
      
      
    
      
      
    }
    
    create( sharedStateList.toSeq.sortBy( x => x.sortBy )  , size );
  }
  
  
  
  
  
  
  
  
  /**
   * same class
   * 	relation ship zum nächsten?
   *
   * sozusagen tree
   * 
   * eigentlich erstmal classes zusammenfassen, danack sub packages danach packages 
   * 
		package
		sub package
		Field
		
		auf 10 - 20 Felder begrenzen
		auf 2 oder 3 für darstellung in stack graph
		
   * 
   * 
   */
  
  def create(sharedStateList : Seq[SharedState],size : Int) =
  {
    if(  sharedStateList.size <= size )
    {
      sharedStateList;
    }
    else
    {
      val createGroupsByPackageNameAlgo = new CreateGroupsByPackageNameAlgo(size);
    createGroupsByPackageNameAlgo.build( sharedStateList );
    
    
    }
  }
  
  
  
}
  