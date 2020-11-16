package com.vmlens.api.internal.reports

import com.anarsoft.race.detection.model.result._;
import java.io._;
import com.vmlens.api.internal.reports.element._;
import scala.collection.mutable.ArrayBuffer
import net.liftweb.json._;
import org.apache.commons.io._;
import net.liftweb.json.Extraction._
import com.vmlens.api.internal.ReportData4Plugin
import scala.collection.mutable.HashMap
import java.nio.file.Files
import com.github.difflib.DiffUtils;
import collection.JavaConverters._
import com.anarsoft.integration.CopyFiles
import org.apache.commons.lang.StringEscapeUtils
import com.github.difflib.patch.Chunk 
 
object ReportFacade {
  
  // viewResult : ViewResult, reportDir : File, elementTemplate : String, elementDetailTemplate : String , elementTitle : String , elementFileName : String , titleIndex : String
  
       implicit val formats = DefaultFormats 
  
  
  
  val callOffline = new CallView2X[Unit]()
  {
    def  call(viewResult : ViewResult[ReportElement[ContextReport]], reportDir : File, elementTemplate : String, elementDetailTemplate : String , elementTitle : String, noElementsTemplate : String) =
    {
       View2X.save2File( viewResult, reportDir , elementTemplate, elementDetailTemplate, elementTitle  , noElementsTemplate )
    }
    
    
  }
   
  
  val callOnline = new  CallView2X[ResultHtmlOnline]()
  {
    def  call(viewResult : ViewResult[ReportElement[ContextReport]], reportDir : File, elementTemplate : String, elementDetailTemplate : String , elementTitle : String, noElementsTemplate : String) =
    {
      // (viewResult : ViewResult[ReportElement], elementTemplate : String, elementDetailTemplate : String , elementTitle : String , elementFileName : String , titleIndex : String) 
      
       View2X.createOnlineMap( viewResult , elementTemplate, elementDetailTemplate, elementTitle , noElementsTemplate   )
    }
    
    
  }
  
  
  
 
  
  
    def save2File(modelFacade : ModelFacadeAll, reportDir : File)
  {
//       val viewData =  TransformModel2View.transformAll(modelFacade);      
//       View2X.save2File(viewData , reportDir ,  "templates/htmlParallizedGroups.mustache"    , "none" , "Scheduled" , "scheduled.html" , Model2View.headerIssue );
       
   callOffline.templateAll(modelFacade, reportDir);
  }
    
    
    
  
    
    
    
    def createReportData4Plugin(modelFacade : ModelFacadeAll) =
    {
       val viewData =  TransformModel2View.transformAll(modelFacade);   
       
       val onlineData =     callOnline.templateAll(modelFacade, new File(""));
       
       
    new ReportData4Plugin( Left( viewData.issues ) , "elements.html" ,onlineData );
       
  
       
    }
    
    
   
   
    
    
    def createExceptionReport4Plugin(throwable : Throwable) =
    {
        val resultHtmlOnline = new ResultHtmlOnline();
        val contextReportBuilder = new ContextReportBuilder(resultHtmlOnline,"","" ,"" );
            val context = contextReportBuilder.createContext(true,false,true);
           
            
         val issues = new ArrayBuffer[ReportElement[ContextReport]]   
            
         issues.append( new ReportElementError(throwable) );
            
            resultHtmlOnline.addView(  Model2View.PATH_INDEX ,  new ViewProvider( "templates/htmlException.mustache" , Model2View.PATH_INDEX  ,
          new   ViewData(    issues , Nil  ,  throwable.getClass.getName , None , "" , context ) ) )
       
         
         
         
            
            
             new ReportData4Plugin( Right(  Model2View.PATH_INDEX  ) ,  Model2View.PATH_INDEX   ,resultHtmlOnline );
    }
    
    
    
    
    def saveDeadlockAndDataRaceDetectionFalse(reportDir : File){
      
         saveStaticHtml(reportDir , "deadlockAndDataRaceDetectionFalse.mustache");
      
    }
    
    
    def saveNoTestToRunReport2File(reportDir : File)
    {
      
    // htmlNoTestToRun.mustache
      saveStaticHtml(reportDir , "htmlNoTestToRun.mustache");
      
    }
    
    
    
