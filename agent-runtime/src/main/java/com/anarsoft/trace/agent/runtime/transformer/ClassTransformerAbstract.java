package com.anarsoft.trace.agent.runtime.transformer;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.anarsoft.trace.agent.serialization.ClassDescription;
import com.anarsoft.trace.agent.serialization.MethodDescription;
import com.anarsoft.trace.agent.serialization.SerializedFieldDescription;

public abstract class ClassTransformerAbstract  extends ClassVisitor {


	protected TLinkedList<MethodDescriptionBuilder> methodBuilderList = new TLinkedList<MethodDescriptionBuilder>();
	protected  final String className;
	protected  final TransformConstants callBackStrings;
	protected  FilterList filterList;
	protected ClassVisitorCreateDesc  classVisitorCreateDesc;
    private static int maxMethodId = -1;
    
    protected String superClassName;
    private WriteClassDescription writeClassDescription;
    private int classVersion;

    protected boolean isClass = true;
    
	public ClassTransformerAbstract(ClassVisitor cv,String className,FilterList filterList,
			TransformConstants callBackStrings,ClassVisitorCreateDesc		
			classVisitorCreateDesc,WriteClassDescription writeClassDescription) {
		super(AgentClassFileTransformer.ASM_API_VERSION , cv);
		this.filterList = filterList;
		this.className = className;
		this.callBackStrings = callBackStrings;
		this.classVisitorCreateDesc = classVisitorCreateDesc;
		this.writeClassDescription = writeClassDescription;
	}

	
	
	 protected boolean useExpandedFrames()
		{
			int major =  classVersion & 0xFF;
			
			return major < Opcodes.V1_6;
		}
	
	
	 @Override
	public final void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		
		 classVersion= version;
		 
		 superClassName = superName;
		 
		  if( ( access & Opcodes.ACC_INTERFACE ) == Opcodes.ACC_INTERFACE  )
          {
                  isClass = false;
                  
          }
		 
		  cv.visit(version, access, name, signature, superName, interfaces);
		
		  addToHasGeneratedMethodsSetBasedAtStart();
		
	}
	 
	 
	


//	protected String[] appendInterfaceForClass(String[] interfaces) {
//		
//		return interfaces;
//	}


	 
	 
	 public static synchronized void setMaxMethodId(int id)
	 {
		 maxMethodId = id;
	 }


	static synchronized int newMethodId()
	{
		maxMethodId++;
		return maxMethodId;
	}




	protected ClassDescription createClassAnalyzedEvent()
	{

		 MethodDescription[] methodArray 	= new MethodDescription[methodBuilderList.size()];

		  int index = 0;

		 for( MethodDescriptionBuilder methodDescriptionBuilder :  methodBuilderList  )
			{

			 methodArray[index] = methodDescriptionBuilder.build();



			index++;

			}
		 
		 SerializedFieldDescription[] fieldArray 	= new SerializedFieldDescription[classVisitorCreateDesc.fieldDescriptionList.size()];

		   index = 0;

		 for( SerializedFieldDescription serializedFieldDescription :  classVisitorCreateDesc.fieldDescriptionList  )
			{

			 fieldArray[index] = serializedFieldDescription;



			index++;

			}	 
		 


	    // (String name, boolean isThreadSafe ,boolean isStateless , String[] exceptArray 
		ClassDescription classAnalyzedEvent = new ClassDescription(className  , classVisitorCreateDesc.source   ,  classVisitorCreateDesc.isThreadSafe ,
				classVisitorCreateDesc.isStateless , classVisitorCreateDesc.except , methodArray , fieldArray, classVisitorCreateDesc.superName , classVisitorCreateDesc.interfaces );


		return classAnalyzedEvent;
	}

	




	@Override
	public void visitEnd() {
		
		
		if( isClass )
	    {
			addMethodsAndFieldsToClass();
		}

	
		writeClassDescription.write(  this.createClassAnalyzedEvent()  );


		super.visitEnd();
	}

	
	protected void addToHasGeneratedMethodsSetBasedAtStart()
	{
		
	}



	protected void addMethodsAndFieldsToClass() {
		// TODO Auto-generated method stub
		
	}





}
