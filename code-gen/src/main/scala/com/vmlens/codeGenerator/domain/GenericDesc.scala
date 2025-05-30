package com.vmlens.codeGenerator.domain

trait GenericDesc {
  
  val intTyp =  new FieldTyp("int" , 4 , " buffer.putInt( %s ); " , "data.getInt()",  "data.putInt(%s)" , "java.lang.Integer.compare( o1.%s , o2.%s  )" , "stream.writeInt( %s )" );
  val booleanTyp = new FieldTyp("boolean" , 1 , " buffer.put( (byte) ( %s ? 1 : 0 ) );" , "if( data.get( ) == 1.asInstanceOf[Byte] ) { true } else { false } " ,
       "data.put( if( %s) 1.asInstanceOf[Byte] else 0.asInstanceOf[Byte] )  " ,  "java.lang.Boolean.compare( o1.%s , o2.%s  )" , "stream.writeBoolean( %s )" );
   val longTyp =  new FieldTyp("long" , 8 , "  buffer.putLong( %s );  " , "data.getLong()" , "data.putLong(%s)"  ,  "java.lang.Long.compare( o1.%s , o2.%s  )" ,
        "stream.writeLong( %s )" );
  val byteTyp =  new FieldTyp("byte" , 1 , " buffer.put( %s ); " , "data.get()",  "data.put(%s)" , "java.lang.Byte.compare( o1.%s , o2.%s  )" , "stream.write( %s )" );
  val shortTyp =  new FieldTyp("short" , 2 , " buffer.putShort( %s ); " , "data.getShort()",  "data.putShort(%s)" , "java.lang.Short.compare( o1.%s , o2.%s  )" , "stream.writeShort( %s )" );

  var id = 0;
  
   
   def nextId(): Int = {
    id = id + 1;
    id;
   }
  
}