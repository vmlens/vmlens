package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.FieldInClassDescription;
import com.anarsoft.trace.agent.description.MethodDescription;
import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public abstract class ClassTransformerAbstract  extends ClassVisitor {


	protected TLinkedList<MethodDescriptionBuilder> methodBuilderList = new TLinkedList<MethodDescriptionBuilder>();
    protected final String className;
    protected ClassVisitorCreateDesc classVisitorCreateDesc;
    private static int maxMethodId = -1;

    protected String superClassName;
    private WriteClassDescription writeClassDescription;
    private int classVersion;

    protected boolean isClass = true;

    public ClassTransformerAbstract(ClassVisitor cv, String className,
                                    ClassVisitorCreateDesc
                                            classVisitorCreateDesc, WriteClassDescription writeClassDescription) {
        super(AgentClassFileTransformer.ASM_API_VERSION, cv);
        this.className = className;
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

        MethodDescription[] methodArray = new MethodDescription[methodBuilderList.size()];

        int index = 0;

        for (MethodDescriptionBuilder methodDescriptionBuilder : methodBuilderList) {
            methodArray[index] = methodDescriptionBuilder.build();
            index++;

        }

        FieldInClassDescription[] fieldArray = new FieldInClassDescription[classVisitorCreateDesc.fieldDescriptionList.size()];

        index = 0;



        ClassDescription classAnalyzedEvent = new ClassDescription(className, classVisitorCreateDesc.source,
                methodArray, fieldArray, classVisitorCreateDesc.superName, classVisitorCreateDesc.interfaces);

        return classAnalyzedEvent;
    }

	@Override
	public void visitEnd() {
		if (isClass) {
            addMethodsAndFieldsToClass();
        }
		writeClassDescription.write(  this.createClassAnalyzedEvent()  );
		super.visitEnd();
	}


    protected void addToHasGeneratedMethodsSetBasedAtStart() {
    }


	protected void addMethodsAndFieldsToClass() {
	}
}
