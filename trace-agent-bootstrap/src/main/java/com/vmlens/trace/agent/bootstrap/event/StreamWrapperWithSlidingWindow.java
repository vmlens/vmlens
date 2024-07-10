package com.vmlens.trace.agent.bootstrap.event;


import gnu.trove.list.linked.TLinkedList;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class StreamWrapperWithSlidingWindow extends AbstractStreamWrapper {

    public static final String EVENT_FILE_POSTFIX = ".vmlens";
    public static final String STATISTIC_FILE_POSTFIX = "Statistic.vmlens";
    protected final String eventDir;
    private int lastWrittenSlidingWindow = -1;
    private long start;
    protected final String name;
    private DataOutputStream streamStatistic;
    private ByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;

    public StreamWrapperWithSlidingWindow(
            String eventDir, String name, TLinkedList<AbstractStreamWrapper> list) {
        super(list);
        this.eventDir = eventDir;
        this.name = name;
    }

    public StreamWrapperWithSlidingWindow(
            String eventDir, String name) {
        super();
        this.eventDir = eventDir;
        this.name = name;
    }


    public void flush() throws IOException {
        if (streamStatistic != null) {
            streamStatistic.writeInt(lastWrittenSlidingWindow);
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            streamStatistic.flush();
            fileChannel.force(false);
        }
    }

    public void close() throws Exception {
        if (streamStatistic != null) {
            streamStatistic.writeInt(lastWrittenSlidingWindow);
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            streamStatistic.close();
            fileChannel.close();
        }
    }

    public ByteBuffer getByteBuffer(int arraySize, int blocksize) throws Exception {
      /*  if (lastWrittenSlidingWindow != slidingWindowId) {
            if (mappedByteBuffer == null) {
                fileChannel = (new RandomAccessFile(new File(eventDir + "/" + name + EVENT_FILE_POSTFIX), "rw")).getChannel();
                mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, start, blocksize);
                streamStatistic = new DataOutputStream((new FileOutputStream(eventDir + "/" + name + STATISTIC_FILE_POSTFIX)));
            }
            streamStatistic.writeInt(lastWrittenSlidingWindow);
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            lastWrittenSlidingWindow = slidingWindowId;
        }

        if ((mappedByteBuffer.position() + arraySize) >= blocksize) {
            start = start + mappedByteBuffer.position();
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, start, blocksize);
        } */
        // Fixme
        return mappedByteBuffer;
    }
}
