CREATE USER demo IDENTIFIED BY demo;		-- User creation
GRANT CREATE SESSION TO demo;			-- Necessary to connect to the database
GRANT CREATE ANY TABLE TO demo;			-- Necessary to be able to create tables
GRANT RESOURCE TO demo;				-- Necessary to extend the system tablespace
GRANT SELECT_CATALOG_ROLE TO demo;		-- Necessary to be able to AutoTrace commands
GRANT CREATE ANY VIEW TO demo;			-- Necessary to be able to create views	
GRANT UNLIMITED TABLESPACE TO demo;		-- Necessary to extend a tablespace when needed	

