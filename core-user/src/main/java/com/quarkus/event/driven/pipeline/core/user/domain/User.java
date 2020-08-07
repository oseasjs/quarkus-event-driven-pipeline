package com.quarkus.event.driven.pipeline.core.user.domain;

import com.quarkus.event.driven.pipeline.core.user.enums.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(schema = "public", name = "user")
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	private String username;

	private String password;

	private String name;

	private String email;

	private String phone;

	private Boolean active;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@ElementCollection(targetClass = RoleEnum.class)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role_id")
	@Enumerated(EnumType.STRING)
	private Set<RoleEnum> roles = new HashSet<>();

	@OneToMany( mappedBy = "user" , fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private Set<UserParameter> userParameterSet = new HashSet<>();

	public void addAllRoles(final List<RoleEnum> role) {
		this.roles.addAll(role);
	}

}
