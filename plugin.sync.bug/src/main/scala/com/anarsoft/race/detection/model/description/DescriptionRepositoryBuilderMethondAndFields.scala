package com.anarsoft.race.detection.model.description




import com.anarsoft.race.detection.model.description._
import java.io._
import com.anarsoft.race.detection.model.graph._
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;
import com.vmlens.api.MemoryAccessType
import com.typesafe.scalalogging.Logger

class DescriptionRepositoryBuilderMethondAndFields (val methodOrdinal2MethodModel : Array[MethodModel] ,
    val methodId2Ordinal :  ModelKey2OrdinalMap[Int], val arrayAndFieldOrdinalMap  : ArrayAndFieldOrdinalMap , val ownerId2Name :  HashMap[Int,String]   ) extends DescriptionRepositoryBuilder {

  

  val methodOrdinalAndFieldOrdinal2Operation = new HashMap[MethodOrdinalAndFieldOrdinal,Int]
  
  
  
  def  visitFieldAccessDescription( name : String,  owner : String,  id  : Int,
       isStatic : Boolean,  isWrite : Boolean, isTraced : Boolean, isFinal : Boolean)
  {
    
      arrayAndFieldOrdinalMap.setFieldModelFromAccess(id, new FieldModelFromAccess(name,replaceSlahWithDot(owner) , isStatic  )) match
      {
        
        case None =>
          {
            
          }
        
        case Some(fieldOrdinal) =>
        {
          currentMethodOrdinal match
          {
            case None =>
              {
                
              }
            
            case Some(methodOrdinal) =>
              {
                val key = new MethodOrdinalAndFieldOrdinal(methodOrdinal,fieldOrdinal);
                val op = methodOrdinalAndFieldOrdinal2Operation.getOrElseUpdate(key, 0);
                
                val addOp = 
                  if( isWrite )
                  {
                    MemoryAccessType.IS_WRITE
                  }
                  else
                  {
                    MemoryAccessType.IS_READ
                  }
                
                methodOrdinalAndFieldOrdinal2Operation.put( key , op | addOp   );
                
                
              }
            
            
          }
          
          
          
        }
        
        
      }
   
     
     
     
   
  }


  def visitFieldDescription( name : String,  id : Int,  desc : String,  access : Int)
  {
   
     val model =  new FieldModelFromClassDesc( currentClassName : String,  name : String,   desc : String,   access : Int);
    
     logger.debug(id + " " +  model);
      
     arrayAndFieldOrdinalMap.setFieldModelFromClassDesc(id,model)
    
  }


}

