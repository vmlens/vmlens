package com.vmlens.codeGenerator.domain

import com.vmlens.codeGenerator.Util

class FieldTyp(val name : String, val size : Int , val writeJavaElement : String, val scalaFromByteBuffer : String,  val scalaToByteBuffer : String , val compare : String, val writeToDataOutputStream : String) {
  
  def getFieldDecl() = name;
  
  def getCamelCase() = Util.getCamelCase(name);
  
  
}