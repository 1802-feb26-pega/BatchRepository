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
  
drop table users;  
  
CREATE TABLE users (
  user_id NUMBER (5) NOT NULL,
  user_name VARCHAR2 (50) NOT NULL,
  pwd VARCHAR2 (50) NOT NULL,
  PRIMARY KEY (user_id)
  );
  
drop table accounts;
  
CREATE TABLE accounts (
    account_id NUMBER (10),
    user_id NUMBER (10),
    balance NUMBER (38),
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
  );

insert into users 
values (seq_users.nextval,'fish','passfish');

insert into accounts
values (seq_accounts, (select user_id from users where user_name = 'fish'), 0);

select user_id from users where user_name = 'fish';

select * from users;
select * from accounts;

delete from users
where user_name = 'fish';

INSERT INTO accounts(account_id, user_id, balance) VALUES (seq_accounts.nextval, 0, 0);
