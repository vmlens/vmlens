package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplate;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Iterator;

/*
 * Für Constructoren wird aktuell nor monitor enter und exit getraced
 * 
 * 
 * wir können hier keine try catch blöcke verwenden da this noch nicht
 * initialisiert ist
 * 
 * 
 * !ENTRY org.eclipse.equinox.common 4 0 2017-10-21 11:27:34.156 !MESSAGE
 * FrameworkEvent ERROR !STACK 0 org.osgi.framework.BundleException: Error
 * starting module. at
 * org.eclipse.osgi.container.Module.doStart(Module.java:590) at
 * org.eclipse.osgi.container.Module.start(Module.java:449) at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1620)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1600)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.doContainerStartLevel(ModuleContainer.java:1571)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1514)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.run(EventManager.java:340)
 * Caused by: java.lang.VerifyError: Stack map does not match the one at
 * exception handler 71 Exception Details: Location:
 * org/eclipse/core/internal/runtime/Activator.<init>()V @71: ldc Reason:
 * Current frame's flags are not assignable to stack map frame's. Current Frame:
 * bci: @16 flags: { flagThisUninit } locals: { uninitializedThis } stack: {
 * 'java/lang/Throwable' } Stackmap Frame: bci: @71 flags: { } locals: { }
 * stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 0045 59b7 0046
 * b500 4a12 4bb8 0034 0x0000010: 2ab7 004c 2a01 b500 4e2a 01b5 0050 2a01
 * 0x0000020: b500 522a 01b5 0054 2a01 b500 562a 01b5 0x0000030: 0058 2a01 b500
 * 5a2a 01b5 005c 2a01 b500 0x0000040: 5e12 4bb8 0041 b112 4bb8 0041 bf
 * Exception Handler Table: bci [16, 71] => handler: 71 Stackmap Table:
 * full_frame(@71,{},{Object[#67]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.loadBundleActivator(BundleContextImpl.java:758)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.start(BundleContextImpl.java:711)
 * at
 * org.eclipse.osgi.internal.framework.EquinoxBundle.startWorker0(EquinoxBundle.java:932)
 * at
 * org.eclipse.osgi.internal.framework.EquinoxBundle$EquinoxModule.startWorker(EquinoxBundle.java:309)
 * at org.eclipse.osgi.container.Module.doStart(Module.java:581) ... 8 more Root
 * exception: java.lang.VerifyError: Stack map does not match the one at
 * exception handler 71 Exception Details: Location:
 * org/eclipse/core/internal/runtime/Activator.<init>()V @71: ldc Reason:
 * Current frame's flags are not assignable to stack map frame's. Current Frame:
 * bci: @16 flags: { flagThisUninit } locals: { uninitializedThis } stack: {
 * 'java/lang/Throwable' } Stackmap Frame: bci: @71 flags: { } locals: { }
 * stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 0045 59b7 0046
 * b500 4a12 4bb8 0034 0x0000010: 2ab7 004c 2a01 b500 4e2a 01b5 0050 2a01
 * 0x0000020: b500 522a 01b5 0054 2a01 b500 562a 01b5 0x0000030: 0058 2a01 b500
 * 5a2a 01b5 005c 2a01 b500 0x0000040: 5e12 4bb8 0041 b112 4bb8 0041 bf
 * Exception Handler Table: bci [16, 71] => handler: 71 Stackmap Table:
 * full_frame(@71,{},{Object[#67]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.loadBundleActivator(BundleContextImpl.java:758)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.start(BundleContextImpl.java:711)
 * at
 * org.eclipse.osgi.internal.framework.EquinoxBundle.startWorker0(EquinoxBundle.java:932)
 * at
 * org.eclipse.osgi.internal.framework.EquinoxBundle$EquinoxModule.startWorker(EquinoxBundle.java:309)
 * at org.eclipse.osgi.container.Module.doStart(Module.java:581) at
 * org.eclipse.osgi.container.Module.start(Module.java:449) at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1620)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.incStartLevel(ModuleContainer.java:1600)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.doContainerStartLevel(ModuleContainer.java:1571)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1514)
 * at
 * org.eclipse.osgi.container.ModuleContainer$ContainerStartLevel.dispatchEvent(ModuleContainer.java:1)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.run(EventManager.java:340)
 * 
 * !ENTRY org.eclipse.equinox.ds 4 0 2017-10-21 11:27:34.505 !MESSAGE Exception
 * occurred while creating new instance of component Component[ name =
 * org.eclipse.e4.core.services.events activate = activate deactivate =
 * deactivate modified = configuration-policy = optional facade = null
 * autoenable = true immediate = true implementation =
 * org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier state =
 * Unsatisfied properties =
 * {dependency.injection.annotation=org.eclipse.e4.core.di.extensions.EventTopic}
 * serviceFactory = false serviceInterface =
 * [org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier] references = {
 * Reference[name = EventAdmin, interface = org.osgi.service.event.EventAdmin,
 * policy = static, cardinality = 1..1, target = null, bind = setEventAdmin,
 * unbind = null] } located in bundle =
 * org.eclipse.e4.core.di.extensions_0.14.0.v20160211-1614 [560] ] !STACK 0
 * java.lang.VerifyError: Stack map does not match the one at exception handler
 * 26 Exception Details: Location:
 * org/eclipse/e4/core/di/suppliers/ExtendedObjectSupplier.<init>()V @26: ldc
 * Reason: Current frame's flags are not assignable to stack map frame's.
 * Current Frame: bci: @16 flags: { flagThisUninit } locals: { uninitializedThis
 * } stack: { 'java/lang/Throwable' } Stackmap Frame: bci: @26 flags: { }
 * locals: { } stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 002b
 * 59b7 002d b500 3112 32b8 0018 0x0000010: 2ab7 0033 1232 b800 26b1 1232 b800
 * 26bf 0x0000020: Exception Handler Table: bci [16, 26] => handler: 26 Stackmap
 * Table: full_frame(@26,{},{Object[#40]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.equinox.internal.ds.model.ServiceComponent.createInstance(ServiceComponent.java:493)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.createInstance(ServiceComponentProp.java:270)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:331)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.buildNewlySatisfied(Resolver.java:473)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.enableComponents(Resolver.java:217)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager.performWork(SCRManager.java:816)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager$QueuedJob.dispatch(SCRManager.java:783)
 * at org.eclipse.equinox.internal.ds.WorkThread.run(WorkThread.java:89) at
 * java.lang.Thread.run(Thread.java:748)
 * 
 * !ENTRY org.eclipse.equinox.ds 4 0 2017-10-21 11:27:34.506 !MESSAGE Exception
 * occurred while creating new instance of component Component[ name =
 * org.eclipse.e4.core.services.events activate = activate deactivate =
 * deactivate modified = configuration-policy = optional facade = null
 * autoenable = true immediate = true implementation =
 * org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier state =
 * Unsatisfied properties =
 * {dependency.injection.annotation=org.eclipse.e4.core.di.extensions.EventTopic}
 * serviceFactory = false serviceInterface =
 * [org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier] references = {
 * Reference[name = EventAdmin, interface = org.osgi.service.event.EventAdmin,
 * policy = static, cardinality = 1..1, target = null, bind = setEventAdmin,
 * unbind = null] } located in bundle =
 * org.eclipse.e4.core.di.extensions_0.14.0.v20160211-1614 [560] ] !STACK 0
 * java.lang.VerifyError: Stack map does not match the one at exception handler
 * 26 Exception Details: Location:
 * org/eclipse/e4/core/di/suppliers/ExtendedObjectSupplier.<init>()V @26: ldc
 * Reason: Current frame's flags are not assignable to stack map frame's.
 * Current Frame: bci: @16 flags: { flagThisUninit } locals: { uninitializedThis
 * } stack: { 'java/lang/Throwable' } Stackmap Frame: bci: @26 flags: { }
 * locals: { } stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 002b
 * 59b7 002d b500 3112 32b8 0018 0x0000010: 2ab7 0033 1232 b800 26b1 1232 b800
 * 26bf 0x0000020: Exception Handler Table: bci [16, 26] => handler: 26 Stackmap
 * Table: full_frame(@26,{},{Object[#40]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.equinox.internal.ds.model.ServiceComponent.createInstance(ServiceComponent.java:493)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.createInstance(ServiceComponentProp.java:270)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:331)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.buildNewlySatisfied(Resolver.java:473)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.enableComponents(Resolver.java:217)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager.performWork(SCRManager.java:816)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager$QueuedJob.dispatch(SCRManager.java:783)
 * at org.eclipse.equinox.internal.ds.WorkThread.run(WorkThread.java:89) at
 * java.lang.Thread.run(Thread.java:748)
 * 
 * !ENTRY org.eclipse.equinox.ds 4 0 2017-10-21 11:27:34.508 !MESSAGE Exception
 * occurred while creating new instance of component Component[ name =
 * org.eclipse.e4.core.services.events activate = activate deactivate =
 * deactivate modified = configuration-policy = optional facade = null
 * autoenable = true immediate = true implementation =
 * org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier state =
 * Unsatisfied properties =
 * {dependency.injection.annotation=org.eclipse.e4.core.di.extensions.EventTopic}
 * serviceFactory = false serviceInterface =
 * [org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier] references = {
 * Reference[name = EventAdmin, interface = org.osgi.service.event.EventAdmin,
 * policy = static, cardinality = 1..1, target = null, bind = setEventAdmin,
 * unbind = null] } located in bundle =
 * org.eclipse.e4.core.di.extensions_0.14.0.v20160211-1614 [560] ] !STACK 0
 * java.lang.VerifyError: Stack map does not match the one at exception handler
 * 26 Exception Details: Location:
 * org/eclipse/e4/core/di/suppliers/ExtendedObjectSupplier.<init>()V @26: ldc
 * Reason: Current frame's flags are not assignable to stack map frame's.
 * Current Frame: bci: @16 flags: { flagThisUninit } locals: { uninitializedThis
 * } stack: { 'java/lang/Throwable' } Stackmap Frame: bci: @26 flags: { }
 * locals: { } stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 002b
 * 59b7 002d b500 3112 32b8 0018 0x0000010: 2ab7 0033 1232 b800 26b1 1232 b800
 * 26bf 0x0000020: Exception Handler Table: bci [16, 26] => handler: 26 Stackmap
 * Table: full_frame(@26,{},{Object[#40]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.equinox.internal.ds.model.ServiceComponent.createInstance(ServiceComponent.java:493)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.createInstance(ServiceComponentProp.java:270)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:331)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
 * at org.eclipse.equinox.internal.ds.Resolver.getEligible(Resolver.java:343) at
 * org.eclipse.equinox.internal.ds.SCRManager.serviceChanged(SCRManager.java:222)
 * at
 * org.eclipse.osgi.internal.serviceregistry.FilteredServiceListener.serviceChanged(FilteredServiceListener.java:109)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.dispatchEvent(BundleContextImpl.java:915)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
 * at
 * org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:148)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEventPrivileged(ServiceRegistry.java:862)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEvent(ServiceRegistry.java:801)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistrationImpl.register(ServiceRegistrationImpl.java:127)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.registerService(ServiceRegistry.java:225)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:464)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.registerService(InstanceProcess.java:536)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:260)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.buildNewlySatisfied(Resolver.java:473)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.enableComponents(Resolver.java:217)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager.performWork(SCRManager.java:816)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager$QueuedJob.dispatch(SCRManager.java:783)
 * at org.eclipse.equinox.internal.ds.WorkThread.run(WorkThread.java:89) at
 * java.lang.Thread.run(Thread.java:748)
 * 
 * !ENTRY org.eclipse.equinox.ds 4 0 2017-10-21 11:27:34.509 !MESSAGE Exception
 * occurred while creating new instance of component Component[ name =
 * org.eclipse.e4.core.services.events activate = activate deactivate =
 * deactivate modified = configuration-policy = optional facade = null
 * autoenable = true immediate = true implementation =
 * org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier state =
 * Unsatisfied properties =
 * {dependency.injection.annotation=org.eclipse.e4.core.di.extensions.EventTopic}
 * serviceFactory = false serviceInterface =
 * [org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier] references = {
 * Reference[name = EventAdmin, interface = org.osgi.service.event.EventAdmin,
 * policy = static, cardinality = 1..1, target = null, bind = setEventAdmin,
 * unbind = null] } located in bundle =
 * org.eclipse.e4.core.di.extensions_0.14.0.v20160211-1614 [560] ] !STACK 0
 * java.lang.VerifyError: Stack map does not match the one at exception handler
 * 26 Exception Details: Location:
 * org/eclipse/e4/core/di/suppliers/ExtendedObjectSupplier.<init>()V @26: ldc
 * Reason: Current frame's flags are not assignable to stack map frame's.
 * Current Frame: bci: @16 flags: { flagThisUninit } locals: { uninitializedThis
 * } stack: { 'java/lang/Throwable' } Stackmap Frame: bci: @26 flags: { }
 * locals: { } stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 002b
 * 59b7 002d b500 3112 32b8 0018 0x0000010: 2ab7 0033 1232 b800 26b1 1232 b800
 * 26bf 0x0000020: Exception Handler Table: bci [16, 26] => handler: 26 Stackmap
 * Table: full_frame(@26,{},{Object[#40]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.equinox.internal.ds.model.ServiceComponent.createInstance(ServiceComponent.java:493)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.createInstance(ServiceComponentProp.java:270)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:331)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
 * at org.eclipse.equinox.internal.ds.Resolver.getEligible(Resolver.java:343) at
 * org.eclipse.equinox.internal.ds.SCRManager.serviceChanged(SCRManager.java:222)
 * at
 * org.eclipse.osgi.internal.serviceregistry.FilteredServiceListener.serviceChanged(FilteredServiceListener.java:109)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.dispatchEvent(BundleContextImpl.java:915)
 * at
 * org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
 * at
 * org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:148)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEventPrivileged(ServiceRegistry.java:862)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.publishServiceEvent(ServiceRegistry.java:801)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistrationImpl.register(ServiceRegistrationImpl.java:127)
 * at
 * org.eclipse.osgi.internal.serviceregistry.ServiceRegistry.registerService(ServiceRegistry.java:225)
 * at
 * org.eclipse.osgi.internal.framework.BundleContextImpl.registerService(BundleContextImpl.java:464)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.registerService(InstanceProcess.java:536)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:260)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.buildNewlySatisfied(Resolver.java:473)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.enableComponents(Resolver.java:217)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager.performWork(SCRManager.java:816)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager$QueuedJob.dispatch(SCRManager.java:783)
 * at org.eclipse.equinox.internal.ds.WorkThread.run(WorkThread.java:89) at
 * java.lang.Thread.run(Thread.java:748)
 * 
 * !ENTRY org.eclipse.equinox.ds 4 0 2017-10-21 11:27:34.513 !MESSAGE Exception
 * occurred while creating new instance of component Component[ name =
 * org.eclipse.e4.core.services.events activate = activate deactivate =
 * deactivate modified = configuration-policy = optional facade = null
 * autoenable = true immediate = true implementation =
 * org.eclipse.e4.core.di.internal.extensions.EventObjectSupplier state =
 * Unsatisfied properties =
 * {dependency.injection.annotation=org.eclipse.e4.core.di.extensions.EventTopic}
 * serviceFactory = false serviceInterface =
 * [org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier] references = {
 * Reference[name = EventAdmin, interface = org.osgi.service.event.EventAdmin,
 * policy = static, cardinality = 1..1, target = null, bind = setEventAdmin,
 * unbind = null] } located in bundle =
 * org.eclipse.e4.core.di.extensions_0.14.0.v20160211-1614 [560] ] !STACK 0
 * java.lang.VerifyError: Stack map does not match the one at exception handler
 * 26 Exception Details: Location:
 * org/eclipse/e4/core/di/suppliers/ExtendedObjectSupplier.<init>()V @26: ldc
 * Reason: Current frame's flags are not assignable to stack map frame's.
 * Current Frame: bci: @16 flags: { flagThisUninit } locals: { uninitializedThis
 * } stack: { 'java/lang/Throwable' } Stackmap Frame: bci: @26 flags: { }
 * locals: { } stack: { 'java/lang/Throwable' } Bytecode: 0x0000000: 2abb 002b
 * 59b7 002d b500 3112 32b8 0018 0x0000010: 2ab7 0033 1232 b800 26b1 1232 b800
 * 26bf 0x0000020: Exception Handler Table: bci [16, 26] => handler: 26 Stackmap
 * Table: full_frame(@26,{},{Object[#40]})
 * 
 * at java.lang.Class.getDeclaredConstructors0(Native Method) at
 * java.lang.Class.privateGetDeclaredConstructors(Class.java:2671) at
 * java.lang.Class.getConstructor0(Class.java:3075) at
 * java.lang.Class.newInstance(Class.java:412) at
 * org.eclipse.equinox.internal.ds.model.ServiceComponent.createInstance(ServiceComponent.java:493)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.createInstance(ServiceComponentProp.java:270)
 * at
 * org.eclipse.equinox.internal.ds.model.ServiceComponentProp.build(ServiceComponentProp.java:331)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponent(InstanceProcess.java:620)
 * at
 * org.eclipse.equinox.internal.ds.InstanceProcess.buildComponents(InstanceProcess.java:197)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.buildNewlySatisfied(Resolver.java:473)
 * at
 * org.eclipse.equinox.internal.ds.Resolver.enableComponents(Resolver.java:217)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager.performWork(SCRManager.java:816)
 * at
 * org.eclipse.equinox.internal.ds.SCRManager$QueuedJob.dispatch(SCRManager.java:783)
 * at org.eclipse.equinox.internal.ds.WorkThread.run(WorkThread.java:89) at
 * java.lang.Thread.run(Thread.java:748)
 * 
 * 
 * tritt auf bei -vm /usr/lib/jvm/java-8-openjdk-amd64/jre/bin
 * 
 * bei eclipse neon
 * 
 * 
 * 
 * 
 * @author thomas
 *
 */

