package com.anarsoft.race.detection.model.description

import com.anarsoft.race.detection.model._;
import com.anarsoft.integration._;
import com.anarsoft.race.detection.model.result._;
import org.objectweb.asm.Opcodes;
import  com.vmlens.api.internal.reports.Model2View
import scala.collection.mutable.HashMap

case class MethodModelFromTrace( val methodName : String,  
    val className : String, val desc : String, val access : Int , val lineNumber : Int,val classModel :   ClassModel) extends Equals with MethodModel   {
  def canEqual(other: Any) = {
      other.isInstanceOf[com.anarsoft.race.detection.model.description.MethodModelFromTrace]
    }
  
   def classModelOption = Some(classModel);
  
  
   def rootPackageName() = Some( SearchData.getRootPackageName(className)  ); 
  
  
   def searchData() = Some( SearchData( className , SearchDataLineNumber(lineNumber) ) );
  
  
  var threadCount = 0;
  
   def getLineNumber() = {
     
     if( lineNumber > -1 )
     {
       " (" + classModel.source + ":" +  lineNumber + ")"
     }
     else
     {
       "";
     }
     
   }
  
  
  
    def getMethodNameAndDesc() = new MethodNameAndDesc(methodName , desc) ;


  
  
   def getInternalFullyQualifiedClassName(modelFacade : ModelFacadeAll) = className;
  
    def accept[T]( visitor : MethodModelVisitor[T]) =
   {
     visitor.visit(this);
   }
    
    
    
    def isPublic() = ( access & Opcodes.ACC_PUBLIC  ) == Opcodes.ACC_PUBLIC
    
      def isSynchronized() = ( access & Opcodes.ACC_SYNCHRONIZED  ) == Opcodes.ACC_SYNCHRONIZED



     def getFullNameWithoutBracket()   =  MethodModelFromTrace.getFullName(className , methodName ) ;
    def getFullName() =  MethodModelFromTrace.getFullName(className , methodName ) +   ParseDescription.createArgumentString(desc) ;
  
    def getFullNameWithBoldName() =  MethodModelFromTrace.getFullNameWithBoldMethodName(className , methodName ) +  Model2View.makeBreakable(  ParseDescription.createArgumentString(desc) );
    
    
    def getNameWithDescription() = methodName +   ParseDescription.createArgumentString(desc) ;
    
    
     def isStatic() =  ( access & Opcodes.ACC_STATIC  ) == Opcodes.ACC_STATIC
    
     
      def isThreadSafe =   classModel.isThreadSafe;
 def isStateless  =   classModel.isStateless
 def except  =   classModel.except
  
 
 
     
}

object MethodModelFromTrace
{
  
  def getFullName(className : String , methodName : String) = ClassModel.replaceShlashWithDot( className )  +"." + methodName;
  
  def getFullNameWithBoldMethodName(className : String , methodName : String) = Model2View.makeBreakable(  ClassModel.replaceShlashWithDot( className )  +"." ) + "<strong>" + methodName + "</strong>";
    
  
  
  
}
