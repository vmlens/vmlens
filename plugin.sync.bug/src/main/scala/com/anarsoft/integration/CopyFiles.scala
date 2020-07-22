package com.anarsoft.integration

import net.liftweb.json._;
import java.io._;
import org.apache.commons.io._;
import java.io._;
import collection.JavaConverters._
import org.apache.commons.io.IOUtils

class CopyFiles {
  
  
  implicit val formats = DefaultFormats
  
  def copy(target : String,targetDir : File)
  {
       
        
        val stream = this.getClass.getClassLoader.getResourceAsStream("copy/copy.json")
        
        
       val copyDescription =  parse ( IOUtils.toString( new InputStreamReader( stream ) ) ).extract[CopyDescription] 
        
        
      for( targetDesc <- copyDescription.targets  )
      {
        
        if(  targetDesc.name.equals( target ) )
        {
          
          for( c <- targetDesc.copyFiles )
          {
            
            val td = new File(  targetDir.getAbsolutePath + "/" + c.dir );
            
            td.mkdirs();
            
            val input = this.getClass.getClassLoader.getResourceAsStream( c.path );
            val output = new FileOutputStream( td.getAbsolutePath + "/" + c.name );
            
            
            
             IOUtils.copy(input, output)
            
             input.close();
             output.close();
            
            
          }
          
          
          
        }
        
        
        
      }
        
        
        
        
        
  }
  
  
  
}