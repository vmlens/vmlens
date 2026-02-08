package com.vmlens.codeGenerator.domain

trait GenericDesc {
  
  val intTyp =  new FieldTyp("int" , 4 , " buffer.writeInt( %s ); " , "data.readInt()" );
  val longTyp =  new FieldTyp("long" , 8 , "  buffer.writeLong( %s );  " , "data.readLong()"  );

  var id = 0;
  
   
   def nextId(): Int = {
    id = id + 1;
    id;
   }
  
}