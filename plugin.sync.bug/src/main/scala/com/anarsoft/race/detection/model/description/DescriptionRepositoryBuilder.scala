package com.anarsoft.race.detection.model.description


import java.io._
import com.anarsoft.race.detection.model.graph._
import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;
import com.vmlens.api.MemoryAccessType
import com.typesafe.scalalogging.Logger

abstract class  DescriptionRepositoryBuilder extends DescVisitor  {
  
  
    val logger = Logger(classOf[DescriptionRepositoryBuilder])
  
  
  
    def ownerId2Name : HashMap[Int,String] 
  
    def  methodId2Ordinal :  ModelKey2OrdinalMap[Int];
    
    def methodOrdinal2MethodModel : Array[MethodModel] ;
  
    var currentMethodOrdinal : Option[Int] = None;
  
   def replaceSlahWithDot( in : String) = in.replace('/', '.');
  
  
   def buildFromFile(file : File) =
  {
       

       val in = new DataInputStream(new BufferedInputStream( new FileInputStream(file )) );
    
    val serializeDescription = new DeSerializeDescription();
    try{
      while( true)
      {
        serializeDescription.deserialize(this , in);
      }

    }
    catch
    {
      case eof : java.io.EOFException =>  { }
      
      case data : java.io.UTFDataFormatException =>  { data.printStackTrace()}
    }
    finally
    {
      in.close();
    }
       
       
    
    
		   // descriptionRepository;
  }
  
  
  
  
  
  

  
  
  
  
    def buildFromQueue(eventDir : String) =
  {
       

       val in = new DataInputStream(new BufferedInputStream( new FileInputStream(eventDir + "/description.vmlens" )) );
    
    val serializeDescription = new DeSerializeDescription();
    try{
      while( true)
      {
        serializeDescription.deserialize(this , in);
      }

    }
    catch
    {
      case eof : java.io.EOFException =>  { }
    }
       
       
    
    
		   // descriptionRepository;
  }
    
    //val descriptionRepository = new  DescriptionRepository();
    var currentClassName = "unknown"; 
    var currentClassModel =  ClassModel(  );
   
    
    def  visitClassDescription( name : String,source : String , isThreadSafe : Boolean, isStateLess : Boolean, except : Array[String],superName : String, interfaces: Array[String])
    {
      currentClassName = replaceSlahWithDot(name);
      
      currentMethodOrdinal = None;
      
      
      
      
      currentClassModel = new ClassModel( source , isThreadSafe , isStateLess , except,replaceSlahWithDot(superName) , interfaces.map( x =>   replaceSlahWithDot(x)));
      
      logger.debug("" +  currentClassModel);
      
      
    }
    
    
   def addMethodModel(methodId : Int, model : MethodModelFromTrace)
   {
     
     logger.debug("methodId:" + methodId + ","  +  model);
     
     methodId2Ordinal.getOrdinal(methodId)  match
     {
       
       case None =>
         {
            currentMethodOrdinal = None;
         }
    
       case Some(x) =>
         {
           methodOrdinal2MethodModel(x) = model;
           
            currentMethodOrdinal = Some(x);
         }
     }
     
   }   
         
    
//    def visitAnnotationArray(annotationArray: Array[String])
//    {
//      
//    }
  
  
  def endMethod()
  {
    currentMethodOrdinal = None;
  }
   
   
    
  def  visitMethodDescription( name : String, id : Int , desc : String,access : Int,lineNumber : Int )
  {
   
    
    addMethodModel(id , new MethodModelFromTrace(name,currentClassName,desc,access,lineNumber , currentClassModel) );
    
     //descriptionRepository.methodId2MethodModel.put(id , new MethodModel(name,currentClassName,desc,access));
  }
  
      def visitOwner( id : Int , name : String  )
      {
        ownerId2Name.put(id, name);
      }


}