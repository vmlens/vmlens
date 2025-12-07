package com.vmlens.preanalyzed

import com.vmlens.preanalyzed.factory.standard.StandardPreAnalyzedClassesFactory
import org.objectweb.asm.ClassReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

class StandardPreAnalyzedClassesFactoryTest extends AnyFlatSpec with Matchers {

  "The classes in StandardPreAnalyzedClassesFactory " should
    "exactly contain the public methods from the classes in the jdk" in {

    val list = StandardPreAnalyzedClassesFactory.classList();
    for( elem <- list ) {


      val traced = new mutable.HashSet[NameAndDescription]
      for(method <- elem.methods) {
        traced +=  NameAndDescription(method.name,method.desc)
      }


      val reader = new ClassReader(elem.name)
      val publicMethodCollector = new PublicMethodCollector(elem.notTracedMethods.toSet);
      reader.accept(publicMethodCollector,0)

      val inClass = publicMethodCollector.getMethods;

     // printForFilter(inClass.diff(traced));
     // printForMethodList(inClass.diff(traced));

      traced.diff(inClass) should be(empty)
      inClass.diff(traced) should be(empty)
    }
  }

  def printForMethodList(difference :  Set[NameAndDescription]): Unit = {

    for (elem <- difference) {
      println("new MethodToMethodType(\"" +   elem.name +  "\", \"" + elem.description  +  "\",    ),");
    }

  }


  def printForFilter(difference :  Set[NameAndDescription]): Unit = {
    val result = new StringBuilder()
    for(elem <- difference) {
      result.append("\n" + elem.name + "\n")
      result.append(",")
    }
    println(result);
  }

}
