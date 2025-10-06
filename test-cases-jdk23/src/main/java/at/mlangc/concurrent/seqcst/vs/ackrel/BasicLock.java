package at.mlangc.concurrent.seqcst.vs.ackrel;

interface BasicLock {
    void lock();
    void unlock();
}
