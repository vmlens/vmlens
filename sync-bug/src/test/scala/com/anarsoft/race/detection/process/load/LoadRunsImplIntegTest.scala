package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.loopAndRunData.LoopAndRunId
import com.anarsoft.race.detection.main.LoadRunsFactory
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileFieldAccessEvent
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.file.Files
import java.util

class LoadRunsImplIntegTest extends AnyFlatSpec with Matchers {

  "LoadRunsImpl" should "load sync actions" in {
    // Expected
    val expectedLoopAndRunIds = util.HashSet[LoopAndRunId]()
    expectedLoopAndRunIds.add(LoopAndRunId(2, 0));
    expectedLoopAndRunIds.add(LoopAndRunId(1, 1));
    expectedLoopAndRunIds.add(LoopAndRunId(1, 2));

    // Given
    val tempDirectory = Files.createTempDirectory("testDir")
    val streamRepository = new StreamRepository(tempDirectory.toString);

    val first = new VolatileFieldAccessEvent(null);
    first.setLoopId(2)
    first.setRunId(0)

    val second = new VolatileFieldAccessEvent(null);
    second.setLoopId(1)
    second.setRunId(1)

    val third = new VolatileFieldAccessEvent(null);
    third.setLoopId(1)
    third.setRunId(2)

    // When
    first.serialize(streamRepository)
    second.serialize(streamRepository)
    third.serialize(streamRepository);

    streamRepository.close();

    val loadRuns = new LoadRunsFactory().create(tempDirectory)

    val runAndLoopIdSet = util.HashSet[LoopAndRunId]();
    for (rundata <- loadRuns) {
      runAndLoopIdSet.add(rundata.loopAndRunId)
    }

    // Then
    runAndLoopIdSet should be(expectedLoopAndRunIds)

    // Fixme delete does not work
    //FileUtils.deleteDirectory(tempDirectory.toFile)

  }

}
