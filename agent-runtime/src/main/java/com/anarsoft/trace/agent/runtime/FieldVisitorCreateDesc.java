package com.anarsoft.trace.agent.runtime;

import com.vmlens.trace.agent.bootstrap.FieldIdRepository;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;


import com.anarsoft.trace.agent.serialization.SerializedFieldDescription;

public class FieldVisitorCreateDesc extends FieldVisitor {

	private final  TLinkedList<SerializedFieldDescription> fieldDescriptionList;
	private final String className;
	private final String fieldName;
	private final int access;
	private final String desc;
	private final String signature;
	
	
	private boolean hasSuppressAnnotation;
	
	
	public FieldVisitorCreateDesc( TLinkedList<SerializedFieldDescription> fieldDescriptionList, String className, String fieldName,int access,String desc, String signature ) {
		super(AgentClassFileTransformer.ASM_API_VERSION );
		this.fieldDescriptionList = fieldDescriptionList;
		this.className = className;
		this.fieldName = fieldName;
		
		this.access = access;
		this.desc = desc;
		this.signature = signature;
		
	}

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		if("Lcom/vmlens/annotation/SuppressWarningForRace;".equals(arg0))
		{
			hasSuppressAnnotation= true;
		}
		
		return new FieldAnnotationVisitor();
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		
		
	}

	@Override
	public void visitEnd() {
		
		if(hasSuppressAnnotation)
		{
			  int fieldId = FieldIdRepository.create(className, fieldName,FieldTyp.FINAL).id;
				
				fieldDescriptionList.add(new SerializedFieldDescription( fieldId ,  access,  fieldName,  desc,
						 signature));
			
			
			
		}
		else
		{
			  int fieldId = FieldIdRepository.create(className, fieldName,FieldTyp.NON_VOLATILE).id;
				
				fieldDescriptionList.add(new SerializedFieldDescription( fieldId ,  access,  fieldName,  desc,
						 signature));
		}
		
		
		
	}

}
