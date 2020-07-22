package com.anarsoft.race.detection.model.description

import org.objectweb.asm.Type
import org.objectweb.asm.Type._



object ParseDescription {
  
  
  
  def createArgumentString(desc : String) =
  {
    var argumentString = "";
    var isFirst = true;
    
    try{
    val types =  Type.getArgumentTypes(desc);
    
       for( t <- types )
       {
         
         val comma = 
            if(!isFirst)
         {
           ","
         }
         else
         {
           ""
         }
         
         
         argumentString = argumentString + comma   + t.getClassName();
         
        
         isFirst = false;
        
       }
       
      "(" +  argumentString + ")";
      
    }
    catch
    {
      
      case exp : ArrayIndexOutOfBoundsException =>
      {
        System.err.println(desc);
        exp.printStackTrace();
        "()";
        
      }
      
    }
       
  }
  
  
  def main( desc : Array[String]) {
	
       
    println( createArgumentString  ( "([Ljava/lang/String;[[Ljava/lang/String;)V" ))
    
    
}
  
  // VOID, BOOLEAN, CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE, ARRAY, OBJECT or METHOD.
  
  def getReadableName(t : Type) =
  {
    
    t.getSort() match
    {
      
      case OBJECT =>
        {
            t.getInternalName() 
        }
        
        
      case _ =>
        {
         t.toString();
        }
      
      
      
      
    }
    
    
  }
  
  
  
}