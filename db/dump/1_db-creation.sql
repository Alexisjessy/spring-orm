
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
