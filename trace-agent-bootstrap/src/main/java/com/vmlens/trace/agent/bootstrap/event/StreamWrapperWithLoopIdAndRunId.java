package com.vmlens.trace.agent.bootstrap.event;


import gnu.trove.list.linked.TLinkedList;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class StreamWrapperWithLoopIdAndRunId extends AbstractStreamWrapper {

    public static final String EVENT_FILE_POSTFIX = ".vmlens";
    public static final String STATISTIC_FILE_POSTFIX = "Statistic.vmlens";
    protected final String eventDir;
    protected final String name;

    private LoopIdAndRunId currentLoopIdAndRunId;
    private long start;
    private DataOutputStream streamStatistic;
    private ByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;

    public StreamWrapperWithLoopIdAndRunId(
            String eventDir, String name, TLinkedList<AbstractStreamWrapper> list) {
        super(list);
        this.eventDir = eventDir;
        this.name = name;
    }

    public StreamWrapperWithLoopIdAndRunId(
            String eventDir, String name) {
        super();
        this.eventDir = eventDir;
        this.name = name;
    }


    public void flush() throws IOException {
        if (streamStatistic != null) {
            streamStatistic.writeInt(currentLoopIdAndRunId.loopId());
            streamStatistic.writeInt(currentLoopIdAndRunId.runId());
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            streamStatistic.flush();
            fileChannel.force(false);
        }
    }

    public void close() throws Exception {
        if (streamStatistic != null) {
            streamStatistic.writeInt(currentLoopIdAndRunId.loopId());
            streamStatistic.writeInt(currentLoopIdAndRunId.runId());
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            streamStatistic.close();
            fileChannel.close();
        }
    }

    public ByteBuffer getByteBuffer(LoopIdAndRunId loopIdAndRunId, int arraySize, int blocksize) throws Exception {
        if (mappedByteBuffer == null) {
            fileChannel = (new RandomAccessFile(new File(eventDir + "/" + name + EVENT_FILE_POSTFIX), "rw")).getChannel();
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, start, blocksize);
            streamStatistic = new DataOutputStream((new FileOutputStream(eventDir + "/" + name + STATISTIC_FILE_POSTFIX)));
            currentLoopIdAndRunId = loopIdAndRunId;
            return mappedByteBuffer;
        }
        if (!currentLoopIdAndRunId.equals(loopIdAndRunId)) {
            streamStatistic.writeInt(currentLoopIdAndRunId.loopId());
            streamStatistic.writeInt(currentLoopIdAndRunId.runId());
            streamStatistic.writeLong(start + mappedByteBuffer.position());
            currentLoopIdAndRunId = loopIdAndRunId;
        }

        if ((mappedByteBuffer.position() + arraySize) >= blocksize) {
            start = start + mappedByteBuffer.position();
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, start, blocksize);
        }
        return mappedByteBuffer;
    }
}
