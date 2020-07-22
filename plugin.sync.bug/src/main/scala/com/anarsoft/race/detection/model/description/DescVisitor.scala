package com.anarsoft.race.detection.model.description

trait DescVisitor {
  
    
  def visitClassDescription( name : String,source : String , isThreadSafe : Boolean, isStateLess : Boolean, except : Array[String], superName : String, interfaces: Array[String]);
	
  def visitMethodDescription( name : String, id : Int, desc : String,access : Int,lineNumber : Int );
  
 // def visitAnnotationArray(annotationArray: Array[String]);
  
  
  def endMethod();
  
  
	
  def visitFieldAccessDescription( name: String,  owner : String,  id : Int,
			 isStatic : Boolean,  isWrite : Boolean, isTraced : Boolean,
			 isFinal : Boolean);
	def visitFieldDescription( name : String,  id : Int,  desc : String,  access : Int);
  
  
  
}