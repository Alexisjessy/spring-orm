
DROP TABLE if exists public.account CASCADE;



CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	email VARCHAR(50),
	birthday date,
	creationTime timestamp,
	balance bigint
);



DROP TABLE IF EXISTS public.client CASCADE;


CREATE TABLE client (
    id SERIAL PRIMARY KEY,        
    first_name VARCHAR(100) NOT NULL, 
    last_name VARCHAR(50) NOT NULL,   
    email VARCHAR(100) UNIQUE NOT NULL, 
    birthday DATE,                     
    creationTime TIMESTAMP DEFAULT NOW() 
);

CREATE TABLE insurance (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCEs client(id) ON DELETE CASCADE,

);

TABLE client_insurance (
	client_id UUID,
	insurance_id INT,
	PRIMARY KEY (client_id,insurance_id),
	CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE,
	CONSTRAINT fk_insurance FOREIGN KEY (insurance_id) REFERENCES insurance(id) ON DELETE CASCADE
)