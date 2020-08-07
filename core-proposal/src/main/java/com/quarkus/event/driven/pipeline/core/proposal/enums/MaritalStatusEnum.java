package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MaritalStatusEnum {

	SINGLE,
	MARRIED,
	DIVORCED,
	WIDOWER,
	SEPARATE,
	OTHERS;

}
