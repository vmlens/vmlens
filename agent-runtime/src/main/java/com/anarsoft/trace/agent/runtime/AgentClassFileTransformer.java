package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.classArrayTransformer.ClassArrayTransformer;
import com.anarsoft.trace.agent.runtime.classArrayTransformer.ClassArrayTransformerFactory;
import com.anarsoft.trace.agent.runtime.classArrayTransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.transformer.*;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_LOCK_STATEMENT;


public class AgentClassFileTransformer implements ClassFileTransformer {

    public static final int ASM_API_VERSION = Opcodes.ASM7;
    private final HasGeneratedMethods hasGeneratedMethods;
    private boolean skipJavaUtil;
    private final TLinkedList<TLinkableWrapper<ClassArrayTransformer>> classArrayTransformerList;
    private WriteClassDescription writeClassDescription;
    private boolean addInterface;

    public AgentClassFileTransformer(boolean skipJavaUtil,
                                     WriteClassDescription writeClassDescription, boolean addInterface,
                                     HasGeneratedMethods hasGeneratedMethods) {
        super();
        this.skipJavaUtil = skipJavaUtil;
        this.writeClassDescription = writeClassDescription;
        this.addInterface = addInterface;
        this.hasGeneratedMethods = hasGeneratedMethods;

        this.classArrayTransformerList = new ClassArrayTransformerFactory().create();
    }

    private static boolean isUtilPackage(String name) {
        if (name.startsWith("java/util")) {
            return true;
        }

        return false;

    }

    public static boolean doNotTraceIn(String name) {

        if (name.startsWith("java/io")) {
            if (name.equals("java/io/ObjectStreamClass")
                    || name.equals("java/io/ObjectInputStream")) {
                return false;
            } else {
                return true;
            }
        }

        return name.startsWith("java/lang/ClassLoader")
                || name.startsWith("java/lang/Class")
                || name.startsWith("java/lang/invoke");

    }


