package com.quarkus.event.driven.pipeline.core.proposal.service;

import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.proposal.domain.Proposal;
import com.quarkus.event.driven.pipeline.core.proposal.enums.ProposalStatusEnum;
import com.quarkus.event.driven.pipeline.core.proposal.mapper.ProposalMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProposalService {

    @Inject
    EntityManager entityManager;

    @Inject
    ProposalMapper proposalMapper;

    @Transactional
    public void save(Result result) {

        Proposal proposal = proposalMapper.toDomain(result);
        proposalMapper.afterMapping(proposal, result);

        proposal.setStatus(ProposalStatusEnum.APPROVED);

        entityManager.persist(proposal);
        result.setProposalId(proposal.getId().toString());

    }

}
