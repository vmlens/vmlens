package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.filter.FilterStateDefaultValue;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsSetBased;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.vmlens.trace.agent.bootstrap.mode.AgentModeInterleave;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.instrument.IllegalClassFormatException;

public class ClassLoaderForTransformation extends ClassLoader {

    protected ClassLoaderForTransformation(ClassLoader parent) {
        super(parent);
    }

    AgentClassFileTransformer createTransformer() {
        TransformConstants callBackStrings = new TransformConstants(
                "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback",
                "com/vmlens/trace/agent/bootstrap/callback/SynchronizedStatementCallback",
                "com/vmlens/trace/agent/bootstrap/callback/LockStatementCallback",
                "com/vmlens/trace/agent/bootstrap/ThreadIdForField",
                "com/vmlens/trace/agent/bootstrap/callback/MethodCallback", "_pAnarsoft_", "_pAnarsoft_set_",
                "_pAnarsoft_get_");

        FilterList filterList = new FilterList(new FilterStateDefaultValue(true),
                new FilterStateDefaultValue(false), new FilterStateDefaultValue(false),
                new FilterStateDefaultValue(false), new AgentModeInterleave());

        return new AgentClassFileTransformer(filterList, callBackStrings, false,
                new WriteClassDescriptionNormal(), true, new HasGeneratedMethodsSetBased());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            if (name.startsWith("java")) {
                System.out.println(name);
            }

            String resourceName = name.replace('.', '/') + ".class";
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
            System.out.println(stream);

            byte[] targetArray = IOUtils.toByteArray(stream);
            AgentClassFileTransformer transformer = createTransformer();

            byte[] transformed = transformer.transform(null, name.replace('.', '/'), null, null, targetArray);
            if (transformed == null) {
                return defineClass(name, targetArray, 0, targetArray.length);
            }
            String fileName = name.substring(name.lastIndexOf("/") + 1);
            OutputStream outTransformed = new FileOutputStream(fileName + "_trans.class");
            outTransformed.write(transformed);
            outTransformed.close();

            return defineClass(name, transformed, 0, transformed.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalClassFormatException e) {
            throw new RuntimeException(e);
        }


    }
}
