package com.anarsoft.trace.agent.preanalyzed.serialize;


import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.io.DataOutputStream;
import java.io.IOException;


public class SerializePackageOrClass {

    public void serialize(TLinkedList<TLinkableWrapper<PackageOrClass>> packageOrClasses,
                          DataOutputStream out) throws IOException {
        out.writeInt(packageOrClasses.size());
        for (TLinkableWrapper<PackageOrClass> packageOrClass : packageOrClasses) {
            packageOrClass.element().serialize(out);
        }
    }
}
