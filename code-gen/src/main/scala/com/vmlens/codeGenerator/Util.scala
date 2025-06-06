package com.vmlens.codeGenerator

object Util {
  
  
 def getCamelCase(name : String) = Character.toUpperCase(name.charAt(0)) + name.substring(1); 
 def getInvertCamelCase(name : String) = Character.toLowerCase(name.charAt(0)) + name.substring(1); 
  
}