    def saveStaticHtml( reportDir : File , templateName : String  ) {
       val noTestResults =  new HtmlProviderStaticSiteWithHeader("templates/" + templateName);
      
    copyImgCssFiles( reportDir);
    
     
      val printStream = new PrintWriter(reportDir.getAbsolutePath() +  "/"   + "issues.html");
     
     
      noTestResults.write2Stream("" , printStream);
  
     
     
    
     printStream.close();

    }
    
    
    
    def copyImgCssFiles(reportDir : File)
    {
            val copyFiles = new CopyFiles();
     copyFiles.copy("mvnHtmlReport", reportDir);
    }
    
    
    
    
     def checkIssues(expectedDirString : String, runResult: ModelFacadeAll) =
    {
         val expectedRunCount = new File(expectedDirString + "/issues.expected");
         
         
         
         if(expectedRunCount.exists())
         {
             val dataStream = new FileInputStream(expectedRunCount);
     val contents =    IOUtils.toString(dataStream);
    val json = parse( contents );
    
     val   set    = json.extract[Set[String]]
     
     val name2Found = new HashMap[String,Boolean]
     
     for( elem <- set )
     {
       name2Found.put(elem , false);
     }
     
     
     
     for(elem <- runResult.races ++ runResult.deadlocks )
     {
      
       val name = elem.name(runResult);
       
       if( ! set.contains(name) )
       {
         throw new RuntimeException("not expected issue " + name);
       }
       
       
       name2Found.put(name, true)
     
     
    }
     
     
     for(elem <- name2Found)
     {
       if(! elem._2 )
       {
         throw new RuntimeException("not there " + elem._1);
       }
     }
     
     
     
     true;
     
         }
         else
         {
           false;
         }
         
         
    }
    
    
    
    
    
    
      def test(expectedDirString : String, actualDir : File, runResult: ModelFacadeAll)
  {
      
      
       val nameOnly = checkIssues(expectedDirString , runResult);
        
        
        
      val expectedDir = new File(expectedDirString);  
      if(  compareDir(expectedDir , actualDir , "") == 0  && !nameOnly )
      {
        throw new RuntimeException("files for comparison missing");
      }
  }
    
    
   //implicit val formats = TestableElementFromJson.jsonFormats;  
    
    
  def test(expectedDirString : String, actualDir : File)
  {
      val expectedDir = new File(expectedDirString);  
      if(  compareDir(expectedDir , actualDir , "") == 0 )
      {
        throw new RuntimeException("files for comparison missing");
      }
  }
     
   
  
   
  

    
    def compareDir(expectedDir : File, actualDir : File , directory : String) : Int =
    {
      
        var compareCount = 0;
      
             for( file <- expectedDir.listFiles() )
       {
         
               if( file.isDirectory() )
               {
                
                 
                 compareCount  = compareCount  + compareDir(file , actualDir  ,directory +  file.getName()  + "/" );
               }
               else
               {
                 if( file.getName().endsWith(".html") )
                 {
                   compareFile( file, actualDir , directory );
                   compareCount = compareCount+ 1;
                 }
               }
               
       }
             
             compareCount;
       
    }
    
    
    
