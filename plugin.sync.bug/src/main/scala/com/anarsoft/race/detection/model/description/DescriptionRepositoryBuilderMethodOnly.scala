package com.anarsoft.race.detection.model.description

import com.anarsoft.race.detection.model.graph._
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;
import com.vmlens.api.MemoryAccessType


class DescriptionRepositoryBuilderMethodOnly(val methodOrdinal2MethodModel : Array[MethodModel] ,
    val methodId2Ordinal :  ModelKey2OrdinalMap[Int] ,val ownerId2Name :  HashMap[Int,String] ) extends DescriptionRepositoryBuilder 
{
  
    def  visitFieldAccessDescription( name : String,  owner : String,  id  : Int,
       isStatic : Boolean,  isWrite : Boolean, isTraced : Boolean, isFinal : Boolean)
  {
      
  }
  
  
    def visitFieldDescription( name : String,  id : Int,  desc : String,  access : Int)
    {
      
    }
  
  
  
}