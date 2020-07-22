package com.vmlens.api.internal



class ModelFilter(val exclude : Seq[String]) {
 
 def takeForMemoryAndMonitorAccess( fullyQualifiedName : String) =
 {
//   ! ( fullyQualifiedName.startsWith("java") || fullyQualifiedName.startsWith("sun") ||
//   fullyQualifiedName.startsWith("com.sun")  || fullyQualifiedName.startsWith("org.apache.maven.surefire")
//   || fullyQualifiedName.startsWith("com.anarsoft.vmlens.concurrent.junit") || fullyQualifiedName.startsWith("com.vmlens.maven.plugin")
//   || fullyQualifiedName.startsWith("org.junit") )
   
   
  
   
   var take = true;
   
   for( ex <- exclude )
   {
     if(fullyQualifiedName.startsWith(ex) )
     {
      take = false; 
     }
   }
   
   take;
   
   // org.apache.maven.surefire
 }
  
  
  
}