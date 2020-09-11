package com.anarsoft.race.detection.process.result


import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.workflow.SingleStep


class StepBuildFieldAndArrayFacade extends SingleStep[ContextFieldAndArrayFacade] {

  def execute(context : ContextFieldAndArrayFacade)
  {
    
    
    
    
    /*
     * class ArrayAndFieldOrdinalMap(val  fieldId2Ordinal : HashMap[Int,Int]
    ,val fieldOrdinal2FieldModelDesc : Array[FieldModel] , 
    val arrayAggregateId2Ordinal : Array[Int] ,
    val arrayOrdinal2Model : Array[ArrayModel]
     */
    
      context.fieldAndArrayFacade =
     new FieldAndArrayPerMethodFacade( 
         context.arrayAndFieldOrdinalMap.fieldOrdinal2FieldModelDesc , context.arrayAndFieldOrdinalMap.arrayAggregateId2Ordinal , context.arrayAndFieldOrdinalMap.arrayOrdinal2Model , context.classId2Name );

    
     
  }
  

  
}