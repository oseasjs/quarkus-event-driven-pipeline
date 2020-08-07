package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DueDayEnum {

	FIVE("5"), TEN("10"), FIFTEEN("15"), TWENTY("20"), TWENTY_FIVE("25");
	private String value;

}
