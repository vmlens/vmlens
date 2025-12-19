package com.vmlens.preanalyzed

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

import scala.collection.mutable

class MethodCollector(notTraced : Set[String], take : (Int,String) => Boolean) extends ClassVisitor(ASM9) {
  
  private val methods = mutable.HashSet.empty[NameAndDescription]

  def getMethods: Set[NameAndDescription] = methods.toSet

  override def visitMethod(
                            access: Int,
                            name: String,
                            descriptor: String,
                            signature: String,
                            exceptions: Array[String]
                          ): MethodVisitor = {
   
    
    if (take(access, name)) {
      val nameAndDescription = NameAndDescription(name, descriptor);
      if(! notTraced.contains(nameAndDescription.name)) {
        methods += nameAndDescription
      }
    
    }
    super.visitMethod(access, name, descriptor, signature, exceptions)
  }
}

