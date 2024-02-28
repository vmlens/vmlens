package com.vmlens.report.dataView;

public interface MemoryAccessReportBuilder {

    void addMemoryAccess(MemoryAccessView event);

    void addDataRace(MemoryAccessView event);

}
