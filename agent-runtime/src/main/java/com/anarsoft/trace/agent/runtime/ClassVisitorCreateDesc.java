package com.anarsoft.trace.agent.runtime;




import com.vmlens.trace.agent.bootstrap.ClassInheritanceRepository;
import com.vmlens.trace.agent.bootstrap.FieldIdRepository;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.anarsoft.trace.agent.serialization.SerializedFieldDescription;


public class ClassVisitorCreateDesc extends ClassVisitor {


	//private TIntLinkedList fieldIds = new TIntLinkedList();
	final String className;
	private THashMap<MethodIdentifier,MethodCounts> methodIdentifierSet  = new THashMap<MethodIdentifier,MethodCounts>();
	final FilterList filterList;

	public boolean hasUnsafeAccess = false; 
	public boolean hasFinalFields = false;
	
	public String source = "";
	
	
	public TLinkedList<SerializedFieldDescription> fieldDescriptionList = new TLinkedList<SerializedFieldDescription>();
	public boolean callbackMethodNotGenerated = true;

	public CreateAtomic createAtomic = new CreateNotAtomic();

	
	public boolean hasClinit = false;
	public boolean containsNative = false;
	public boolean potentialSingelton = false;
	public boolean isAbstract = false;
	
	public boolean isStateless = false;
	public String[] except = new String[0];
	
	public boolean isThreadSafe = false;
	
	
	public String superName;
	public String[] interfaces;
	
	
//	public TIntLinkedList getFieldIds() {
//		return fieldIds;
//	}

	
	public MethodCounts getMethodCounts(MethodIdentifier mi)
	{
		return methodIdentifierSet.get(mi);
	}
	
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		
		this.superName = superName;
		this.interfaces = interfaces;
		
		if(this.interfaces == null)
		{
			this.interfaces = new String[0];
		}
		
		
	   if(   (access & Opcodes.ACC_ABSTRACT ) ==  Opcodes.ACC_ABSTRACT)
	   {
		   isAbstract = true;
	   }
		
		
		ClassInheritanceRepository.addRelation(className,superName);
		
	}


	public ClassVisitorCreateDesc( String className , FilterList filterList
			  ) {
		super(AgentClassFileTransformer.ASM_API_VERSION  );
		this.className = className;
		this.filterList = filterList;
	
	}


	
	public boolean traceMethodCalls(MethodIdentifier mi)
	{
		
	  return traceMethodCalls(mi, className);

	}
	
	public  boolean traceMethodCalls(MethodIdentifier mi,String className)
	{
	boolean trace = true;
		
//		if( className.startsWith("java/") )
//		{
//			if(    ! className.startsWith("java/util/logging") 
//				&& ! className.startsWith("java/util/concurrent")  
//				&& ! className.startsWith("java/util/function") 
//				&& ! className.startsWith("java/util/jar") 
//				&& ! className.startsWith("java/util/prefs") 
//				&& ! className.startsWith("java/util/regex") 
//				&& ! className.startsWith("java/util/spi") 
//				&& ! className.startsWith("java/util/stream") 
//				&& ! className.startsWith("java/util/zip") 
//				&& ! AgentClassFileTransformer.isTraceableJavaPackage(className) 
//			)
//			{
//				trace = false;
//			}
//		}
//		
//		
//		
 		return trace   && ( methodIdentifierSet.get(mi).methodCallCount > 0 ) && filterList.traceMethod(className , mi.getName());
//	
		
	//	return trace;
	}

	



	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

        if( name.startsWith("_pAnarsoft_") )
        {
        	callbackMethodNotGenerated = false;
        }
	
     if(name.startsWith("<cl"))
     {
    	 hasClinit = true;
     }
        
       
     
     if( ( access & Opcodes.ACC_NATIVE ) == Opcodes.ACC_NATIVE )
     {
    	 containsNative = true;
     }
        
		return new MethodVisitorCreateDesc(name , desc ,methodIdentifierSet , this);
	}





	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {

		
		if( ( access & Opcodes.ACC_STATIC  ) == Opcodes.ACC_STATIC  )
		{
			/*
			 * 
{
fv = cw.visitField(ACC_FINAL, "className", "Ljava/lang/String;", null, null);
fv.visitEnd();
}
			 */
			
			if(desc.equals("L" +className + ";" ))
			{
				potentialSingelton = true;
			}
			
		}
     
	

		if( ( access & Opcodes.ACC_FINAL  ) == Opcodes.ACC_FINAL  )
		{
			hasFinalFields = true;
			
			
			   int fieldId = FieldIdRepository.create(className, name,FieldTyp.FINAL).id;
				
				fieldDescriptionList.add(new SerializedFieldDescription( fieldId ,  access,  name,  desc,
						 signature));
			
			
			return null;

		}
		
		if( ( access & Opcodes.ACC_VOLATILE  ) == Opcodes.ACC_VOLATILE  )
		{
			
			  int fieldId = FieldIdRepository.create(className, name,FieldTyp.VOLATILE).id;
				
				fieldDescriptionList.add(new SerializedFieldDescription( fieldId ,  access,  name,  desc,
						 signature));
			
			
			return null; 
		
		}
		
		
//		if(className.equals("java/util/ArrayList") && name.equals("elementData"))
//		{
//			System.out.println("elementData as volatile");
//			
//			 int fieldId = FieldIdRepository.create(className, name,FieldTyp.VOLATILE).id;
//				
//				fieldDescriptionList.add(new SerializedFieldDescription( fieldId ,  access,  name,  desc,
//						 signature));
//			
//			
//			return null; 
//		}
		
		
		
		
			
			return new FieldVisitorCreateDesc(fieldDescriptionList,className,name, access, desc,  signature );
			
		


		
		




	}




	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean isAtRuntime) {
		

		
		if( filterList.agentMode.processInterleaveAnnotation() )
		{
			if("Lcom/vmlens/annotation/Atomic;".equals(name))
			{
				createAtomic = new CreateIsAtomic();
			}
		}
		
		
		if( filterList.agentMode.processSharedAnnotation() )
		{
			if("Lcom/vmlens/annotation/ThreadSafe;".equals(name))
			{
				this.isThreadSafe = true;
				return null;
				
			}
			
			if("Lcom/vmlens/annotation/Stateless;".equals(name))
			{
				this.isStateless = true;
				
				return new AnnotationVisitorOwner(this);
			}
		}
		
		
		
		
		
		return null;
	}


	@Override
	public void visitSource(String source, String debug) {
		this.source = source;
	}


	





}
