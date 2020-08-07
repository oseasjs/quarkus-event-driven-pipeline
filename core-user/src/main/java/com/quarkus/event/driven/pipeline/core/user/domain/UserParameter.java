package com.quarkus.event.driven.pipeline.core.user.domain;

import com.quarkus.event.driven.pipeline.core.user.enums.UserParameterTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_parameter")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParameter {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserParameterTypeEnum type;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "value", length = 100, nullable = false)
	private String value;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
