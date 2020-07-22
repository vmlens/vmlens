package com.anarsoft.race.detection.process.read

import java.io._
import scala.collection.mutable._

object ReadArray {
  
  
  def read(stream :  RandomAccessFile,  list : ArrayBuffer[FilePosition]) =
  {
    
    val result = new ArrayBuffer[Array[Byte]]; 
    
            try {
              for (position <- list) {

                  // kann bei fehlern auftreten das das kleiner 0 ist
                 if(  (position.endPosition -  position.startPosition).asInstanceOf[Int] > 0)
                 {
                    val array = Array.ofDim[Byte]( (position.endPosition -  position.startPosition).asInstanceOf[Int]   );
                
                 stream.seek( position.startPosition );
                 
                 stream.read(array);
                 
                 
                 result.append( array );
                 }
                        
                
                      
                    
                    
                   
              }
            } catch {
              case e: java.io.EOFException =>
                {

                }
            }
            
            result;
  }
  
  
}