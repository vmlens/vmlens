package com.anarsoft.race.detection.model.description

import com.anarsoft.integration._;
import com.vmlens.api._;
import  com.vmlens.api.internal.reports.Model2View
import com.anarsoft.race.detection.model.result._
 
 
class FieldModelFromAccess( val name : String , val owner : String,val isStaticField : Boolean) extends FieldModel  {
  
  
  
  
  def getClassName() = owner;
  def getName() = name;
  
  
    def getNameWithBold() =  Model2View.makeBreakable(  getClassName() + "." )   + "<strong>"  + getName()  + "</strong>"; //  fieldModel.getClassName() + "." + fieldModel.getName();
   
    
  def getDisplayName()  =   getClassName() + "." + getName();
  
   def getSearchData() =  Some(  SearchData( getClassName() , new SearchDataInClassField( getName() )  )  )
  
}