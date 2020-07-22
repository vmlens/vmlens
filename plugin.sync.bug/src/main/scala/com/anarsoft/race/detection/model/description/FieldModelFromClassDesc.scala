package com.anarsoft.race.detection.model.description

import com.anarsoft.integration._;

import  com.vmlens.api.internal.reports.Model2View
import com.anarsoft.race.detection.model.result._
import org.objectweb.asm.Opcodes;
import com.vmlens.api._;
/*
 * private final int access;
	private final String name;
	private final String desc;
	private final String signature
 */

case class FieldModelFromClassDesc(val className : String, val name : String,  val desc : String,  val access : Int) extends FieldModel {
  
   
  
  
  def getClassName() = className;
  def getName() = name;
 
  

      def getNameWithBold() =  Model2View.makeBreakable(  getClassName() + "." )   + "<strong>"  + getName()  + "</strong>"; //  fieldModel.getClassName() + "." + fieldModel.getName();
   
    
  def getDisplayName()  =   getClassName() + "." + getName();
  
   def getSearchData() =  Some(  SearchData( getClassName() , new SearchDataInClassField( getName() )  )  )
  

  
}