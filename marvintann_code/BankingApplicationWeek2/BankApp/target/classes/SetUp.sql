CREATE OR REPLACE PROCEDURE 


DROP SEQUENCE seq_users;

CREATE SEQUENCE seq_users
MINVALUE 1
START WITH 1
MAXVALUE 999
INCREMENT BY 1
CYCLE;

CREATE OR REPLACE TRIGGER trig_users
  BEFORE INSERT ON users
  FOR EACH ROW
BEGIN
  SELECT seq_users.nextval
  INTO :new.user_id
  FROM dual;
END;
/

DROP SEQUENCE seq_accounts;

CREATE SEQUENCE seq_accounts
MINVALUE 1
START WITH 1
MAXVALUE 999
INCREMENT BY 1
CYCLE;

CREATE OR REPLACE TRIGGER trig_accounts
  BEFORE INSERT ON accounts
  FOR EACH ROW
BEGIN
  SELECT seq_accounts.nextval
  INTO :new.account_id
  FROM dual;
END;
/
  
drop table users;  
  
CREATE TABLE users (
  user_id INTEGER PRIMARY KEY,
  user_name VARCHAR2 (50) NOT NULL,
  pwd VARCHAR2 (50) NOT NULL
  );
  
drop table accounts;
  
CREATE TABLE accounts (
    account_id INTEGER,
    user_id NUMBER (5),
    balance NUMBER (20, 20),
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

commit;