    public static boolean shouldBeTransformed(boolean skipJavaUtil, String name) {
        if (name.startsWith("com/vmlens/api") || name.startsWith("com/vmlens/test")) {
            return true;
        }

        if (name.indexOf('[') > -1) {
            return false;
        }

        if (name.equals("java/lang/Thread") || doNotTraceIn(name)) {
            return true;
        }

        if (

                name.startsWith("com/vmlens/shaded") || name.startsWith("com/vmlens/trace/agent/bootstrap") ||
                        name.startsWith("java/util/IdentityHashMap") || name.startsWith("java/lang/reflect/AccessibleObject")
                        || name.startsWith("java/lang/reflect/Executable") ||
                        name.startsWith("sun/reflect/GeneratedMethodAccessor")
                        || name.startsWith("java/util/concurrent/locks/LockSupport") || // für backpressure
                        name.startsWith("sun/reflect/annotation/AnnotationParser") ||
                        name.startsWith("sun/nio/ch/FileChannelImpl") ||
                        name.startsWith("sun/misc/Cleaner") ||
                        name.startsWith("sun/misc/Unsafe") || // beinhaltet sun.misc.Unsafe
                        name.startsWith("java/lang/ref/") || name.startsWith("java/nio/") || // wird in events verwendet
                        name.startsWith("sun/reflect/UnsafeQualifiedStatic") || // beinhaltet
                        // sun/reflect/UnsafeQualifiedStaticLongFieldAccessorImpl
                        // sun.reflect.UnsafeQualifiedStaticObjectFieldAccessorImpl
                        // name.startsWith("java/net/URLClassLoader") ||

                        // für jdk 11

                        name.startsWith("jdk") || name.startsWith("java/lang/reflect/Proxy") || name.startsWith("java/security")
                        ||
                        // name.startsWith("java/util/logging") ||

                        name.startsWith("java/util/ServiceLoader") ||

                        name.startsWith("java/util/Arrays") || name.startsWith("java/util/HashMap")
                        || name.startsWith("java/util/ImmutableCollections") || name.startsWith("java/util/Optional")
                        || name.startsWith("java/util/Objects") || name.startsWith("java/util/AbstractCollection")
                        || name.startsWith("java/util/Collections") || name.startsWith("java/util/HashSet") ||


                        name.startsWith("sun/launcher") ||


                        name.startsWith("com/anarsoft/trace/agent/runtime") || name.startsWith("gnu/trove")
                        || name.startsWith("org/objectweb/asm")


        ) {


            if (name.equals("java/util/Collections$AsLIFOQueue") || name.equals("java/util/Collections$SetFromMap")
                    || name.equals("java/util/Collections$ReverseComparator2")
                    || name.equals("java/util/Collections$ReverseComparator")
                    || name.equals("java/util/Collections$CopiesList")
                    || name.equals("java/util/Collections$SingletonMap")
                    || name.equals("java/util/Collections$SingletonList")
                    || name.equals("java/util/Collections$SingletonSet")
                    || name.equals("java/util/Collections$EmptyMap") || name.equals("java/util/Collections$EmptyList")
                    || name.equals("java/util/Collections$EmptySet")
                    || name.equals("java/util/Collections$EmptyEnumeration")
                    || name.equals("java/util/Collections$EmptyListIterator")
                    || name.equals("java/util/Collections$EmptyIterator")
                    || name.equals("java/util/Collections$CheckedNavigableMap")
                    || name.equals("java/util/Collections$CheckedSortedMap")
                    || name.equals("java/util/Collections$CheckedMap")
                    || name.equals("java/util/Collections$CheckedRandomAccessList")
                    || name.equals("java/util/Collections$CheckedList")
                    || name.equals("java/util/Collections$CheckedNavigableSet")
                    || name.equals("java/util/Collections$CheckedSortedSet")
                    || name.equals("java/util/Collections$CheckedSet")
                    || name.equals("java/util/Collections$CheckedQueue")
                    || name.equals("java/util/Collections$CheckedCollection")
                    || name.equals("java/util/Collections$SynchronizedNavigableMap")
                    || name.equals("java/util/Collections$SynchronizedSortedMap")
                    || name.equals("java/util/Collections$SynchronizedMap")
                    || name.equals("java/util/Collections$SynchronizedRandomAccessList")
                    || name.equals("java/util/Collections$SynchronizedList")
                    || name.equals("java/util/Collections$SynchronizedNavigableSet")
                    || name.equals("java/util/Collections$SynchronizedSortedSet")
                    || name.equals("java/util/Collections$SynchronizedSet")) {
                return true;
            } else {
                return false;
            }

        }


        if (name.startsWith("sun/instrument") || name.startsWith("sun/tracing") || name.startsWith("sun/tools")
                || name.startsWith("sun/management") || name.startsWith("sun/dc")) {

            return false;

        }


        if (name.startsWith("java/")) {

            if (isTraceableJavaPackage(name)) {
                return true;
            }

            if ((skipJavaUtil && isUtilPackage(name)) || !isUtilPackage(name)) {
                return false;
            }

        }

        return true;

    }

    /**
     * hiervon wird alls getraced auch methodcalls
     *
     * @param className
     * @return
     */

    public static boolean isTraceableJavaPackage(String className) {
        return className.startsWith("java/applet") || className.startsWith("java/awt")
                || className.startsWith("java/rmi") || className.startsWith("java/sql")
                || className.startsWith("java/beans") || className.startsWith("java/math")
                || className.startsWith("java/text") || className.startsWith("java/net")
                || className.startsWith("java/security") || className.startsWith("java/nio")
                || className.startsWith("java/io") || className.startsWith("java/lang/Class")
                || className.startsWith("java/lang/Package") || className.startsWith("java/lang/reflect")
                || className.startsWith("java/lang/invoke") || className.startsWith("sun/nio");
    }

