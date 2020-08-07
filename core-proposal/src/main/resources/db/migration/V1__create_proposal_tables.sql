CREATE TABLE proposal (
	id uuid NOT NULL,
	created_date timestamp NOT NULL,
	updated_date timestamp NOT NULL,
	user_id varchar NOT NULL,
	status varchar NOT NULL,
	schooling varchar NULL,
    partners_name varchar NULL,
    marital_status varchar NULL,
	declared_income numeric(8,2) NULL,
	declared_patrimony numeric(10,2) NULL,
	CONSTRAINT proposal_pkey PRIMARY KEY (id)
);

CREATE TABLE credit_analysis (
	id uuid NOT NULL,
	created_date timestamp NOT NULL,
	updated_date timestamp NOT NULL,
	proposal_id uuid NOT NULL,
	status varchar NOT NULL,
	long_term_score int4 NULL,
	short_term_score int4 NULL,
	credit_limit int4 NULL,
	assumed_income int4 NULL,
	CONSTRAINT credit_analysis_pkey PRIMARY KEY (id),
	CONSTRAINT fk_credit_analysis_proposal FOREIGN KEY (proposal_id) REFERENCES proposal(id)
);