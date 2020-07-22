package com.anarsoft.race.detection.model.description

 import com.anarsoft.race.detection.model.result.SearchData;

trait FieldModel {
  
   def getClassName() : String;
 //  def getName()      : String;
  
  
   def getDisplayName() : String;
   
   

   
   def getNameWithBold() : String;
   
   
   def getSearchData() : Option[SearchData];
   
   
   
}