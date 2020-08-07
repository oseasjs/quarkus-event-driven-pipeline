CREATE TABLE customer (
	user_id uuid NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	phone varchar NOT NULL,
	email varchar NOT NULL,
	"name" varchar NOT NULL,
	birth_date date NOT NULL,
	mothers_name varchar NOT NULL,
	partners_name varchar NULL,
	schooling varchar NOT NULL,
	declared_income numeric(10,2) NULL,
	marital_status varchar NULL,
	declared_patrimony numeric(10,2) NOT NULL,
	proposal_id uuid NOT NULL,
	event_status varchar NULL,
	gender varchar NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (user_id)
);

CREATE TABLE customer_address (
	id uuid NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	customer_id uuid NOT NULL,
	zip_code varchar NOT NULL,
	state_abbreviation varchar NOT NULL,
	locality varchar NOT NULL,
	neighborhood varchar NOT NULL,
	public_place varchar NOT NULL,
	"number" varchar NOT NULL,
	complement varchar NULL,
	reference varchar NULL,
	longitude varchar NULL,
	latitude varchar NULL,
	address_type varchar NULL,
	domicile bool NULL,
	residence_type varchar NULL,
	CONSTRAINT customer_address_pkey PRIMARY KEY (id),
	CONSTRAINT fk_customer_address FOREIGN KEY (customer_id) REFERENCES customer(user_id)
);

CREATE TABLE customer_document (
	id uuid NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	customer_id uuid NOT NULL,
	document_type varchar,
	document_number varchar,
	issuing_state_abbreviation varchar NOT NULL,
	issuing_date date NOT NULL,
	CONSTRAINT customer_document_pkey PRIMARY KEY (id),
	CONSTRAINT fk_customer_document FOREIGN KEY (customer_id) REFERENCES customer(user_id)
);
