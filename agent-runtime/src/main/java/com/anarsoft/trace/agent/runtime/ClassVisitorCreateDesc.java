package com.anarsoft.trace.agent.runtime;




import com.anarsoft.trace.agent.runtime.atomic.CreateAtomic;
import com.anarsoft.trace.agent.runtime.atomic.CreateIsAtomic;
import com.anarsoft.trace.agent.runtime.atomic.CreateNotAtomic;
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

import com.anarsoft.trace.agent.serialization.SerializedFieldDescription;


public class ClassVisitorCreateDesc extends ClassVisitor {

	final String className;
	private THashMap<MethodIdentifier,MethodCounts> methodIdentifierSet  = new THashMap<MethodIdentifier,MethodCounts>();

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
	public String superName;
	public String[] interfaces;

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


    public ClassVisitorCreateDesc(String className) {
        super(AgentClassFileTransformer.ASM_API_VERSION);
        this.className = className;
    }

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

        if (name.startsWith("_pAnarsoft_")) {
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
        if ((access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC) {

            if (desc.equals("L" + className + ";")) {
                potentialSingelton = true;
            }
        }


        if ((access & Opcodes.ACC_FINAL) == Opcodes.ACC_FINAL) {
            hasFinalFields = true;
            int fieldId = FieldIdRepository.create(className, name, FieldTyp.FINAL).id;
            fieldDescriptionList.add(new SerializedFieldDescription(fieldId, access, name, desc,
                    signature));
            return null;
        }

        if ((access & Opcodes.ACC_VOLATILE) == Opcodes.ACC_VOLATILE) {
            int fieldId = FieldIdRepository.create(className, name, FieldTyp.VOLATILE).id;
            fieldDescriptionList.add(new SerializedFieldDescription(fieldId, access, name, desc,
                    signature));
            return null;
        }
        return new FieldVisitorCreateDesc(fieldDescriptionList, className, name, access, desc, signature);
    }

	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean isAtRuntime) {
        if ("Lcom/vmlens/annotation/Atomic;".equals(name)) {
            createAtomic = new CreateIsAtomic();
        }
		return null;
	}

	@Override
	public void visitSource(String source, String debug) {
		this.source = source;
	}

}
