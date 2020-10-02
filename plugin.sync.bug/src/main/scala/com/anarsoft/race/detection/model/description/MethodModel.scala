package com.anarsoft.race.detection.model.description

import com.anarsoft.integration._;
import com.anarsoft.race.detection.model._;
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api._;
import com.anarsoft.race.detection.model.result.SearchData;

trait MethodModel {
  
  var stateNotFiltered = false;
  
  
   def methodName : String;

 def getFullName() : String; // ClassModel.replaceShlashWithDot( className )  +"." + methodName + "()";
 def getFullNameWithoutBracket() : String;
 
 
 def getFullNameWithBoldName() : String;
 
 
 def threadCount : Int;
 
    def getNameWithDescription()  : String;
 
 def className : String;
 
 
 def accept[T]( visitor : MethodModelVisitor[T] ) : T;
 
 
 def rootPackageName() : Option[String]
 
 
  def searchData() : Option[SearchData];
 
 def getLineNumber() : String;
 
// def isThreadSafe : Boolean;  
// def isStateless : Boolean;
 def except : Array[String];
 
 
 def classModelOption : Option[ClassModel]
}
