ToDo Test infrastruktur zurückbauen
Todo BlockList und Block Generic überdenken
Block<T>  T -> SyncAction Typ (nicht von Block)
hierfür Policy überdenken:

public interface Block<T> {

    int threadIndex();
    void createOrder(T otherBlock,
macht das sinn, welche variationen existieren?
wofür benötige ich generic?



CreateAlternatingOrderFromSyncAction glue code
liste aus locks -> LockContext?
liste aus syncactions -> BlockListCollection (hierfür deadlock context)

BlockListCollection:
BlockList -> AlternatingOrder