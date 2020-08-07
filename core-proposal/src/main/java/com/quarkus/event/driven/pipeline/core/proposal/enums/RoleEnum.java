package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ANALYSING_PROPOSAL, 
	FILLING_IN_PROPOSAL, 
	PROPOSAL_REPROVED;
}
