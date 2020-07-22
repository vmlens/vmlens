package com.anarsoft.race.detection.model.description


import java.io.DataInputStream



class DeSerializeDescription {
  
   
   
   
  def deserialize(  visitor : DescVisitor ,  in : DataInputStream)
	{
		val name = in.readUTF();
		val source = in.readUTF();
		
		
		val isThreadSafe  = in.readBoolean();
		val isStateLess = in.readBoolean();
		
		val exceptArrayLength = in.readInt();
	  val exceptArray = Array.ofDim[String](exceptArrayLength)
		
		for( i <- 0 until exceptArrayLength)
		    {
		  
		    exceptArray(i) = in.readUTF();
		  
		    
		    
		    }
		
	
		 val superName = in.readUTF();
		 
		 val interfaceLength = in.readInt();
		 val interfaceArray = Array.ofDim[String](interfaceLength)
		
		 	for( i <- 0 until interfaceLength)
		    {
		  
		    interfaceArray(i) = in.readUTF();
		  
		    
		    
		    }
		 
		 
		
		
		visitor.visitClassDescription(name,source , isThreadSafe , isStateLess , exceptArray , superName , interfaceArray);
		val length = in.readInt();
		
		for(  i <- 0  until length )
		{
			deserializeMethodDescription( visitor , in );
		}
		
    val lengthFieldArray = in.readInt();
		
		for( i <- 0  until lengthFieldArray )
		{
			deserializeFieldDescription( visitor , in );
		}
		
	}
  	
  	
   def deserializeMethodDescription(  visitor : DescVisitor ,  in : DataInputStream) 
	{
		val name = in.readUTF();
		val id = in.readInt();
		val desc = in.readUTF();
		val access = in.readInt();
		val lineNumber = in.readInt();
		

	
		
		
			visitor.visitMethodDescription(name, id,desc,access,lineNumber);
		
		
		
		val length = in.readInt();
		
		for(  i <- 0 until  length)
		{
			deserializeAccessFieldDescription( visitor , in );
		}
		
		
	
		
		visitor.endMethod();
		
		
		
	}
  		
   
   
  def deserializeFieldDescription(  visitor : DescVisitor,  in : DataInputStream) 
	{
		val name = in.readUTF();
		val id = in.readInt();
		val desc = in.readUTF();
		val access = in.readInt();
		
		visitor.visitFieldDescription(name, id,desc,access);
		
		
		
	}
  			
  			
   def deserializeAccessFieldDescription(  visitor : DescVisitor ,  in: DataInputStream) 
	{
		val name = in.readUTF();
		val owner = in.readUTF();
		val id = in.readInt();
		val isStatic = in.readBoolean();
		val isWrite = in.readBoolean();
		val isTraced = in.readBoolean();
		val isFinal = in.readBoolean();
		visitor.visitFieldAccessDescription( name,  owner,  id,
				 isStatic,  isWrite, isTraced, isFinal);
		
		
		
	}

  
  
}