package com.anarsoft.trace.agent.runtime.classArrayTransformer;

import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class TransformerStrategyNormal implements TransformerStrategy {
    @Override
    public byte[] transform(TransformerContext context) {
        boolean addInterface = true;


        ClassWriter cw = context.createClassWriter();
        ClassReader classReader = context.createClassReader();

        ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(context.name(), context.filterList());

        classReader.accept(classVisitorCreateDesc, ClassReader.EXPAND_FRAMES);

        ClassVisitor classTransformer = new ClassTransformer(cw, context.name(), context.filterList(),
                context.callBackStrings(), classVisitorCreateDesc, context.writeClassDescription(),
                addInterface && classVisitorCreateDesc.callbackMethodNotGenerated, context.hasGeneratedMethods());
        classReader.accept(classTransformer, 0);

        return cw.toByteArray();
    }
}
