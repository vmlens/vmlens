package com.anarsoft.trace.agent.runtime;


import com.anarsoft.trace.agent.runtime.repositorydeprecated.FieldRepositoryFacade;
import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldIdRepository;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;



public class ClassVisitorCreateDescForAtomic  extends ClassVisitor  {


	private final String className;
	private int valueFieldId = -1;
	public final FieldRepositoryFacade schedulingInfoClassMap;



	public ClassVisitorCreateDescForAtomic(String className,FieldRepositoryFacade schedulingInfoClassMap) {
		super(AgentClassFileTransformer.ASM_API_VERSION  );
		this.className = className;
		this.schedulingInfoClassMap = schedulingInfoClassMap;
	}




	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {


		if( ( access & Opcodes.ACC_VOLATILE  ) == Opcodes.ACC_VOLATILE  )
		{
				
			
           FieldIdAndTyp idAndTyp = FieldIdRepository.create(className, name, FieldTyp.VOLATILE);

		
			

			if( name.equals("value")  )
			{
				valueFieldId = idAndTyp.id;
			}
		}
		







		return super.visitField(access, name, desc, signature, value);



	}




	public int getValueFieldId() {

		if(  valueFieldId == -1)
		{
			throw new RuntimeException("field was not set for " + className);
		}

		return valueFieldId;
	}






}
