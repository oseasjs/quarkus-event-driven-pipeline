package com.quarkus.event.driven.pipeline.core.proposal.mapper;

import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.proposal.domain.CreditAnalysis;
import com.quarkus.event.driven.pipeline.core.proposal.domain.Proposal;
import com.quarkus.event.driven.pipeline.core.proposal.enums.CreditAnalysisStatusEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Mapper(componentModel = "cdi")
public abstract class ProposalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schooling", expression = "java(com.quarkus.event.driven.pipeline.core.proposal.enums.SchoolingEnum.COMPLETE_HIGH_SCHOOL)")
    @Mapping(target = "maritalStatus", expression = "java(com.quarkus.event.driven.pipeline.core.proposal.enums.MaritalStatusEnum.SINGLE)")
    @Mapping(target = "partnersName", expression = "java(result.getName().getFullName() + \" Patterns \")")
    @Mapping(target = "declaredIncome", constant = "1500.00")
    @Mapping(target = "declaredPatrimony", constant = "15000.00")

    public abstract Proposal toDomain(Result result);

    @AfterMapping
    public void afterMapping(Proposal proposal, Result result) {
        buildCreditAnalises(proposal);
    }

    private void buildCreditAnalises(Proposal proposal) {

        CreditAnalysis creditAnalysis = new CreditAnalysis();

        creditAnalysis.setCreatedDate(LocalDateTime.now());
        creditAnalysis.setProposal(proposal);
        creditAnalysis.setStatus(CreditAnalysisStatusEnum.APPROVED);
        creditAnalysis.setLongTermScore(10);
        creditAnalysis.setShortTermScore(10);
        creditAnalysis.setLimit(1000);
        creditAnalysis.setAssumedIncome(10000);

        proposal.setCreditAnalyses(Arrays.asList(creditAnalysis));

    }

}