    private static void logTransformedClass(String className, byte[] transformedArray) {
        if (transformedArray == null) {
            return;
        }
        try {
            String logDir = "";
            String fileName = className.substring(className.lastIndexOf("/") + 1);
            fileName = fileName.replace('$', 'I');
            OutputStream outTransformed = new FileOutputStream(logDir + fileName + "_trans.class");
            outTransformed.write(transformedArray);
            outTransformed.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    /*
                ClassReader classReader = new ClassReader(classfileBuffer); // | ClassWriter.COMPUTE_FRAMES
            // ClassWriter.COMPUTE_MAXS
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS); // //

            if (name.equals("java/lang/Thread")) {
                ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name, filterList);
                classReader.accept(classVisitorCreateDesc, 0);

                ClassVisitor classVisitor = new ClassTransformerTraceClassThread(cw, name, filterList, callBackStrings,
                        classVisitorCreateDesc, writeClassDescription);

                classReader.accept(classVisitor, 0);
                return cw.toByteArray();
            }

            if (name.equals("com/vmlens/api/AgentLog")) {
                ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
                classReader.accept(classVisitor, 0);
                return cw.toByteArray();
            }

            if (name.equals("com/vmlens/api/Agent")) {
                ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
                classReader.accept(classVisitor, 0);
                return cw.toByteArray();
            }

            if (name.equals("com/vmlens/api/AllInterleavings")) {
                ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
                classReader.accept(classVisitor, 0);
                return cw.toByteArray();
            }


            if (doNotTraceIn(name)) {

                ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name, filterList);

                classReader.accept(classVisitorCreateDesc, 0);

                ClassVisitor classVisitor = new ClassTransformerTraceClassLoader(cw, name, filterList, callBackStrings,
                        classVisitorCreateDesc, writeClassDescription);
                classReader.accept(classVisitor, 0);

                return cw.toByteArray();
            }

            if (name.startsWith("java/util/concurrent/")) {

                if (!transformConcurrent(classReader, cw, name, filterList)) {
                    return null;
                }

            } else {
                transform(classReader, cw, name, filterList);
            }


            return cw.toByteArray();

     */

    private boolean transformConcurrent(ClassReader classReader, ClassWriter cw, String name) {


        if (name.startsWith("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject")) {
            transform(classReader, cw, name);
            return true;
        }


        if (name.startsWith("java/util/concurrent/locks/AbstractQueuedSynchronizer")) {

            THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

            // String name, String desc
            methodName2Transformer.put(new MethodIdentifier("acquire", "(I)V"),
                    new FactoryMethodTransformerMethodExit(CALLBACK_CLASS_LOCK_STATEMENT, "acquire"));

            methodName2Transformer.put(new MethodIdentifier("acquireInterruptibly", "(I)V"),
                    new FactoryMethodTransformerMethodExit(CALLBACK_CLASS_LOCK_STATEMENT, "acquire"));

            methodName2Transformer.put(new MethodIdentifier("acquireShared", "(I)V"),
                    new FactoryMethodTransformerMethodExit(CALLBACK_CLASS_LOCK_STATEMENT,
                            "acquireShared"));

            methodName2Transformer.put(new MethodIdentifier("tryAcquireSharedNanos", "(IJ)Z"),
                    new FactoryMethodTransformerMethodExitWithReturnBoolean(
                            CALLBACK_CLASS_LOCK_STATEMENT, "tryAcquireSharedBoolean"));

            methodName2Transformer.put(
                    new MethodIdentifier("acquireQueued",
                            "(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer$Node;I)Z"),
                    new FactoryMethodTransformerMethodExit(CALLBACK_CLASS_LOCK_STATEMENT,
                            "acquireQueued"));

            methodName2Transformer.put(new MethodIdentifier("acquireSharedInterruptibly", "(I)V"),
                    new FactoryMethodTransformerMethodExit(CALLBACK_CLASS_LOCK_STATEMENT,
                            "acquireShared"));

            methodName2Transformer.put(new MethodIdentifier("release", "(I)Z"),
                    new FactoryMethodTransformerMethodEnterNoArg(CALLBACK_CLASS_LOCK_STATEMENT,
                            "release"));

            methodName2Transformer.put(
                    new MethodIdentifier("fullyRelease",
                            "(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer$Node;)I"),
                    new FactoryMethodTransformerMethodEnterNoArg(CALLBACK_CLASS_LOCK_STATEMENT,
                            "fullyRelease"));

            methodName2Transformer.put(new MethodIdentifier("releaseShared", "(I)Z"),
                    new FactoryMethodTransformerMethodEnterNoArg(CALLBACK_CLASS_LOCK_STATEMENT,
                            "releaseShared"));


            transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name);
            return true;
        }

        if (name.startsWith("java/util/concurrent/locks/AbstractOwnableSynchronizer")) {
            THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

            // String name, String desc
            methodName2Transformer.put(new MethodIdentifier("setExclusiveOwnerThread", "(Ljava/lang/Thread;)V"),
                    new FactoryMethodTransformerMethodEnterObjectArg(CALLBACK_CLASS_LOCK_STATEMENT,
                            "setExclusiveOwnerThread"));

            transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name);
            return true;
        }

        // java/util/concurrent/locks/ReentrantReadWriteLock$Sync

        if (name.startsWith("java/util/concurrent/locks/ReentrantReadWriteLock$Sync")) {
            THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

            // String name, String desc
            methodName2Transformer.put(new MethodIdentifier("tryAcquireShared", "(I)I"),
                    new FactoryMethodTransformerMethodExitWithReturnInt(CALLBACK_CLASS_LOCK_STATEMENT,
                            "tryAcquireShared"));

            methodName2Transformer.put(new MethodIdentifier("tryReleaseShared", "(I)Z"),
                    new FactoryMethodTransformerMethodExitWithReturnBoolean(
                            CALLBACK_CLASS_LOCK_STATEMENT, "tryReleaseShared"));

            transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name);
            return true;
        }

