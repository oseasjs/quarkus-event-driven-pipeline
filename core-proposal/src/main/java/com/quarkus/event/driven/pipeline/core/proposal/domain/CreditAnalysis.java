package com.quarkus.event.driven.pipeline.core.proposal.domain;

import com.quarkus.event.driven.pipeline.core.proposal.enums.CreditAnalysisStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "credit_analysis")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditAnalysis {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "proposal_id", nullable = false)
	private Proposal proposal;

	@Column(name = "created_date", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private CreditAnalysisStatusEnum status;

	@Column(name = "long_term_score")
	private Integer longTermScore;

	@Column(name = "short_term_score")
	private Integer shortTermScore;

	@Column(name = "credit_limit")
	private Integer limit;

	@Column(name = "assumed_income")
	private Integer assumedIncome;

}
