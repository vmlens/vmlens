package com.anarsoft.race.detection.model.result

abstract sealed class SearchDataInClass {
  
}


case class SearchDataInClassField(val fieldName : String) extends SearchDataInClass;


case class SearchDataLineNumber(val lineNumber : Int) extends SearchDataInClass;
