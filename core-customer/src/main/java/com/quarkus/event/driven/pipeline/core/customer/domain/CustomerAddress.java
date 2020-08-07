package com.quarkus.event.driven.pipeline.core.customer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "customer_address")
@EqualsAndHashCode(of = "id")
public class CustomerAddress {

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

	@Column(name = "zip_code", nullable = false, length = 8)
	private String zipCode;

	@Column(name = "state_abbreviation", nullable = false)
	private String stateAbbreviation;

	private String locality;

	private String neighborhood;

	@Column(name = "public_place", nullable = false)
	private String publicPlace;
	
	private String number;
	
	private String complement;
	
	private String reference;

	private String address_type;

	private boolean domicile;

	private String longitude;
	
	private String latitude;

	@Column(name = "residence_type", nullable = false)
	private String residenceType;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "user_id", nullable = false)
	private Customer customer;

}
