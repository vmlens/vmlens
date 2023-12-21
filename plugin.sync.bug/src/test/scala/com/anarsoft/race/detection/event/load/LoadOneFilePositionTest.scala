package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.event.gen.SyncActionsDeserializer
import com.anarsoft.race.detection.event.syncAction.LoadedSyncActionEvent
import com.anarsoft.race.detection.testFixture.RaceDetectionVolatileFixture
import com.vmlens.trace.agent.bootstrap.event.gen.EventConstants
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.{File, RandomAccessFile}
import java.nio.channels.FileChannel
import java.nio.file.Paths

class LoadOneFilePositionTest extends AnyFlatSpec with Matchers {

  " LoadOneFilePosition" should "load a file into a bytebuffer" in {
    // Given
    val name = "syncActions";
    val tempDir = Paths.get(System.getProperty("java.io.tmpdir"))
    val filePath = tempDir.resolve(name + ".vmlens");
    val writeFile = new RandomAccessFile(filePath.toFile, "rw");
    val fileChannel = writeFile.getChannel
    val mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 10 * EventConstants.MAX_ARRAY_SIZE)

    val testData = new RaceDetectionVolatileFixture().volatileReadAndWrite();
    val events = testData.javaEvents;
    for (e <- events) {
      e.serialize(mappedByteBuffer);
    }
    val endPos = mappedByteBuffer.position();
    fileChannel.close();
    writeFile.close();

    val loadByteBuffer = new LoadOneFilePosition();

    // When
    val loadedByteBuffer = loadByteBuffer.load(new RandomAccessFile(filePath.toFile, "r"), FilePosition(0, endPos));

    // Then
    val deserializeEvents = DeserializeEvents[LoadedSyncActionEvent]();
    val serializedEvents = deserializeEvents.deserialize(loadedByteBuffer, new SyncActionsDeserializer());
    serializedEvents should be(testData.volatileAccessEvents);


  }

}