package com.quarkus.event.driven.pipeline.core.proposal.domain;

import com.quarkus.event.driven.pipeline.core.proposal.enums.MaritalStatusEnum;
import com.quarkus.event.driven.pipeline.core.proposal.enums.ProposalStatusEnum;
import com.quarkus.event.driven.pipeline.core.proposal.enums.SchoolingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proposal implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@Column(name = "user_id", nullable = false, updatable = false)
	private String userId;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProposalStatusEnum status;

	@Column(name = "partners_name", length = 150)
	private String partnersName;

	@Column(name = "marital_status")
	@Enumerated(EnumType.STRING)
	private MaritalStatusEnum maritalStatus;

	@Column(name = "schooling")
	@Enumerated(EnumType.STRING)
	private SchoolingEnum schooling;

	@Column(name = "declared_income", scale = 8, precision = 2)
	private BigDecimal declaredIncome;

	@Column(name = "declared_patrimony", precision = 10, scale = 2)
	private BigDecimal declaredPatrimony;

	@OneToMany(mappedBy = "proposal", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<CreditAnalysis> creditAnalyses = new ArrayList<>();

}