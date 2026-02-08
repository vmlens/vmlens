package at.mlangc.concurrent.seqcst.vs.ackrel;

public class ThreadIndex {
    private static final String PREFIX = "indexed-thread-";

    public static int current() {
        Thread currentThread = Thread.currentThread();

        try {
            return Integer.parseInt(currentThread.getName(), PREFIX.length(), currentThread.getName().length(), 10);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot extract thread index from '" + currentThread.getName() + "'", e);
        }
    }



    public static String toName(int index) {
        return PREFIX + index;
    }
}
