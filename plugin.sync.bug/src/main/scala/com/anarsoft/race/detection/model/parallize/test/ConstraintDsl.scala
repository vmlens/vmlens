package com.anarsoft.race.detection.model.parallize.test

import  com.vmlens.api.FieldDescription


abstract class ConstraintDsl {
  
}


case class Read(val threadIndex : Int , val field : FieldDescription) extends ConstraintDsl;


case class Write(val threadIndex : Int, val field : FieldDescription) extends ConstraintDsl;


case class Atomic(val threadIndex : Int,val field : FieldDescription) extends ConstraintDsl;
