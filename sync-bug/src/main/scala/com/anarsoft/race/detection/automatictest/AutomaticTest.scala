package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.preanalyzed.model.{ClassModel, MethodToMethodType}

import java.io.PrintWriter
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class AutomaticTest {

  private val idToMethod = new mutable.HashMap[Int,AutomaticTestMethod]
  
  def createBuilder(automaticTestMethodId : Int, automaticTestType : Int): Option[AutomaticTestMethodBuilder] = {
    if(idToMethod.contains(automaticTestMethodId)) {
      None
    } else {
      Some(new AutomaticTestMethodBuilder(automaticTestMethodId,automaticTestType,this))
    }
    
  }

   def put(key: Int, value: AutomaticTestMethod): Unit = idToMethod.put(key, value)

  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    for (elem <- idToMethod) {
      elem._2.addToNeedsDescription(callback);
    }
  }

  def buildPreAnalyzedClasses(className : String,
                              context: DescriptionContext,
                              writer : PrintWriter,
                              result : ArrayBuffer[ClassModel]): Unit = {
  
    val methodResult = new ArrayBuffer[MethodToMethodType]();
    for (elem <- idToMethod) {
      elem._2.buildPreAnalyzedClasses(className,context,writer,methodResult);
    }
    val internalClassName = className.replace('.', '/');
    result.append(new ClassWithMethodToMethodType(internalClassName,methodResult.toList, List()));
  }
  
}
