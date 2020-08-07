package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum InquiryStatusEnum {

	FINISHED("Finished"), IS_FAULTED("IsFaulted"), IN_ANALYSIS("InAnalysis");
	
	String asJson;
	
}
