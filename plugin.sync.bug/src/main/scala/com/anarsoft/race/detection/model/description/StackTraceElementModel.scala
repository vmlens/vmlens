package com.anarsoft.race.detection.model.description

import com.anarsoft.integration._
import com.anarsoft.race.detection.model.result._;

class StackTraceElementModel(  
           val className : String, 
           val methodName : String, 
           val fileName : String, 
           val linenumber : Int) extends Equals with MethodModel {
 
   def classModelOption =  None;
  
   def rootPackageName() = Some( SearchData.getRootPackageName(className)  ); 
  
  
     def getLineNumber() = "";
  
 
 def owner = None;
  
  def searchData() =Some( SearchData( className , SearchDataLineNumber(linenumber) ) );
  
   def threadCount = 0;
  
  def canEqual(other: Any) = {
             other.isInstanceOf[StackTraceElementModel]
           }

  override def equals(other: Any) = {
             other match {
               case that: StackTraceElementModel => that.canEqual(StackTraceElementModel.this) && className == that.className && methodName == that.methodName && fileName == that.fileName && linenumber == that.linenumber
               case _ => false
             }
           }

  override def hashCode() = {
             val prime = 41
             prime * (prime * (prime * (prime + className.hashCode) + methodName.hashCode) + fileName.hashCode) + linenumber.hashCode
           }
  
   def accept[T]( visitor : MethodModelVisitor[T]) =
   {
     visitor.visit(this);
   }
  
    def getFullNameWithoutBracket()   = ClassModel.replaceShlashWithDot( className )  +"." + methodName ;
     
       
       def getFullName() =  MethodModelFromTrace.getFullName(className , methodName ) + "()";
  
    def getFullNameWithBoldName() =  MethodModelFromTrace.getFullNameWithBoldMethodName(className , methodName )  + "()" ;
       
       
   override def toString() =  "StackTraceElementModel "   + className + " " + methodName;
       
    def getNameWithDescription()  =  methodName;
    
    
     def isThreadSafe  = false;
 def isStateless = false;
 def except  = Array.ofDim[String](0);
    
    
    
    
    
    
    
}