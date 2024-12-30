package com.anarsoft.trace.agent.runtime.write;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class WriteClassDescriptionDuringStartup  implements WriteClassDescription  {

    private TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList;
	
	public WriteClassDescriptionDuringStartup(
			TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList) {
		super();
		this.classAnalyzedEventList = classAnalyzedEventList;
	}

	@Override
	public void write(ClassDescription classDescription) {
        classAnalyzedEventList.add(new TLinkableWrapper<>(classDescription));
		
	}

}
