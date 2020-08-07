package com.quarkus.event.driven.pipeline.core.proposal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CreditStatusEnum {

	SENT_TO_CREDIT_ENGINE(true), 
	ERRO_SENT_TO_CREDIT_ENGINE(false), 
	RETURNED_CREDIT_ENGINE(true), 
	SENT_TO_BIOMETRY(true),
	ERRO_SENT_TO_BIOMETRY(false),
	RETURNED_BIOMETRY(true),
	SENT_TO_TABLE_FRAUD(true), 
	ERRO_SENT_TO_TABLE_FRAUD(false),
	RETURNED_TABLE_FRAUD(true);
	
	private boolean isSucessStatus;

}