        if (name.startsWith("java/util/concurrent/locks/ReentrantLock$Sync")) {
            THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

            // String name, String desc
            methodName2Transformer.put(new MethodIdentifier("nonfairTryAcquire", "(I)Z"),
                    new FactoryMethodTransformerMethodExitWithReturnBoolean(
                            CALLBACK_CLASS_LOCK_STATEMENT, "tryAcquire"));

            transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name);
            return true;
        }


        transform(classReader, cw, name);
        return true;


    }

    private void transformWithClassTransformerTraceMethod(
            THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer, ClassReader classReader,
            ClassWriter cw, String name) {

        ClassTransformerTraceMethod classTransformerTraceMethod = new ClassTransformerTraceMethod(cw,
                methodName2Transformer);

        classReader.accept(classTransformerTraceMethod, 0);

    }

    protected void transform(ClassReader classReader, ClassWriter outputClassWriter, String name) {

        ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name);

        classReader.accept(classVisitorCreateDesc, ClassReader.EXPAND_FRAMES);

        ClassVisitor classTransformer = createClassTransformer(outputClassWriter, name,
                classVisitorCreateDesc);
        classReader.accept(classTransformer, 0);

    }

    public ClassVisitor createClassTransformer(ClassWriter outputClassWriter, String name,
                                               ClassVisitorCreateDesc classVisitorCreateDesc) {

        return new ClassTransformer(outputClassWriter, name, classVisitorCreateDesc,
                writeClassDescription, addInterface && classVisitorCreateDesc.callbackMethodNotGenerated,
                hasGeneratedMethods);
    }

    @Override
    public byte[] transform(ClassLoader loader, String name, Class<?> cl, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        try {
            CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

            if (loader != null && loader.equals(this.getClass().getClassLoader())) {
                return null;
            }
            if (name == null) {
                return null;
            }
            if (name.indexOf('[') > -1) {
                return null;
            }
            if (name.startsWith("com/vmlens/shaded")) {
                return null;
            }

            for (TLinkableWrapper<ClassArrayTransformer> transformerWrapper : classArrayTransformerList) {
                ClassArrayTransformer transformer = transformerWrapper.getElement();
                if (transformer.appliesTo(name)) {
                    TransformerContext context = new TransformerContext(classfileBuffer, name,
                            writeClassDescription, hasGeneratedMethods);
                    byte[] result = transformer.transform(context);
                    logTransformedClass(name, result);
                    return result;
                }
            }
            return null;

        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            return null;
        } finally {
            CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;

        }
    }
}
