package com.vmlens.nottraced.agent.write;

import com.vmlens.trace.agent.bootstrap.description.ClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class WriteClassDescriptionAndWarningDuringStartup implements WriteClassDescriptionAndWarning {

	private final TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList;
	private final TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList;

	public WriteClassDescriptionAndWarningDuringStartup(
			TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList,
			TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList) {
		super();
		this.classAnalyzedEventList = classAnalyzedEventList;
		this.infoMessageEventList = infoMessageEventList;
	}

	@Override
	public void write(ClassDescription classDescription) {
		classAnalyzedEventList.add(wrap(classDescription));
		
	}

	@Override
	public void write(InfoMessageEvent infoMessageEvent) {
		infoMessageEventList.add(wrap(infoMessageEvent));
	}
}
