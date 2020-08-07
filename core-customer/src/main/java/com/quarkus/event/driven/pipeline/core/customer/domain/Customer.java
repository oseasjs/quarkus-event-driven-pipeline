package com.quarkus.event.driven.pipeline.core.customer.domain;

import com.quarkus.event.driven.pipeline.core.customer.enums.CustomerEventStatus;
import com.quarkus.event.driven.pipeline.core.customer.enums.MaritalStatusEnum;
import com.quarkus.event.driven.pipeline.core.customer.enums.SchoolingEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Customer {

	@Id
	@Column(name = "user_id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "name", length = 100)
	@Size(max = 100)
	private String name;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "partners_name", length = 100)
	@Size(max = 100)
	private String partnersName;

	@Column(name = "mothers_name", length = 100)
	@Size(max = 100)
	private String mothersName;

	@Column(name = "phone", nullable = false, length = 13)
	@Size(max = 13)
	private String phone;

	@Column(name = "email", nullable = false, length = 100)
	@Size(max = 100)
	private String email;

	@Column(name = "schooling")
	@Enumerated(EnumType.STRING)
	private SchoolingEnum schooling;

	@Column(name = "marital_status")
	@Enumerated(EnumType.STRING)
	private MaritalStatusEnum maritalStatus;

	@Column(name = "declared_income")
	private BigDecimal declaredIncome;

	@Column(name = "declared_patrimony")
	private BigDecimal declaredPatrimony;

	@Column(name = "proposal_id", nullable = false)
	private UUID proposalId;

	@Column(name = "event_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private CustomerEventStatus eventStatus;

	@OneToMany( mappedBy = "customer" , fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private List<CustomerAddress> addressList;

	@Column(name = "gender")
	private String gender;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private CustomerDocument document;

}
