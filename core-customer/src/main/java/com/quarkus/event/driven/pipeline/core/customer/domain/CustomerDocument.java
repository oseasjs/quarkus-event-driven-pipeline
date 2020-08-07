package com.quarkus.event.driven.pipeline.core.customer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "customer_document")
@EqualsAndHashCode(of = "id")
public class CustomerDocument {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "document_number", length = 50)
	private String documentNumber;

	@Column(name = "issuing_state_abbreviation", length = 2)
	private String issuingStateAbbreviation;

	@Column(name = "issuing_date")
	private LocalDate issuingDate;
	
	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

}
