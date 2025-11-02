package com.vmlens.trace.agent.bootstrap.lock;


public class LockTypes {

    public static final ReentrantLock REENTRANT_LOCK = new ReentrantLock(1);
    public static final ReadLock READ_LOCK = new ReadLock(2);
    public static final WriteLock WRITE_LOCK = new WriteLock(3);

    public static final StampedLock STAMPED_READ_LOCK = new StampedLock(4 ,true);
    public static final StampedLock STAMPED_WRITE_LOCK = new StampedLock(5, false);
    
    public void accept(int lockId, LockTypeVisitor visitor) {
        switch(lockId) {
            case 1:
                visitor.visitReentrant();
              break;
            case 2:
                visitor.visitRead();
                break;
            case 3:
                visitor.visitWrite();
                break;
            case 4:
                visitor.visitRead();
                break;
            case 5:
                visitor.visitWrite();
                break;
            default:  
                throw new RuntimeException("unknown lockId:" + lockId);
        }
    }
}
