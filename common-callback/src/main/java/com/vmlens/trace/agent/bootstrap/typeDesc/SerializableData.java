package com.vmlens.trace.agent.bootstrap.typeDesc;

import java.io.DataOutputStream;
import java.io.IOException;


public interface SerializableData {

	void accept(SerializableDataVisitor parallizeMetaDataVisitor);
	void serialize(DataOutputStream stream) throws IOException;
}