public class MethodTransformerForConstructor extends MethodTransformerTraceLineNumber implements Opcodes {

	private final HasGeneratedMethods hasGeneratedMethods;

	protected final String CALLBACK_CLASS_SYNCHRONIZED_STATEMENT;
	// private final MethodDescriptionBuilder methodDescriptionBuilder;
	private final boolean traceMethodCalls;

	private int monitorPosition = 1;
	private int monitorExitPosition =0;
	protected String className;

	public MethodTransformerForConstructor(MethodVisitor mv, int access, String desc, String name, String className,
			String superClassName, int tryCatchBlockCount, MethodDescriptionBuilder methodDescriptionBuilder,
			HasGeneratedMethods hasGeneratedMethods, TransformConstants callBackStrings, boolean traceMethodCalls) {
		super(mv, methodDescriptionBuilder);
		this.hasGeneratedMethods = hasGeneratedMethods;
		CALLBACK_CLASS_SYNCHRONIZED_STATEMENT = callBackStrings.callback_class_synchronized_statement;
		this.traceMethodCalls = traceMethodCalls;
		this.className = className;
	}

	@Override
	public final void visitCode() {
		mv.visitCode();
		onMethodEnter();

	}
	  

	  
	public final void onMethodEnter() {

//		if ((this.hasGeneratedMethods.hasGeneratedMethods(this.className))) {
//			this.mv.visitVarInsn(ALOAD, 0);
//			this.mv.visitTypeInsn(NEW, "com/vmlens/trace/agent/bootstrap/callback/state/ObjectState");
//			this.mv.visitInsn(DUP);
//			this.mv.visitMethodInsn(INVOKESPECIAL, "com/vmlens/trace/agent/bootstrap/callback/state/ObjectState",
//					"<init>", "()V");
//			this.mv.visitFieldInsn(PUTFIELD, this.className, "_pAnarsoft_",
//					"Lcom/vmlens/trace/agent/bootstrap/callback/state/ObjectState;");
//		}

		// if (this.traceMethodCalls)
		// {
		// this.mv.visitLdcInsn(Integer.valueOf(methodDescriptionBuilder.getId() ));
		// this.mv.visitMethodInsn(INVOKESTATIC,
		// "com/vmlens/trace/agent/bootstrap/callback/MethodCallback" ,
		// "methodEnter", "(I)V");
		// }
		
		
	}

