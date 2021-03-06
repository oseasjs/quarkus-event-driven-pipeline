package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum SchoolingEnum {
	
	INCOMPLETE_ELEMENTARY,
	COMPLETE_ELEMENTARY,
	INCOMPLETE_HIGH_SCHOOL,
	COMPLETE_HIGH_SCHOOL,
	INCOMPLETE_HIGHER_EDUCATION,
	COMPLETE_HIGHER_EDUCATION,
	INCOMPLETE_POST_GRADUATE,
	COMPLETE_GRADUATE,
	INCOMPLETE_MASTER,
	COMPLETE_MASTER,
	INCOMPLETE_DOCTORATE,
	COMPLETE_DOCTORATE,
	OTHER;
	
}
