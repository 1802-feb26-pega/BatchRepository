CREATE USER bankapp IDENTIFIED BY bankapp;    
GRANT CREATE SESSION TO bankapp;          
GRANT CREATE ANY TABLE TO bankapp;    
GRANT RESOURCE TO bankapp;             
GRANT SELECT_CATALOG_ROLE TO bankapp;   
GRANT CREATE ANY VIEW TO bankapp;       
GRANT UNLIMITED TABLESPACE TO bankapp;

CREATE SEQUENCE seq_users
MINVALUE 1
START WITH 1
MAXVALUE 999
INCREMENT BY 1
CYCLE;

CREATE SEQUENCE seq_accounts
MINVALUE 1
START WITH 1
MAXVALUE 999
INCREMENT BY 1
CYCLE;
  
--drop table users;  
  
CREATE TABLE users (
  user_id NUMBER (5) NOT NULL,
  user_name VARCHAR2 (50) NOT NULL,
  pwd VARCHAR2 (50) NOT NULL,
  PRIMARY KEY (user_id)
  );
  
--drop table accounts;
  
CREATE TABLE accounts (
    account_id NUMBER (10),
    user_id NUMBER (10),
    balance NUMERIC (36,2),
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
  );
/*
insert into users 
values (seq_users.nextval,'fish','passfish');

insert into accounts (account_id, user_id, balance)
values (96, 6, 32.5);

select user_id from users where user_name = 'fish';

select * from users;
select * from accounts;
*/
