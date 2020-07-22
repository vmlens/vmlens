package com.anarsoft.race.detection.model.result

 class SearchData( val packageName : String , val className : String ,val fullQualifiedClassName : String,val searchDataInClass : SearchDataInClass) {
  
}

object SearchData
{
  def apply( qualifiedClassName : String , searchDataInClass : SearchDataInClass ) =
  {
    new SearchData( getPackageName(qualifiedClassName) , getSimpleClassNameFromString(qualifiedClassName) , qualifiedClassName , searchDataInClass);
  }
  
  
  private def capAtSymbol(in : String , symbol : Char) =
   {
     val index =   in.lastIndexOf(symbol);
    
    if( index == -1 )
    {
     in;
    }
    else
    {
       in.substring(index + 1, in.length() );
    }
   }
   
 
  
 private  def getSimpleClassNameFromString( in : String ) =
  {
      val className = capAtSymbol(in, '.');
      
      val index =   className.lastIndexOf('$');
    
     
    
    if( index == -1 )
    {
     className;
    }
    else
    {
       className.substring( 0 , index  );
    }
      
      
      
      
  }
    
    
  def getRootPackageName( qualifiedClassName : String) =
  {
    val index =  qualifiedClassName.indexOf('.');
    
    if( index == -1 )
    {
      "";
    }
    else
    {
       qualifiedClassName.substring(0, index);
    }
    
  } 
 
 
 
  
  private def getPackageName( qualifiedClassName : String) =
  {
    val index =  qualifiedClassName.lastIndexOf('.');
    
    if( index == -1 )
    {
      "";
    }
    else
    {
       qualifiedClassName.substring(0, index);
    }
    
  } 
  
  
  
}

