package com.vmlens.codeGenerator.domain

trait GenericDesc {
  
  val intTyp =  new FieldTyp("int" , 4 , " buffer.writeInt( %s ); " , "data.readInt()",  "data.putInt(%s)" , "java.lang.Integer.compare( o1.%s , o2.%s  )" , "stream.writeInt( %s )" );
  val longTyp =  new FieldTyp("long" , 8 , "  buffer.writeLong( %s );  " , "data.readLong()" , "data.putLong(%s)"  ,  "java.lang.Long.compare( o1.%s , o2.%s  )" ,
        "stream.writeLong( %s )" );
 // val byteTyp =  new FieldTyp("byte" , 1 , " buffer.write( %s ); " , "data.get()",  "data.put(%s)" , "java.lang.Byte.compare( o1.%s , o2.%s  )" , "stream.write( %s )" );
 // val shortTyp =  new FieldTyp("short" , 2 , " buffer.putShort( %s ); " , "data.getShort()",  "data.putShort(%s)" , "java.lang.Short.compare( o1.%s , o2.%s  )" , "stream.writeShort( %s )" );

  var id = 0;
  
   
   def nextId(): Int = {
    id = id + 1;
    id;
   }
  
}