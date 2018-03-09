--CREATE TABLE users
--(   user_id NUMBER(10) NOT NULL,
--    first_name VARCHAR(50) NOT NULL,
--    middle_initial VARCHAR2(1),
--    last_name VARCHAR2(50) NOT NULL,
--    username VARCHAR2(40) NOT NULL,
--    password VARCHAR2(40) NOT NULL,
--    email VARCHAR2(50) NOT NULL,
--    CONSTRAINT pk_user_id PRIMARY KEY (user_id)
--);

--CREATE TABLE accounts
--(   user_id NUMBER(10) NOT NULL,
--    account_id NUMBER(10) NOT NULL,
--    balance NUMBER(15,2) DEFAULT 0.00,
--    account_name VARCHAR2(30),
--    CONSTRAINT pk_account_id PRIMARY KEY (account_id),
--    CONSTRAINT fk_user_id 
--        FOREIGN KEY (user_id) 
--        REFERENCES users(user_id)
--);

--CREATE SEQUENCE uid_seq
--MinVALUE 1
--NOMAXVALUE
--START WITH 1
--INCREMENT BY 1;
--
--CREATE OR REPLACE TRIGGER user_id_trgr
--BEFORE INSERT ON users
--FOR EACH ROW
--BEGIN
--    SELECT uid_seq.NEXTVAL INTO :new.user_id FROM dual;
--END;
--/

INSERT INTO USERS(FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, USERNAME, PASSWORD, EMAIL) VALUES ('Elizabeth', 'G', 'Rosser', 'egr', 'password', 'egr@gmail.com');
SELECT * FROM users;