    def filter(list : java.util.List[String],set : Set[String]) =
    {
      val result = new java.util.LinkedList[String]
      val iter = list.iterator();
      
      while( iter.hasNext() )
      {
        val current = iter.next();
        
        if( ! current.trim().startsWith("<!-- skipAtCompare -->") )
        {
          
          val readable = toReadableString(current);
          
          var notInSet = true;
          
          for( elem <- set)
          {
            if(readable.startsWith(elem) )
            {
               notInSet = false
            }
          }
          
          if(notInSet)
          {
             result.add(current);
          }
         
        }
        
      }
      
      result;
    }
    
    
    def toReadableString(in : String) = 
    {
      StringEscapeUtils.unescapeHtml( in.replace("<wbr>", "") ).trim()
    }
    
    
  def toString( chunk : Chunk[_]  ) =
  {
  
    var text = "";
    
    val iter = chunk.getLines.iterator();
    
    while( iter.hasNext() )
     {
      text = text + iter.next();
    }
    
    
   val temp =  toReadableString(text)
    
   println(temp);
   
   temp; 
 }
    
    
    
    
    
    def compareFile( expectedFile : File, actualDir : File, directory : String)
    {
       val  expected = Files.readAllLines(expectedFile.toPath());
       val  actual = Files.readAllLines(new File(actualDir.getAbsolutePath() + "/" + directory + expectedFile.getName()).toPath());
         
       var set =  Set[String]();
       
             val filterSetFile = new File( expectedFile.getAbsolutePath().replace(".html", ".diff") )
          
          if(filterSetFile.exists())
          {
              val dataStream = new FileInputStream(filterSetFile);
     val contents =    IOUtils.toString(dataStream);
    val json = parse( contents );
    
       set    = json.extract[Set[String]]
      
      
          }
          
             
         var alternatives =  Set[String]();
       
             val alternativeSetFile = new File( expectedFile.getAbsolutePath().replace(".html", ".alt") )
          
          if(alternativeSetFile.exists())
          {
              val dataStream = new FileInputStream(alternativeSetFile);
     val contents =    IOUtils.toString(dataStream);
    val json = parse( contents );
    
       alternatives    = json.extract[Set[String]]
      
       println(alternatives);
       
      
          }      
             
       
       
       
          val patch = DiffUtils.diff(filter(expected,set), filter(actual,set));
      
       
    
          
          
          if(  ! patch.getDeltas.isEmpty()  )
          {
            
            var isAlternative = ! alternatives.isEmpty;
            val iter =  patch.getDeltas().iterator();
          
         while( iter.hasNext()  )
         {
           
           
           
                 val delta = iter.next();
                 
                 if(isAlternative)
                 {
                   if(delta.getSource != null && delta.getTarget != null)
                   {
                     
                     
                     
                     
                     if( ! alternatives.contains( toString( delta.getSource ) ) )
                     {
                       println("not contained " +  toString( delta.getSource ) );
                       
                       isAlternative = false;
                     }
                     
                       if( ! alternatives.contains(  toString( delta.getTarget ) ) )
                     {
                         
                            println("not contained " +    toString( delta.getTarget ) );
                         
                       isAlternative = false;
                     }
                     
                   }
                   else
                   {
                     isAlternative = false;
                   }
                 }
                 
                   if(! isAlternative)
                 {
                       System.err.println(   toReadableString (delta.toString() ));
                 }
    
              
            }
             
         
         if(! isAlternative)
         {
           throw new RuntimeException("difference in result for expectedFile " +  expectedFile.getAbsolutePath + "  to actual dir" + actualDir.getAbsolutePath);
         }
             
         
          
         
         
          }
          
         
    }
    
  
  
  
    def main(args : Array[String])
    {
//      val map = new HashMap[String,Int]
//      
//      map.put("aaaa" , 1);
//      
//      println( pretty(render(decompose(map))));
//      

//    val text = """{  "4444" : 5 }"""
//    
//    
//      val json = parse( text );
//    
//      val set    = json.extract[Map[String,Int]]
//      
//      println( set.get("4444") );
      
      
      val set = Set( "aa"  , "ss");
      
       println( pretty(render(decompose(set))));
      
    }
     
     
     

    
    
    
    
  
  
  
}