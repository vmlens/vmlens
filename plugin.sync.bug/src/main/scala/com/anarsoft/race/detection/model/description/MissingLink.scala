package com.anarsoft.race.detection.model.description

import com.anarsoft.integration._;


class MissingLink extends MethodModel {
  
   def rootPackageName() = None;
  
   def classModelOption =  None;
 
 def owner = None;
 
    def searchData() = None; //Some( SearchData( className , SearchDataLineNumber(lineNumber) ) );
    
    def getLineNumber() = "";
 
  
   def accept[T]( visitor : MethodModelVisitor[T]) =
   {
     visitor.visit(this);
   }
   
    def methodName = "--------- skipping frames -----------";
   def getFullName()  =  methodName;
   def getFullNameWithoutBracket()   =  methodName;
   
   def getFullNameWithBoldName() =  getFullName() ;
   
   
   def className = methodName;
   def getNameWithDescription()  =  methodName;
   
    def threadCount = 0;
   
    
    
    
    
    
 def isThreadSafe  = false;
 def isStateless = false;
 def except  = Array.ofDim[String](0);
    
    
    
    
    
    
}