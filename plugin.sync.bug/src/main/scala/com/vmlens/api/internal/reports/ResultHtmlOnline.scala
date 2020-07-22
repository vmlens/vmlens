package com.vmlens.api.internal.reports

import java.util.concurrent.ConcurrentHashMap
import java.io.PrintWriter


class ResultHtmlOnline extends ReportFactory with HtmlProvider {
  
   val path2View = new ConcurrentHashMap[String,Either[ViewProvider,ReportView]]
  
  
   def addView(link : String ,viewProvider : ViewProvider)
   {
     
     
     path2View.put(  link , Left(viewProvider));
   }
   
   
  
   
   def write2Stream(path : String , printStream : PrintWriter)
   {
     val link = 
       if(  path.startsWith("/") )
       {
         path.substring(1);
       }
       else
       {
         path
       }
           
     
     
     
     
     val result = path2View.get(link);
     
     val view = 
     result match
     {
       case Left(provider) =>
         {
           
           val view = provider.view();
           view.initialize();
           path2View.put( link , Right(view)  );
           view
           
           
         }
       
       case Right(x) => x;  
         
       
       
     }
     
     
     
       view.write2Stream(printStream);
     
     
     
     
     
   }
   
   def addPrefix(prefix : String)
  {
     
  }
   
  
  
  
}