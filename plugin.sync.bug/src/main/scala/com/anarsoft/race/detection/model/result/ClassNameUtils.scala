package com.anarsoft.race.detection.model.result

object ClassNameUtils {
 
  def getPackageName(fullyQualifiedclassName : String) =
   {
       
          var packageName = "default package";
          var className = "";     
          
          val index = fullyQualifiedclassName.lastIndexOf('.');
          
         if( index > 0 )
         {
           packageName = fullyQualifiedclassName.substring(0, index);
           
           className = fullyQualifiedclassName.substring(index+1)
           
         }
         else
         {
           className = fullyQualifiedclassName;
         }
           
         
          packageName;
         
   }
  
  
  def getFirstPackageName(fullyQualifiedclassName : String) =
  {
    
          var className = "";     
          
          val index = fullyQualifiedclassName.indexOf('.');
          
         if( index > 0 )
         {
            fullyQualifiedclassName.substring(0, index);
          
           
         }
         else
         {
           fullyQualifiedclassName;
         }
        
       
  }
  
  
  
}