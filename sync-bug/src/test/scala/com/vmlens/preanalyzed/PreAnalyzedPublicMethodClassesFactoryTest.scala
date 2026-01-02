package com.vmlens.preanalyzed

import com.vmlens.preanalyzed.PreAnalyzedPublicMethodClassesFactoryTest.checkClasses
import com.vmlens.preanalyzed.factory.publicmethods.PreAnalyzedPublicMethodClassesFactory
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import org.objectweb.asm.ClassReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable
import org.objectweb.asm.Opcodes.*

class PreAnalyzedPublicMethodClassesFactoryTest extends AnyFlatSpec  {

  "The classes in StandardPreAnalyzedClassesFactory " should
    "exactly contain the public methods from the classes in the jdk" in {
    checkClasses( PreAnalyzedPublicMethodClassesFactory.classList(), take );
  }

  
  
  def take(access : Int, name : String) : Boolean = {
    val isPublic = (access & ACC_PUBLIC) != 0
    val isAbstract = (access & ACC_ABSTRACT) != 0

    isPublic && !isAbstract && ! name.startsWith("<")
  } 
  

}

object PreAnalyzedPublicMethodClassesFactoryTest extends  Matchers {
  
  def checkClasses(list : List[ClassWithMethodToMethodType],  take : (Int,String) => Boolean): Unit = {
  
    for (elem <- list) {
      val traced = new mutable.HashSet[NameAndDescription]
      for (method <- elem.methods) {
        traced += NameAndDescription(method.name, method.desc)
      }

      try {
        val reader = new ClassReader(elem.name)
        val publicMethodCollector = new MethodCollector(elem.notTracedMethods.toSet, take);
        reader.accept(publicMethodCollector, 0)

        val inClass = publicMethodCollector.getMethods;

        // printForFilter(inClass.diff(traced));
        printForMethodList(inClass.diff(traced));

        traced.diff(inClass) should be(empty)
        inClass.diff(traced) should be(empty)
      } catch {
        case ex: java.io.IOException =>
          println("not found " + elem.name)
          println()
      }
    }
  }

  def printForMethodList(difference: Set[NameAndDescription]): Unit = {

    for (elem <- difference) {
      println("new MethodToMethodType(\"" + elem.name + "\", \"" + elem.description + "\",    ),");
    }

  }
  
  def printForFilter(difference: Set[NameAndDescription]): Unit = {
    val result = new StringBuilder()
    for (elem <- difference) {
      result.append("\n" + elem.name + "\n")
      result.append(",")
    }
    println(result);
  }
  
}


