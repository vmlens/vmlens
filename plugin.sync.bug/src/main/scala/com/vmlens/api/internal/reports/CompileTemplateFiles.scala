package com.vmlens.api.internal.reports


import com.vmlens.api._;
import org.fusesource.scalate._
import java.io.PrintStream
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import org.apache.commons.io.IOUtils
import org.fusesource.scalate.support.StringTemplateSource
import scala.collection.JavaConverters._
import com.vmlens.api.internal._;
import com.anarsoft.race.detection.model.result._;
import java.io._;
import com.anarsoft.integration._;
import io.Codec

object CompileTemplateFiles {
  
  
  def compileAll(pathPrefix : String)
  {
  
    val dir = new File( pathPrefix + "templates" );
    
    
    for( file <- dir.listFiles() )
    {
      if(file.getName().endsWith(".mustache") )
      {
        val temp = file.getName().replace(".mustache", ".scala");
        
        val classFile = temp.charAt(0).toUpper + temp.substring(1);
        
        compile(pathPrefix , file , classFile);
        
      }
    }
    
    
    
  }
  
  
  
  def compile( pathPrefix : String , templateFile : File, classFile : String )
  {
    val templateIndex = new FileInputStream(  templateFile );
       val templateTextIndex  = IOUtils.toString(templateIndex)
     
//          val templateIssue = this.getClass.getClassLoader.getResourceAsStream( "templates/htmlIssue.mustache" );
//       val templateTextIssue  = IOUtils.toString(templateIssue)
//       
       
         val engine = new TemplateEngine
         
       val code =   engine.generateScala(new StringTemplateSource( "templates/" + templateFile.getName , templateTextIndex ))
   
       
       val path = pathPrefix + "src/main/scala/com/vmlens/api/internal/reports/gen/";
       
       
       val writer = new PrintStream(path + classFile);
       
          writer.write(code.source.getBytes(Codec.UTF8.charSet)); 
          writer.close()
       
  }
  
  
  def main( args : Array[String])
  {
     
//       compile( "" ,  "templates/htmlIndex.mustache"  , "HtmlIndex.scala");
//        compile( "", "templates/htmlIssue.mustache"  , "HtmlIssue.scala");
    compileAll("");
  }
  
  
}