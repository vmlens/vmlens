package com.vmlens.trace.agent.bootstrap.event;

public interface RuntimeEvent extends StaticEvent {

    int ID_Field = 0;
    int ID_SyncActions = 1;
    int ID_Method = 2;
    int ID_DirectMemory = 3;


    void send(SendEventContext context);

}