	protected int getMethodId() {
		return this.methodDescriptionBuilder.getId();
	}

	protected void onMonitorEnter() {
		this.mv.visitInsn(DUP);

		this.mv.visitInsn(MONITORENTER);

		this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
		this.mv.visitLdcInsn(Integer.valueOf(monitorPosition));
		monitorPosition++;
		this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorEnter",
				"(Ljava/lang/Object;II)V");
	}

	protected void onMonitorExit() {
		this.mv.visitInsn(DUP);
		this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
		this.mv.visitLdcInsn(Integer.valueOf(monitorExitPosition));
		monitorExitPosition++;
		this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorExit",
				"(Ljava/lang/Object;II)V");
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {

		ApplyMethodTemplate applyMethodTemplate = null;

		Iterator<TemplateMethodDesc> it = MethodTransformer.templateMethodDescList.iterator();

		while (it.hasNext()) {
			TemplateMethodDesc current = it.next();
			applyMethodTemplate = current.applies(opcode, owner, name, desc);

			if (applyMethodTemplate != null) {
				break;
			}

		}

		if (applyMethodTemplate != null) {
			mv.visitLdcInsn(getMethodId());
			applyMethodTemplate.apply(mv);
		} else {
			mv.visitMethodInsn(opcode, owner, name, desc, isInterface);
		}

	}

	// protected void onMethodReturn() {
	// if (this.traceMethodCalls)
	// {
	// this.mv.visitLdcInsn(Integer.valueOf(methodDescriptionBuilder.getId() ));
	// this.mv.visitMethodInsn(INVOKESTATIC,
	// "com/vmlens/trace/agent/bootstrap/callback/MethodCallback", "methodExit",
	// "(I)V");
	// }
	//
	// }

	@Override
	public final void visitInsn(int inst) {

		switch (inst) {

		case Opcodes.MONITORENTER:
			onMonitorEnter();
			break;

		case Opcodes.MONITOREXIT:
			onMonitorExit();
			break;

		// case RETURN:
		// case IRETURN:
		// case FRETURN:
		// case ARETURN:
		// case LRETURN:
		// case DRETURN:
		// //case ATHROW:
		// onMethodReturn();
		// break;

		default:
			break;

		}

		if (inst != Opcodes.MONITORENTER) {
			mv.visitInsn(inst);
		}

	}

	@Override
	public void visitIntInsn(int opcode, int operand) {

		super.visitIntInsn(opcode, operand);

		if (opcode == NEWARRAY) {
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"newArray", "(Ljava/lang/Object;)V");
		}

	}
	
	
	@Override
	public final void visitTypeInsn(int opcode, String type) {
	
		super.visitTypeInsn(opcode, type);
		
		if (opcode == ANEWARRAY) {
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"newArray", "(Ljava/lang/Object;)V");
		}
		
		
	}
	

}
