package com.anarsoft.race.detection.event.loadAndDistribute

import com.anarsoft.race.detection.process.load.LoadAndDistributeOneFilePosition

import java.nio.file.Path

case class DirTypeNameAndLoadAndDistributeOneFilePosition(dir: Path, typeName: String,
                                                          loadAndDistributeOneFilePosition: LoadAndDistributeOneFilePosition) {

}
