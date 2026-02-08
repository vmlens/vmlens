package at.mlangc.concurrent.seqcst.vs.ackrel;

abstract class IndexedLock implements BasicLock {
    abstract int threadLimit();
}
