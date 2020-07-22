package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.model.description._
import scala.collection.mutable.HashMap

class DescVisitorFillMap(val methodId2Name : HashMap[Int,String], val  fieldId2Name : HashMap[Int,String]) extends   DescVisitor {
  
  
  var currentClassName  : String = null 
  
  
  def visitClassDescription( name : String,source : String , isThreadSafe : Boolean, isStateLess : Boolean, except : Array[String], superName : String, interfaces: Array[String])
  {
    currentClassName  = name;
  }
  
  
	def visitMethodDescription( name : String, id : Int, desc : String,access : Int,lineNumber : Int )
	{
	   
	    methodId2Name.put( id , currentClassName + "." +  name );
	  
	}
	
	
	def visitFieldAccessDescription( name: String,  owner : String,  id : Int,
			 isStatic : Boolean,  isWrite : Boolean, isTraced : Boolean,
			 isFinal : Boolean)
	{
	  fieldId2Name.put( id , owner + "." + name );
	  
	  
	  
	  
	}
	
	
	def visitFieldDescription( name : String,  id : Int,  desc : String,  access : Int)
	{
	 fieldId2Name.put( id , currentClassName + "." + name );
	}
  
	
	 def visitAnnotationArray(annotationArray: Array[String])
	 {
	   
	 }
  
  
  def endMethod()
  {
    
  }
  
    def visitOwner( id : Int , name : String  )
    {
      
    }
  
  
}