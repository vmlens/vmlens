# Problem

implementing logic for field, atomic updater and varhandle

# Creation

public static <U,W> AtomicReferenceFieldUpdater<U,W> newUpdater(Class<U> tclass,
Class<W> vclass,
String fieldName)

special return with fieldname and class and object

for reflect.field we cast during usage to get the field name

for varhandle we need a proxy call
or before after?
INVOKEVIRTUAL java/lang/invoke/VarHandle.get (Lcom/vmlens/test/maven/plugin/reflection/TestVarHandle;)Ljava/lang/Integer;

# Usage

set and get require the first value
probably best at methodExit?

for fields we cast to field to get the field name