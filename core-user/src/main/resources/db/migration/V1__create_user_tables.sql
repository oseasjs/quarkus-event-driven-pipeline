CREATE TABLE "user" (
	id uuid NOT NULL,
	username varchar NOT NULL,
	"password" bpchar NOT NULL,
	"name" varchar NOT NULL,
	email varchar NOT NULL,
	phone varchar NOT NULL,
	active bool NOT NULL,
	created_at timestamp NOT NULL,
	modified_at timestamp NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_unique_active_username ON "user" USING btree (username) WHERE (active = true);

CREATE TABLE user_parameter (
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	"type" varchar NOT NULL,
	"name" varchar NOT NULL,
	value varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	CONSTRAINT user_parameter_pkey PRIMARY KEY (id),
	CONSTRAINT user_parameter_fk FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE user_role (
	user_id uuid NOT NULL,
	role_id varchar NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES "user"(id)
);
